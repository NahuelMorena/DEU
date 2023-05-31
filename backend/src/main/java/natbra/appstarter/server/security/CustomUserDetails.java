package natbra.appstarter.server.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import natbra.appstarter.server.model.auth.User;

public class CustomUserDetails implements UserDetails {
	private static final long serialVersionUID = 4026049264978873780L;
	
	private User user;
	private List<GrantedAuthority> roles;
	
	public CustomUserDetails(User user, List<GrantedAuthority> authorities) {
		super();
		this.user = user;
		this.roles = authorities;
	}
	
	public CustomUserDetails() {
		super();
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}
	
	public User getUser() {
		return this.user;
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
		return true;
	}

}
