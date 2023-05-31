package natbra.appstarter.server.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import natbra.appstarter.server.model.auth.Role;
import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.repository.UserRepository;
import natbra.appstarter.server.security.CustomUserDetails;

@Service
public class UserDetailService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;
	
	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role: user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
			role.getAuthorities().stream()
	         .map(p -> new SimpleGrantedAuthority(p.getName()))
	         .forEach(authorities::add);
		}
		return new CustomUserDetails(user, authorities);
	}

}
