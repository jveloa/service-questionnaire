package cu.edu.mes.sigenu.training.core.security;

import java.io.IOException;
import java.security.Principal;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.callback.SecurityAssociationHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import cu.edu.mes.sigenu.training.core.utils.Client;
import cu.edu.mes.subsystem.security.AuthenticationService;
import cu.edu.mes.vo.UserDetailsVO;


public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenProvider tokenProvider;

    private static final String ROLES_GROUP = "Roles";

	private List<GrantedAuthority> authorities;

    private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && tokenProvider.validate(jwt)) {
                String userId = tokenProvider.getUserId(jwt);
                String username = tokenProvider.getUsername(jwt);
                String password = tokenProvider.getPassword(jwt);
                
                if(autenticate(username, password)) {
    				
    				Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, authorities);
    				SecurityContextHolder.getContext().setAuthentication(authentication);
    			} else {
    				throw new BadCredentialsException("Invalid username/password");
    			}
                		
                UserDetailsVO user = (UserDetailsVO)Client.getAuthenticationService(username, password).getUserDetails();

                UserDetails userDetails = UserPrincipal.create(user, tokenProvider.getPassword(jwt));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(request, response);
    }
    
    private boolean autenticate(String username, String password) {
    	boolean authenticated = false;
    	SecurityAssociationHandler handler = new SecurityAssociationHandler();
		SimplePrincipal principal = new SimplePrincipal(username);
		handler.setSecurityInfo(principal, (String)password); 
		try {
			Resource resource = new ClassPathResource("auth.conf");
			String authFile =  resource.getFile().getAbsolutePath(); 
			System.setProperty("java.security.auth.login.config", authFile);
			LoginContext loginContext = new LoginContext("sigenu", handler);
			loginContext.login();
			
			AuthenticationService authenticationService = Client.getAuthenticationService(username, password);
			Subject subject = authenticationService.getUserSubject();
			if (subject != null) {
				populateUserRoles(subject);
				authenticated = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return authenticated;
    }
	
	private void populateUserRoles(Subject subject) {
		Set<Group> groups = subject.getPrincipals(Group.class);
		Iterator<Group> iterator = groups.iterator();
		List<String> grantedAuthorities = new ArrayList<String>();
		while (iterator.hasNext()) {
			Group group = (Group) iterator.next();
			if (ROLES_GROUP.equals(group.getName())) {
				Enumeration members = group.members();
				while (members.hasMoreElements()) {
					Principal role = (Principal) members.nextElement();
					grantedAuthorities.add(role.getName());
				}
			}
		}
		authorities = AuthorityUtils.createAuthorityList(grantedAuthorities.toArray(new String[0]));
	}

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
