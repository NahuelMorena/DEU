package natbra.appstarter.server.model.menu;

public enum SubMenu {
	
	General("General"),
	Admin("Administrador");
	
	private String name;
	
	private SubMenu(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}
