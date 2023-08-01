package natbra.appstarter.server.controllers;

import java.util.*;

import natbra.appstarter.server.model.auth.Role;
import natbra.appstarter.server.model.auth.UserType;
import natbra.appstarter.server.model.train.Training;
import natbra.appstarter.server.repository.RolesRepository;
import natbra.appstarter.server.repository.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import natbra.appstarter.server.Utils;
import natbra.appstarter.server.model.auth.User;
import natbra.appstarter.server.repository.UserRepository;

@Controller
public class UserController {
private final String baseUrl = "/admin/users";

	@Autowired UserRepository userRepository;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	UserTypeRepository userTypeRepository;
	@Autowired PasswordEncoder encoder;
	@Autowired Utils utils;

	@GetMapping(baseUrl)
	public HttpEntity<List<User>> getUsers(){
		return ResponseEntity.ok(userRepository.findAll());
	}

	@PostMapping("/register")
	public HttpEntity<User> createUserIfNotExists(@RequestBody User user){
		if(userRepository.existsByUsernameOrEmail(user.getUsername(),user.getEmail())){
			return ResponseEntity.ok(null);
		}else{
			user.setPassword(encoder.encode(user.getPassword()));
			return ResponseEntity.ok(userRepository.save(user));
		}
	}

	@PostMapping(baseUrl)
	public HttpEntity<User> addUserByTrainer(@RequestBody User user){
		if(userRepository.existsByUsernameOrEmail(user.getUsername(),user.getEmail())){
			return ResponseEntity.ok(null);
		}else{
			user.setPassword(encoder.encode(user.getPassword()));
			Set<Role> roles = new HashSet<>();
			Role role = rolesRepository.findById(1L).orElseThrow(NoSuchElementException::new);
			roles.add(role);
			List<Role> list = new ArrayList<>(roles);
			user.setRoles(list);
			return ResponseEntity.ok(userRepository.save(user));
		}
	}

	@PutMapping("/register")
	public HttpEntity<User> acceptUser(@RequestBody User user){
		User player = userRepository.findById(user.getId()).orElseThrow(NoSuchElementException::new);
		Set<Role> roles = new HashSet<>();
		Role role = rolesRepository.findById(1L).orElseThrow(NoSuchElementException::new);
		roles.add(role);
		List<Role> list = new ArrayList<>(roles);
		player.setRoles(list);
		return ResponseEntity.ok(userRepository.save(player));
	}
	@GetMapping(baseUrl + "/get-user")
	public HttpEntity<Optional<User>> getUser(){
		return ResponseEntity.ok(userRepository.findById(utils.getAuthUser().getId()));
	}

	@GetMapping(baseUrl + "/get-players")
	public HttpEntity<List<User>> getUsersByTrainerId(){
		return ResponseEntity.ok(userRepository.findAllByTrainerId(utils.getAuthUser().getId()));
	}

	@GetMapping(baseUrl + "/get-players-accepted")
	public HttpEntity<Set<User>> getAcceptedUsersByTrainerId(){
		List<User> users = userRepository.findAllByTrainerId(utils.getAuthUser().getId());
		Set<User> usersRequests = new HashSet<>();
		for(User user : users){
			if (!user.getRoles().isEmpty()){
				usersRequests.add(user);
			}
		}
		return ResponseEntity.ok(usersRequests);
	}

	@GetMapping(baseUrl + "/get-players-requests")
	public HttpEntity<Set<User>> getUsersRequestsByTrainerId(){
		List<User> users = userRepository.findAllByTrainerId(utils.getAuthUser().getId());
		Set<User> usersRequests = new HashSet<>();
		for(User user : users){
			if(user.getRoles().isEmpty()){
				usersRequests.add(user);
			}
		}
		return ResponseEntity.ok(usersRequests);
	}

	@GetMapping(baseUrl + "/get-trainers")
	public HttpEntity<Set<User>> getTrainers(){
		List<User> users = userRepository.findAll();
		Set<User> trainers = new HashSet<>();
		List<Role> roles;
		for(User user : users){
			roles = user.getRoles();
			for(Role role : roles){
				//Si no anda con ese agarrar ID y comparar con 3L que es lo mismo y me anduvo
				if(role.getName().equals("TRAINER")){
					trainers.add(user);
				}
			}

		}
		return ResponseEntity.ok(trainers);
	}

	@GetMapping(baseUrl + "/get-types")
	public HttpEntity<List<UserType>> getUserTypes(){
		return ResponseEntity.ok(userTypeRepository.findAll());
	}
	@DeleteMapping(baseUrl)
	public HttpEntity<User> deleteUser(@RequestBody User user){
		userRepository.delete(user);
		return ResponseEntity.ok(user);
	}

	@PutMapping(baseUrl)
	public HttpEntity<User> editUser(@RequestBody User user){
		return ResponseEntity.ok(userRepository.save(user));
	}
}
