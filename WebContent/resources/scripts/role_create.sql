-- Initialization User Role
INSERT INTO `librarysystem`.`user_role`
(`id`,
`role_name`,
`description`)
VALUES
(1,
'admin',
'Library Admin');

INSERT INTO `librarysystem`.`user_role`
(`id`,
`role_name`,
`description`)
VALUES
(2,
'member',
'Library Member');