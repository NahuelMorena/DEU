package natbra.appstarter.server.controllers;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.repository.UserRepository;
import natbra.appstarter.server.security.CustomUserDetails;

@Controller
public class HomeController {
	
	@Autowired
	UserRepository userRepository;
	
    @GetMapping("/api/loggedUser")
    public HttpEntity<CustomUserDetails> loggedUser() {
    	CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new HttpEntity<CustomUserDetails>(user);
    }
    
    @GetMapping("/api/test")
    public HttpEntity<String> test() throws InterruptedException {
    	TimeUnit.SECONDS.sleep(2);
    	return new HttpEntity<String>("Hola");
    }
    
    @GetMapping("/api/getUser")
    @PreAuthorize("hasAuthority('USER')")
    public HttpEntity<User> getUser()  {
    	User user = this.userRepository.findByUsername("admin")
    			.orElseThrow(() -> new UsernameNotFoundException("Username does not exist"));
    	return new HttpEntity<User>(user);
    }
    
    @GetMapping("/api/blocked")
    @PreAuthorize("hasAuthority('IMPOSSIBLE_ROL')")
    public HttpEntity<String> blocked()  {
    	return new HttpEntity<String>("Testeando roles");
    }

}
