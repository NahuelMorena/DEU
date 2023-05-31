package natbra.appstarter.server.model.menu;

import java.util.List;

public class Menu {
	private String title;
	private List<MenuOption> options;
	
	public Menu() {}
	
	public Menu(String title, List<MenuOption> options) {
		this.title = title;
		this.options = options;
	}
	
	public String getTitle() {
		return title;
	}
	
	public List<MenuOption> getOptions() {
		return options;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setOptions(List<MenuOption> options) {
		this.options = options;
	}
	
}
