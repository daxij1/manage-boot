--          --
--  T_USER  --
--          --
-- 密钥manage-boot@2023，密码123456
insert into `T_USER` VALUES (1, 'admin', 'f080d3964e1d25df2da8a31c3b69dbeb', './img/head.jpeg', '吴彦祖', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_USER` VALUES (2, 'test', 'f080d3964e1d25df2da8a31c3b69dbeb', './img/head.jpeg', '发狂迷', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--          --
--  T_MENU  --
--          --
insert into `T_MENU` VALUES (1, '系统配置', 1, null, '','el-icon-s-tools', '系统配置', 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU` VALUES (2, '用户管理', 2, 1, 'user.html','', '系统配置-用户管理', 0, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU` VALUES (3, '角色管理', 2, 1, 'role.html','','系统配置-角色管理', 0, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU` VALUES (4, '菜单管理', 2, 1, 'menu.html','', '系统配置-菜单管理', 0, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


--          --
--  T_ROLE  --
--          --
insert into `T_ROLE` VALUES (1, '超级管理员', '超级管理员', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_ROLE` VALUES (2, '系统管理员', '系统管理员', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_ROLE` VALUES (3, '普通用户', '普通用户', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--          --
--  T_ROLE_USERBINDING  --
--          --
insert into `T_ROLE_USERBINDING` VALUES (1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_ROLE_USERBINDING` VALUES (2, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

--          --
--  T_AUTH  --
--          --
insert into `T_AUTH` VALUES (1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH` VALUES (2, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH` VALUES (3, 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH` VALUES (4, 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH` VALUES (5, 1, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH` VALUES (6, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_AUTH` VALUES (7, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
