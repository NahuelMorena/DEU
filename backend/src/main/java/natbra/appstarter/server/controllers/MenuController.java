package natbra.appstarter.server.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import natbra.appstarter.server.model.menu.MenuOption;
import natbra.appstarter.server.services.MenuService;

@Controller
public class MenuController {
	private final String BASE_URL = "/menu";
	
	@Autowired MenuService menuService;
	
	@GetMapping(BASE_URL)
	public HttpEntity<List<MenuOption>> getMenu() {
		return ResponseEntity.ok(menuService.generalMenu());
	}
	
}
