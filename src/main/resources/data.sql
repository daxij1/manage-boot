--          --
--  T_USER  --
--          --
-- 密钥manage-boot@2023，密码123456
insert into `T_USER`(`id`,`username`,`password`,`avator`,`nickname`,`del`,`createtime`,`lastmodifiedtime`) 
VALUES (0, 'admin', 'f080d3964e1d25df2da8a31c3b69dbeb', './img/head.jpeg', '吴彦祖', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_USER`(`id`,`username`,`password`,`avator`,`nickname`,`del`,`createtime`,`lastmodifiedtime`) 
VALUES (1, 'test', 'f080d3964e1d25df2da8a31c3b69dbeb', './img/head.jpeg', '发狂迷', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--          --
--  T_MENU  --
--          --
insert into `T_MENU`(`id`,`name`,`level`,`parentid`,`path`,`icon`,`descr`,`del`,`sort`,`createtime`,`lastmodifiedtime`) 
VALUES (1, '系统配置', 1, null, '','el-icon-s-tools', '系统配置', 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU`(`id`,`name`,`level`,`parentid`,`path`,`icon`,`descr`,`del`,`sort`,`createtime`,`lastmodifiedtime`) 
VALUES (2, '用户管理', 2, 1, 'user.html','', '系统配置-用户管理', 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU`(`id`,`name`,`level`,`parentid`,`path`,`icon`,`descr`,`del`,`sort`,`createtime`,`lastmodifiedtime`) 
VALUES (3, '菜单管理', 2, 1, 'menu.html','', '系统配置-菜单管理', 0, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU`(`id`,`name`,`level`,`parentid`,`path`,`icon`,`descr`,`del`,`sort`,`createtime`,`lastmodifiedtime`) 
VALUES (4, '角色管理', 2, 1, 'role.html','','系统配置-角色管理', 0, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


--          --
--  T_ROLE  --
--          --
insert into `T_ROLE`(`id`,`name`,`descr`,`createtime`,`lastmodifiedtime`) 
VALUES (0, '超级管理员', '超级管理员', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_ROLE`(`id`,`name`,`descr`,`createtime`,`lastmodifiedtime`) 
VALUES (1, '系统管理员', '系统管理员', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_ROLE`(`id`,`name`,`descr`,`createtime`,`lastmodifiedtime`) 
VALUES (2, '普通用户', '普通用户', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--          --
--  T_ROLE_USERBINDING  --
--          --
insert into `T_ROLE_USERBINDING`(`id`,`userid`,`roleid`,`createtime`,`lastmodifiedtime`) 
VALUES (1, 0, 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_ROLE_USERBINDING`(`id`,`userid`,`roleid`,`createtime`,`lastmodifiedtime`) 
VALUES (2, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--          --
--  T_AUTH  --
--          --
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (1, 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (2, 0, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (3, 0, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (4, 0, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (5, 0, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (6, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (7, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (8, 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (9, 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH`(`id`,`roleid`,`menuid`,`createtime`,`lastmodifiedtime`) 
VALUES (10, 1, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
