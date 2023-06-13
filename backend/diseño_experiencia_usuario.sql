-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-06-2023 a las 22:06:39
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.0.19

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(18, 'CAN_VIEW_SETTING');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities_roles`
--

CREATE TABLE `authorities_roles` (
  `id` int(11) NOT NULL,
  `id_authority` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(47, 17, 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `planifications`
--

INSERT INTO `planifications` (`id`, `name`) VALUES
(8, 'Probando'),
(22, 'ahora si gato'),
(23, 'ASDA'),
(24, 'nuevo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `trainer_planifications`
--

INSERT INTO `trainer_planifications` (`id`, `id_training`, `id_planification`, `order_number`, `minutes`) VALUES
(10, 17, 22, 0, 5),
(17, 17, 23, 0, 5),
(21, 16, 23, 2, 71),
(22, 17, 23, 1, 10),
(23, 16, 24, 0, 50);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `trainings`
--

INSERT INTO `trainings` (`id`, `name`, `date`, `id_user`, `id_training_type`, `description`, `warmup_time`, `repetitions_quantity`) VALUES
(15, 'asd', '2023-06-04', 1, 2, 'cvxcvxvcx', 34, 34),
(16, 'nuevo', '2023-06-05', 7, 2, 'asd', 4, 3),
(17, 'Otro entrenamiento', '2023-06-05', 7, 3, 'Nuevo entrenamiento', 4, 33);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `training_type`
--

CREATE TABLE `training_type` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`id`, `id_trainer`, `username`, `password`, `name`, `surname`, `email`, `birthdate`, `telephone`, `id_user_type`) VALUES
(1, NULL, 'admin', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Josefina', 'Gonzalez', 'admin@gmail.com', '1991-08-15', 23424235, NULL),
(3, 7, 'juan', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Juan', 'Ramirez', 'juan@gmail.com', '1990-01-16', 5435435, 1),
(5, NULL, 'admin2', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Jorge', 'Gonzalez', 'admin@gmail.com', '1991-08-15', 23424235, NULL),
(7, NULL, 'fer', '$2a$10$mXA2/0VX76NmiF3.7pkY3eY.wmnSEv6.qjwAA4Q/X7buKFXGz9vAm', 'Fermin', 'Moreno', 'ferminmorenoz99@gmail.com', '1991-05-14', 1341412421, NULL),
(8, 7, 'pedro', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Pedro', 'Gonzalez', 'pedro@gmail.com', '1990-01-17', 5435435, 2),
(9, 7, 'victor', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km', 'Victor', 'Romero', 'victor@gmail.com', '1990-01-16', 5435435, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_planifications`
--

CREATE TABLE `user_planifications` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_planification` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_planifications`
--

INSERT INTO `user_planifications` (`id`, `id_user`, `id_planification`, `date`) VALUES
(2, 3, 8, '2023-06-06'),
(3, 8, 8, '2023-06-06'),
(4, 9, 8, '2023-06-06'),
(5, 3, 23, '2023-06-07'),
(6, 8, 23, '2023-06-07'),
(7, 9, 23, '2023-06-07'),
(8, 3, 22, '2023-06-20'),
(10, 9, 22, '2023-06-20'),
(11, 9, 24, '2023-06-22');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_roles`
--

CREATE TABLE `user_roles` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_role` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user_roles`
--

INSERT INTO `user_roles` (`id`, `id_user`, `id_role`) VALUES
(1, 1, 1),
(2, 1, 2),
(6, 7, 3),
(10, 3, 1),
(12, 8, 1),
(14, 9, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_type`
--

CREATE TABLE `user_type` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `authorities_roles`
--
ALTER TABLE `authorities_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `planifications`
--
ALTER TABLE `planifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `trainer_planifications`
--
ALTER TABLE `trainer_planifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `trainings`
--
ALTER TABLE `trainings`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `training_type`
--
ALTER TABLE `training_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `user_planifications`
--
ALTER TABLE `user_planifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `user_roles`
--
ALTER TABLE `user_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

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
