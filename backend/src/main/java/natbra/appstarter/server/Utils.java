package natbra.appstarter.server;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.security.CustomUserDetails;


public class Utils {
	
	public Utils() {
	}
	
	public Authentication getAuth() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
		return auth;
	}
	
	public User getAuthUser() {
		CustomUserDetails userDetail = (CustomUserDetails) this.getAuth().getPrincipal();
		return userDetail.getUser();
	}
}
