-- liquibase formatted sql
-- changeset Initdata:1675699146952-1
INSERT INTO `authorities` (`id`, `name`) VALUES
(1, 'CAN_VIEW_HOME'),
(2, 'CAN_VIEW_USERS'),
(3, 'CAN_VIEW_ABOUT'),
(5, 'CAN_VIEW_SECURITY');

-- changeset Initdata:1675699146952-2
INSERT INTO `roles` (`id`, `name`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- changeset Initdata:1675699146952-3
INSERT INTO `authorities_roles` (`id`, `id_authority`, `id_role`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 1),
(4, 4, 2),
(5, 5, 2);    

-- changeset Initdata:1675699146952-5
INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', '$2a$12$BngXzPDbcSen1b.TCNha6uszCLVTiGoXCfo35q34KyP7bk5MSA/Km');

-- changeset Initdata:1675699146952-6
INSERT INTO `user_roles` (`id`, `id_user`, `id_role`) VALUES
(1, 1, 1),
(2, 1, 2);
