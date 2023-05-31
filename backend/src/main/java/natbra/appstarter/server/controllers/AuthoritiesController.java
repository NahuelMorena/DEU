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

import natbra.appstarter.server.model.auth.Authority;
import natbra.appstarter.server.repository.AuthoritiesRepository;

@Controller
public class AuthoritiesController {
	private final String baseUrl = "/admin/authorities";
	
	@Autowired AuthoritiesRepository authoritiesRepository;
	
	@GetMapping(baseUrl)
	public HttpEntity<List<Authority>> getAuthoritiess(){
		return ResponseEntity.ok(authoritiesRepository.findAll());
	}
	
	@DeleteMapping(baseUrl)
	public HttpEntity<Authority> deteleRole(@RequestBody Authority authority){
		authoritiesRepository.delete(authority);
		return ResponseEntity.ok(authority);
	}
	
	@PostMapping(baseUrl)
	public HttpEntity<Authority> addAuthority(@RequestBody Authority authority){
		return ResponseEntity.ok(authoritiesRepository.save(authority));
	}
}
