package natbra.appstarter.server.model.homeCardMenu;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CardMenuOption {
    Trainings("Gestiona tus entrenamientos","/trainings","Accede a tus rutinas existentes, crea \n" +
            "nuevos entrenamientos según tus preferencias, eliminar y/o modifique cualquier detalle"
            ,CardSubMenu.Trainer.getName()),
    Players("Coordina a tus jugadores","/players", "Podrás visualizar detalladamente a cada uno de \n" +
            "tus jugadores, con información relevante. Ademas de agrega y acepta solicitudes de nuevos jugadores."
            ,CardSubMenu.Trainer.getName()),
    Planifications("Organiza tus planificaciones","/planifications","Aquí tendrás el control total sobre \n" +
            " tus planificaciones de entrenamiento. El sistema brinda la flexibilidad para adaptarlos a tus objetivos específicos."
            ,CardSubMenu.Trainer.getName()),
    UsersPlanifications("Estadisticas de rendimiento","/users_planifications", "Explora el rendimiento individual \n" +
            "de cada jugador en sus entrenamientos y evalúe a cada uno de manera sencilla.",CardSubMenu.Trainer.getName()),
    PlanificationsByPlayer("Entrenamientos asignados","/users_planifications","Accede a tus entrenamientos asignados \n" +
            "por el entrenador. Visualiza las actividades programadas y evalúa tu desempeño.", CardSubMenu.Player.getName()),
    Users("Usuarios del sistema","/users","Gestiona y administre a los usuarios que se encuentren asociados a la aplicación", CardSubMenu.Admin.getName()),
    Security("Panel de administración", "/security", "Controla la seguridad del sistema. Podrás ver, \n" +
            "crear, borrar y asignar permisos y roles a los usuarios."
            ,CardSubMenu.Admin.getName()),
    Profile("Ver mis datos","/profile","Observe todos los detalles y datos relevantes asociados a tu cuenta."
            ,CardSubMenu.General.getName()),
    Setting("Configuración","/setting","Ajusta los colores para que se adapten a tu estilo y mejoren tu experiencia \n" +
            "visual. Además, modifica el tamaño de las letras para garantizar una lectura cómoda y adecuada para ti."
            ,CardSubMenu.General.getName());

    private String title;
    private String url;
    private String description;
    private String subMenu;

    private CardMenuOption(String title, String url, String description, String subMenu) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.subMenu = subMenu;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public String getSubMenu() {
        return subMenu;
    }
}
