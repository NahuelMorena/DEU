-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-07-2023 a las 19:23:22
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `diseño_experiencia_usuario`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities`
--

CREATE TABLE `authorities` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `authorities`
--

INSERT INTO `authorities` (`id`, `name`) VALUES
(1, 'CAN_VIEW_HOME'),
(2, 'CAN_VIEW_USERS'),
(3, 'CAN_VIEW_ABOUT'),
(5, 'CAN_VIEW_SECURITY'),
(6, 'CAN_VIEW_TRAININGS'),
(7, 'CAN_VIEW_EDIT_TRAINING'),
(12, 'CAN_VIEW_PLAYERS'),
(13, 'CAN_VIEW_EDIT_PLAYER'),
(14, 'CAN_VIEW_PLANIFICATIONS'),
(15, 'CAN_VIEW_EDIT_PLANIFICATION'),
(16, 'CAN_VIEW_USERSPLANIFICATIONS'),
(17, 'CAN_VIEW_PROFILE'),
(18, 'CAN_VIEW_SETTING'),
(19, 'CAN_VIEW_PLAYERSREQUESTS'),
(20, 'CAN_VIEW_PLANIFICATIONBYPLAYER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities_roles`
--

CREATE TABLE `authorities_roles` (
  `id` int(11) NOT NULL,
  `id_authority` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `authorities_roles`
--

INSERT INTO `authorities_roles` (`id`, `id_authority`, `id_role`) VALUES
(11, 1, 2),
(12, 1, 1),
(13, 2, 2),
(16, 5, 2),
(17, 3, 2),
(27, 1, 3),
(29, 3, 3),
(30, 6, 3),
(31, 7, 3),
(32, 12, 3),
(33, 13, 3),
(34, 14, 3),
(35, 15, 3),
(38, 16, 3),
(39, 16, 1),
(40, 16, 3),
(41, 16, 1),
(42, 18, 2),
(43, 18, 3),
(44, 18, 1),
(45, 17, 2),
(46, 17, 3),
(47, 17, 1),
(48, 19, 3),
(49, 20, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `califications`
--

CREATE TABLE `califications` (
  `id` int(11) NOT NULL,
  `note` tinyint(2) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_training` int(11) NOT NULL,
  `id_trainer_planification` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `databasechangelog`
--

CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `databasechangelog`
--

INSERT INTO `databasechangelog` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
('1675699146952-1', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 1, 'EXECUTED', '8:17a186c241025cf9cd6604d97a19206b', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-2', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 2, 'EXECUTED', '8:6ddd24bb71531d00b47c7e2f49604cdd', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-6', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 3, 'EXECUTED', '8:973fdff72a6290010848a877d00106df', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-8', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 4, 'EXECUTED', '8:4515fcb1cfa1f167f0d6d78563d5e3ae', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-9', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 5, 'EXECUTED', '8:7ae2ee3a1303acdc79b572093b1bec5a', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-12', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 6, 'EXECUTED', '8:f8a322d08086cf66d905233787b3c9f0', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-16', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 7, 'EXECUTED', '8:b30c43cfae0a9a19954310d392c0782b', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-17', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 8, 'EXECUTED', '8:9e001540cd1fbe6839d42553523b30b7', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-18', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 9, 'EXECUTED', '8:58e8d6df8492ce63bfc56383aa778e5b', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-30', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 10, 'EXECUTED', '8:30ba7bf319d7e1ff26176698368b80c2', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-31', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 11, 'EXECUTED', '8:7f9126097f6fee39380107675a84f60e', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-1', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 12, 'EXECUTED', '8:bef490c47e8eda34c7f6c48aca605ff4', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-2', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 13, 'EXECUTED', '8:52f81b6941fa9cdd5aca003ddff9d9e0', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-3', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 14, 'EXECUTED', '8:0099192661f4ce42564a1f387db5dfa5', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-5', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 15, 'EXECUTED', '8:82b6395a87b030e1362acb485b718856', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-6', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 16, 'EXECUTED', '8:f0fa8805f782a4d023e570f06922e420', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-1', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 1, 'EXECUTED', '8:17a186c241025cf9cd6604d97a19206b', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-2', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 2, 'EXECUTED', '8:6ddd24bb71531d00b47c7e2f49604cdd', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-6', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 3, 'EXECUTED', '8:973fdff72a6290010848a877d00106df', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-8', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 4, 'EXECUTED', '8:4515fcb1cfa1f167f0d6d78563d5e3ae', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-9', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 5, 'EXECUTED', '8:7ae2ee3a1303acdc79b572093b1bec5a', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-12', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 6, 'EXECUTED', '8:f8a322d08086cf66d905233787b3c9f0', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-16', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 7, 'EXECUTED', '8:b30c43cfae0a9a19954310d392c0782b', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-17', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 8, 'EXECUTED', '8:9e001540cd1fbe6839d42553523b30b7', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-18', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 9, 'EXECUTED', '8:58e8d6df8492ce63bfc56383aa778e5b', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-30', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 10, 'EXECUTED', '8:30ba7bf319d7e1ff26176698368b80c2', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-31', 'Ale', 'src/main/resources/db/changelogs/2023-02-06T160237065Z-initial.mysql.sql', '2023-03-07 19:13:49', 11, 'EXECUTED', '8:7f9126097f6fee39380107675a84f60e', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-1', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 12, 'EXECUTED', '8:bef490c47e8eda34c7f6c48aca605ff4', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-2', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 13, 'EXECUTED', '8:52f81b6941fa9cdd5aca003ddff9d9e0', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-3', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 14, 'EXECUTED', '8:0099192661f4ce42564a1f387db5dfa5', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-5', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 15, 'EXECUTED', '8:82b6395a87b030e1362acb485b718856', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320'),
('1675699146952-6', 'Initdata', 'src/main/resources/db/changelogs/2023-02-06T162031230Z-initdata.sql', '2023-03-07 19:13:49', 16, 'EXECUTED', '8:f0fa8805f782a4d023e570f06922e420', 'sql', '', NULL, '4.2.2', NULL, NULL, '8227229320');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `databasechangeloglock`
--

CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `databasechangeloglock`
--

INSERT INTO `databasechangeloglock` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
(1, b'0', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planifications`
--

CREATE TABLE `planifications` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `planifications`
--

INSERT INTO `planifications` (`id`, `name`) VALUES
(28, 'Entrenamientos de Arquero'),
(29, 'Entrenamientos de Delanteros'),
(30, 'Entrenamientos de Mediocampistas'),
(31, 'Entrenamientos de Defensas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'USER'),
(2, 'ADMIN'),
(3, 'TRAINER');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trainer_planifications`
--

CREATE TABLE `trainer_planifications` (
  `id` int(11) NOT NULL,
  `id_training` int(11) NOT NULL,
  `id_planification` int(11) NOT NULL,
  `order_number` tinyint(4) NOT NULL,
  `minutes` tinyint(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trainer_planifications`
--

INSERT INTO `trainer_planifications` (`id`, `id_training`, `id_planification`, `order_number`, `minutes`) VALUES
(32, 24, 28, 2, 20),
(33, 24, 28, 0, 20),
(34, 23, 28, 1, 10),
(35, 22, 29, 1, 20),
(36, 21, 29, 0, 20),
(37, 20, 30, 2, 20),
(38, 19, 30, 1, 10),
(39, 19, 30, 3, 10),
(40, 23, 31, 0, 15),
(41, 20, 31, 1, 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trainings`
--

CREATE TABLE `trainings` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `date` date NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_training_type` int(11) NOT NULL,
  `description` text NOT NULL,
  `warmup_time` int(11) NOT NULL,
  `repetitions_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `trainings`
--

INSERT INTO `trainings` (`id`, `name`, `date`, `id_user`, `id_training_type`, `description`, `warmup_time`, `repetitions_quantity`) VALUES
(19, 'Precisión en el pase', '2023-07-29', 7, 2, 'Mejora la precisión y la velocidad en los pases cortos y largos.', 10, 4),
(20, 'Dominio y control del balón', '2023-07-29', 7, 3, 'Trabajar en el primer toque y el control de balón en diferentes situaciones.', 10, 10),
(21, 'Tiro a la portería', '2023-07-29', 7, 4, 'Practicar diferentes tipos de disparos para mejorar la técnica de finalización.', 4, 5),
(22, 'Regate y cambio de dirección', '2023-07-29', 7, 2, 'Mejorar las habilidades de regate y cambios rápidos de dirección.', 3, 5),
(23, 'Defensa individual', '2023-07-29', 7, 3, 'Trabajar en la técnica de marcaje y la anticipación defensiva.', 10, 2),
(24, 'Reflejo y reacción', '2023-07-29', 7, 2, 'Ejercicios específicos para desarrollar reflejos y rapidez de reacción en los porteros.', 10, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `training_type`
--

CREATE TABLE `training_type` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `training_type`
--

INSERT INTO `training_type` (`id`, `name`) VALUES
(1, 'Resistencia'),
(2, 'Velocidad'),
(3, 'Estrategia'),
(4, 'Fuerza');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `id_trainer` int(11) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(200) NOT NULL,
  `surname` varchar(200) NOT NULL,
  `email` varchar(255) NOT NULL,
  `birthdate` date NOT NULL,
  `telephone` int(30) NOT NULL,
  `id_user_type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `id_trainer`, `username`, `password`, `name`, `surname`, `email`, `birthdate`, `telephone`, `id_user_type`) VALUES
(1, NULL, 'admin', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Josefina', 'Gonzalez', 'admin@gmail.com', '1991-08-15', 23424235, NULL),
(3, 7, 'juan', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Juan', 'Ramirez', 'juan@gmail.com', '1990-01-15', 5435435, 1),
(5, NULL, 'admin2', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Jorge', 'Gonzalez', 'admin@gmail.com', '1991-08-15', 23424235, NULL),
(7, NULL, 'fer', '$2a$10$mXA2/0VX76NmiF3.7pkY3eY.wmnSEv6.qjwAA4Q/X7buKFXGz9vAm', 'Fermin', 'Moreno', 'ferminmorenoz99@gmail.com', '1991-05-14', 1341412421, NULL),
(8, 7, 'pedro', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Pedro', 'Gonzalez', 'pedro@gmail.com', '1990-01-17', 5435435, 2),
(9, 7, 'victor', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Victor', 'Romero', 'victor@gmail.com', '1990-01-16', 5435435, 3),
(14, 7, 'pablo', '$2a$10$mnU2pfar5ESvsyXqljeWO.2iRoumNoHFm4lQQ0KE6RRyll1ZitEsi', 'Pablo', 'Perez', 'pablo@gmail.com', '2023-06-13', 345345, 2),
(15, 7, 'juanm', '$2a$10$G2Oiu/2.osMOp.0CW1KpKe3ccm6ZlFz09zeElR6jncFZbSSj6Mhlm', 'Juan', 'Martinez', 'juanm@gmail.com', '1978-05-09', 43533453, 2),
(16, 7, 'player', '$2a$10$wxcZSNAmvBv4aSLsNUTvfOSzo4zPr7kG1u3mkoiQNYqwk0QRpEz4u', 'Aaron ', 'Molina', 'player@gmail.com', '2003-05-06', 221345623, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_planifications`
--

CREATE TABLE `user_planifications` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_planification` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_planifications`
--

INSERT INTO `user_planifications` (`id`, `id_user`, `id_planification`, `date`) VALUES
(15, 16, 31, '2023-07-31'),
(16, 8, 29, '2023-07-31'),
(17, 14, 29, '2023-07-31'),
(18, 3, 31, '2023-07-31'),
(19, 9, 30, '2023-07-31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_roles`
--

CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_roles`
--

INSERT INTO `user_roles` (`id`, `id_user`, `id_role`) VALUES
(2, 1, 2),
(6, 7, 3),
(12, 8, 1),
(14, 9, 1),
(19, 3, 1),
(21, 14, 1),
(22, 16, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_type`
--

CREATE TABLE `user_type` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `user_type`
--

INSERT INTO `user_type` (`id`, `name`) VALUES
(1, 'Defensa'),
(2, 'Ataque'),
(3, 'Centro');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `authorities`
--
ALTER TABLE `authorities`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `authorities_roles`
--
ALTER TABLE `authorities_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_authority` (`id_authority`),
  ADD KEY `id_role` (`id_role`);

--
-- Indices de la tabla `califications`
--
ALTER TABLE `califications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user_calificated` (`id_user`),
  ADD KEY `id_training` (`id_training`),
  ADD KEY `id_trainer_planification` (`id_trainer_planification`);

--
-- Indices de la tabla `databasechangeloglock`
--
ALTER TABLE `databasechangeloglock`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `planifications`
--
ALTER TABLE `planifications`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `trainer_planifications`
--
ALTER TABLE `trainer_planifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_planification` (`id_planification`),
  ADD KEY `id_user_training` (`id_training`);

--
-- Indices de la tabla `trainings`
--
ALTER TABLE `trainings`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_type` (`id_training_type`);

--
-- Indices de la tabla `training_type`
--
ALTER TABLE `training_type`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `player_trainer` (`id_trainer`),
  ADD KEY `user_type_id` (`id_user_type`);

--
-- Indices de la tabla `user_planifications`
--
ALTER TABLE `user_planifications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user_player` (`id_user`),
  ADD KEY `id_user_planification` (`id_planification`);

--
-- Indices de la tabla `user_roles`
--
ALTER TABLE `user_roles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_role` (`id_role`),
  ADD KEY `id_user` (`id_user`);

--
-- Indices de la tabla `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `authorities`
--
ALTER TABLE `authorities`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `authorities_roles`
--
ALTER TABLE `authorities_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- AUTO_INCREMENT de la tabla `califications`
--
ALTER TABLE `califications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `planifications`
--
ALTER TABLE `planifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `trainer_planifications`
--
ALTER TABLE `trainer_planifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `trainings`
--
ALTER TABLE `trainings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `training_type`
--
ALTER TABLE `training_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `user_planifications`
--
ALTER TABLE `user_planifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `user_type`
--
ALTER TABLE `user_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `authorities_roles`
--
ALTER TABLE `authorities_roles`
  ADD CONSTRAINT `authority_role` FOREIGN KEY (`id_authority`) REFERENCES `authorities` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `role_authority` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `califications`
--
ALTER TABLE `califications`
  ADD CONSTRAINT `id_trainer_planification` FOREIGN KEY (`id_trainer_planification`) REFERENCES `trainer_planifications` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_training` FOREIGN KEY (`id_training`) REFERENCES `trainings` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_user_calificated` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `trainer_planifications`
--
ALTER TABLE `trainer_planifications`
  ADD CONSTRAINT `id_planification` FOREIGN KEY (`id_planification`) REFERENCES `planifications` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_user_training` FOREIGN KEY (`id_training`) REFERENCES `trainings` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `trainings`
--
ALTER TABLE `trainings`
  ADD CONSTRAINT `id_type` FOREIGN KEY (`id_training_type`) REFERENCES `training_type` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `player_trainer` FOREIGN KEY (`id_trainer`) REFERENCES `users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `user_type_id` FOREIGN KEY (`id_user_type`) REFERENCES `user_type` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `user_planifications`
--
ALTER TABLE `user_planifications`
  ADD CONSTRAINT `id_user_planification` FOREIGN KEY (`id_planification`) REFERENCES `planifications` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_user_player` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
