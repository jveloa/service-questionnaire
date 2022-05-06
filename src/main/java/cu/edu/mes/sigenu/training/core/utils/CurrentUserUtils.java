package cu.edu.mes.sigenu.training.core.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import cu.edu.mes.sigenu.training.core.security.UserPrincipal;

public class CurrentUserUtils {
	
	public CurrentUserUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final String ANONYMOUS_USER = "anonymousUser";
	
	public static String getUsername() {
		String username = "";
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			username = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		return username;
	}
	
	
	public static String getFullName() {
		String fullName = null;
		if (isLogged()) {
			fullName = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getFullName();
		}
		return fullName;
	}
	
	public static String getPassword() {
		String password = "";
		if (isLogged()) {
			password = (String) SecurityContextHolder.getContext().getAuthentication().getCredentials();
		}
		return password;
	}
	
	public static String getPasswordPlain() {
		String password = "";
		if (isLogged()) {
			password = ((UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPassword();
		}
		return password;
	}
	
	
	public static boolean isLogged() {
		boolean logged = false;
		if ((SecurityContextHolder.getContext().getAuthentication() != null) && (!getUsername().equals(ANONYMOUS_USER))) {
			logged = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
		}
		return logged;
	}
	
	
}
