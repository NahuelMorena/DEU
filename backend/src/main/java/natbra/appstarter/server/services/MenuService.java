package natbra.appstarter.server.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import natbra.appstarter.server.model.menu.MenuOption;
import natbra.appstarter.server.security.CustomUserDetails;

@Service
public class MenuService {
	
	private CustomUserDetails getLoggedUser() {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
	
	private boolean findAuthority(String authority) {
		CustomUserDetails user = this.getLoggedUser();
		List<GrantedAuthority> roles = user.getAuthorities();
		return roles.stream().map(auth -> auth.getAuthority()).anyMatch(auth -> auth.equals(authority));
	}
	
	public List<MenuOption> generalMenu() {
		List<MenuOption> menuOptions = Arrays.asList(MenuOption.values())
				.stream()
				.filter(
						option -> findAuthority(
								"CAN_VIEW_"+ option.toString().toUpperCase()
								)
						)
				.toList();
		return menuOptions;
	}
}
