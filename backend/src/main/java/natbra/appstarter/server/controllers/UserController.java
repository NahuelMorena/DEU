package natbra.appstarter.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import natbra.appstarter.server.Utils;
import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.repository.UserRepository;

@Controller
public class UserController {
private final String baseUrl = "/admin/users";
	
	@Autowired UserRepository userRepository;
	@Autowired PasswordEncoder encoder;
	@Autowired Utils utils;
	
	@GetMapping(baseUrl)
	public HttpEntity<List<User>> getUsers(){
		return ResponseEntity.ok(userRepository.findAll());
	}
	
	@DeleteMapping(baseUrl)
	public HttpEntity<User> deleteUser(@RequestBody User user){
		userRepository.delete(user);
		return ResponseEntity.ok(user);
	}
	
	@PostMapping(baseUrl)
	public HttpEntity<User> addUser(@RequestBody User user){
		user.setPassword(encoder.encode(user.getPassword()));
		return ResponseEntity.ok(userRepository.save(user));
	}
}
