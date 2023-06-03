package natbra.appstarter.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import natbra.appstarter.server.model.auth.Role;
import natbra.appstarter.server.repository.RolesRepository;

@Controller
public class RoleController {
	private final String baseUrl = "/admin/roles";
	
	@Autowired RolesRepository rolesRepository;
	
	@GetMapping(baseUrl)
	public HttpEntity<List<Role>> getRoles(){
		return ResponseEntity.ok(rolesRepository.findAll());
	}
	
	@DeleteMapping(baseUrl)
	public HttpEntity<Role> deleteRole(@RequestBody Role role){
		rolesRepository.delete(role);
		return ResponseEntity.ok(role);
	}
	
	@PostMapping(baseUrl)
	public HttpEntity<Role> addRole(@RequestBody Role role){
		return ResponseEntity.ok(rolesRepository.save(role));
	}
}
