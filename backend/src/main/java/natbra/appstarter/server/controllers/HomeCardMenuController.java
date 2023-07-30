package natbra.appstarter.server.controllers;

import natbra.appstarter.server.model.homeCardMenu.CardMenuOption;
import natbra.appstarter.server.services.HomeCardMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeCardMenuController {
    private final String BASE_URL = "/homeCardMenu";

    @Autowired HomeCardMenuService cardMenuService;

    @GetMapping(BASE_URL)
    public HttpEntity<List<CardMenuOption>> getCardMenu() {
        return ResponseEntity.ok(cardMenuService.generalMenu());
    }
}
