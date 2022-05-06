package cu.edu.mes.sigenu.training.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cu.edu.mes.vo.SecurityRoleVO;
import cu.edu.mes.vo.UserDetailsVO;
import cu.edu.mes.vo.UserVO;


public class UserPrincipal implements UserDetails {
    private String id;
    private String email;
    private String password;
    private boolean active;
    private String username;
    private String fullName;
    private String identification;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String id, String username, String password, boolean active, String identification, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
        this.identification = identification;
    }

    public static UserPrincipal create(UserDetailsVO user, String password) {
    	List<GrantedAuthority> authorities;
    	try {
    		authorities = AuthorityUtils.createAuthorityList(fetchRoles(user).toArray(new String[0]));
		} catch (Exception e) {
			authorities = Collections.
	                singletonList(new SimpleGrantedAuthority("ROLE_USER"));
			
		}
    	return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                password,
                true,
                null,
                authorities
        );
    }
    
    public static List<String> fetchRoles(UserDetailsVO user)
        {
	    	List<String> roles = new ArrayList<String>();
			SecurityRoleVO[] secRoles =  user.getSecurityRoles();
		    for (SecurityRoleVO securityRoleVO : secRoles) {
				roles.add(securityRoleVO.getRoleLink());
			}
		    return roles;
        }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFullName() {
        return fullName;
    }

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}
}
