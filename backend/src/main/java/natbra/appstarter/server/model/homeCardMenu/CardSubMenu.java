package natbra.appstarter.server.model.homeCardMenu;

public enum CardSubMenu {
    General("General"),
    Player("Jugador"),
    Trainer("Entrenador"),
    Admin("Administrador");


    private String name;

    private CardSubMenu(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
