# Deportes App

Este es el repositorio correspondiente al Trabajo Final Integrador (TFI) del curso de Diseño de Experiencia de Usuario y DCU de la Facultad de Informática de la Universidad Nacional de La Plata, desarrollado sobre la cursada correspondiente al primer semestre del año 2023. 

## Descripción del proyecto

El objetivo de este proyecto es brindar una aplicación que funcione como una herramienta de asistencia para un grupo de entrenadores de diferentes deportes, como fútbol, hockey, rugby y ciclismo. La aplicación ofrece características de gestión de equipos y jugadores, planificación de entrenamientos y asignación de actividades, lo que permite un seguimineto efectivo de los jugadores.

## Requisitos técnicos

La aplicación se ha implementado utilizando las siguientes tecnologías:

* Backend: Spring Boot v2.7.5 con Java v17.0.7.
* Frontend: Vue.js v2.7.14.

## Instrucciones para iniciar la aplicación

### Frontend

Para ejecutar el frontend, sigue estos pasos: 

1. Ubícate en el directorio `/frontend`.
2. Ejecuta el siguiente comando:

```bash
$   npm run serve
```
## Funcionalidades de la aplicación

Deportes App tiene como objetivo principal proporcionar un completo seguimineto de los jugadores en distintas disciplinas deportivas. Para lograr esto, la aplicación cuenta con las siguientes funcionalidades:

1. `Registro de Planificación de Entrenaminetos:` Los entrenadores pueden registrar la planificación de entrenamientos, estableciendo el número de sesiones (por ejemplo, 8 entrenamientos) y proporcionando detalles específicos para cada uno. Estos detalles incluyen el tiempo de entrada en calor, ejercicios a realizar, cantidad de repeticiones. Cada entrenamiento estará etiquetado según su tipo (fuerza, resistencia, velocidad o estrategia).
2. `Gestión de Jugadores:` La aplicación ofrecerá una sección dedicada al Alta, Baja y Modificación (ABM) de jugadores. Aquí, los entrenadores podrán administrar la información de los jugadores y mantener actualizado el registro de su rendimineto.
3. `Evaluación de Rendimiento:` Los entrenadores tendrán la capacidad de evaluar el rendimiento de los jugadores después de cada entrenamiento. Podrán calificar las actuaciones de los jugadores.
4. `Gestion de seguridad:` Los administradores tendrán la capacidad de administrar los roles y permisos con los que operara la aplicación.

## Roles y usuarios predefinidos

La aplicación cuenta con tres roles predefinidos, cada uno con sus respectivos usuarios para iniciar sesión:

### Administrador

* Nombre de usuario: `admin`
* Contraseña: `admin`

El rol de Administrador tiene acceso a la gestión de usuarios, roles y permisos dentro de la aplicación. Puede ver, crear, editar y eliminar usuarios, roles y permisos.

### Entrenador

* Nombre de usuario: `fer`
* Contraseña: `fer`

El rol rol de Entrenador tiene acceso a funcionalidades más específicas en el sistema. Puede gestionar jugadores, entrenamientos y planificaciones. Además, puede ver los entrenaminetos asignados a cada jugador y calificar su desempeño.

### Jugador 

* Nombre de usuario: `player`
* Contraseña: `player`

El rol de Jugador tiene acceso a la visualización de los entrenamientos asignados por su respectivo entrenador. También puede ver las calificaciones recibidas después de ser evaluado.

## Consideraciónes 

### Configuración de pantalla

La aplicación ofrece un sistema de configuraciones de pantalla que permite a los usuarios personalizar el aspecto visual para adaptarse a sus preferencias o necesidades. Las opciones incluyen ajustes de paletas de colores y tamaños de letra, lo que garantiza una experiencia de lectura cómoda y adecuada.

### Accesibilidad web

Durante el desarrollo de la aplicación, se prestó especial atención a la accesibilidad web. Se realizaron pruebas utilizando la herramienta Wave, que evalúa la accesibilidad del sitio web y proporciona información sobre constraste y estilos de página. Como resultado, la aplicación es compatible con lectores de pantalla, asegurando una experiencia accesible para todos los usuarios. 
