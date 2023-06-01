-- 密钥kuaidi100@2023，密码123456
insert into `T_USER` VALUES (1, 'admin', '4ab204881d06aee06d4fc4053dfe49ae', './img/head_admin.jpg', '吴彦祖', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into `T_MENU` VALUES (1, '系统配置', 1, 1, null, '','el-icon-s-tools', '系统配置', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU` VALUES (2, '用户管理', 2, 2, 1, 'user.html','', '系统配置-用户管理', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU` VALUES (3, '菜单管理', 2, 2, 1, 'menu.html','', '系统配置-菜单管理', 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU` VALUES (4, '角色管理', 2, 2, 1, 'role.html','','系统配置-角色管理', 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
insert into `T_MENU` VALUES (5, '权限管理', 2, 2, 1, 'auth.html','', '系统配置-权限管理', 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
