package natbra.appstarter.server.model.homeCardMenu;

import natbra.appstarter.server.model.menu.MenuOption;

import java.util.List;

public class CardMenu {
    private String title;
    private List<CardMenuOption> options;

    public CardMenu() {}

    public CardMenu(String title, List<CardMenuOption> options) {
        this.title = title;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public List<CardMenuOption> getOptions() {
        return options;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOptions(List<CardMenuOption> options) {
        this.options = options;
    }
}
