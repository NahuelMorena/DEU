package natbra.appstarter.server.services;

import natbra.appstarter.server.model.homeCardMenu.CardMenuOption;
import natbra.appstarter.server.security.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class HomeCardMenuService {
    private CustomUserDetails getLoggedUser() {
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user;
    }

    private boolean findAuthority(String authority) {
        CustomUserDetails user = this.getLoggedUser();
        List<GrantedAuthority> roles = user.getAuthorities();
        return roles.stream().map(auth -> auth.getAuthority()).anyMatch(auth -> auth.equals(authority));
    }

    public List<CardMenuOption> generalMenu() {
        List<CardMenuOption> cardMenuOptions = Arrays.asList(CardMenuOption.values())
                .stream()
                .filter(
                        option -> findAuthority(
                                "CAN_VIEW_"+ option.toString().toUpperCase()
                        )
                )
                .toList();
        return cardMenuOptions;
    }
}
