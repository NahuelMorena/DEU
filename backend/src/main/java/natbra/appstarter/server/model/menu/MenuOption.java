package natbra.appstarter.server.model.menu;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum MenuOption {

	Home("Home", "/", "mdi-home", SubMenu.General.getName()),
	About("About", "/about", "mdi-information", SubMenu.General.getName()),
	Profile("Profile", "/profile","mdi-account", SubMenu.General.getName()),
	Setting("Setting", "/setting", "mdi-cog", SubMenu.General.getName()),
	Security("Seguridad", "/security", "mdi-key", SubMenu.Admin.getName()),
	Players("Players", "/players", "mdi-account-multiple", SubMenu.Trainer.getName()),
	Trainings("Trainings", "/trainings", "mdi-account-multiple", SubMenu.Admin.getName()),
	Planifications("Planifications", "/planifications", "mdi-account-multiple", SubMenu.Admin.getName()),
	Users("Users", "/users", "mdi-account-multiple", SubMenu.Admin.getName());

	private String name;
	private String url;
	private String icon;
	private String subMenu;

	private MenuOption(String name, String url, String icon, String subMenu) {
		this.name = name;
		this.url = url;
		this.icon = icon;
		this.subMenu = subMenu;
	}

	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public String getIcon() {
		return icon;
	}

	public String getSubMenu() {
		return subMenu;
	}

}
