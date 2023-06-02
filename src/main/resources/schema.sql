-- h2的表名和列名用``
--          --
--  T_USER  --
--          --
drop table IF EXISTS `T_USER`;
create TABLE `T_USER` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`username` varchar(60) UNIQUE NOT NULL,
`password` varchar(60) NOT NULL,
`avator` varchar(60) NOT NULL,
`nickname` varchar(60),
`createtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`lastmodifiedtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
comment on column `T_USER`.`id` is 'id';
comment on column `T_USER`.`username` is '用户名';
comment on column `T_USER`.`password` is '密码';
comment on column `T_USER`.`avator` is '头像';
comment on column `T_USER`.`nickname` is '昵称';
comment on column `T_USER`.`createtime` is '创建时间';
comment on column `T_USER`.`lastmodifiedtime` is '最近更新时间';

--          --
--  T_MENU  --
--          --
drop table IF EXISTS `T_MENU`;
create TABLE `T_MENU` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` varchar(60) NOT NULL,
`level` INT NOT NULL,
`type` INT NOT NULL,
`parentid` INT,
`path` varchar(128),
`icon` varchar(128),
`descr` varchar(128),
`active` INT DEFAULT 1,
`sort` INT DEFAULT 1,
`createtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`lastmodifiedtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
comment on column `T_MENU`.`id` is 'id';
comment on column `T_MENU`.`name` is '菜单名称';
comment on column `T_MENU`.`level` is '菜单级别 1、2';
comment on column `T_MENU`.`type` is '菜单类型 1-目录型 2-菜单项';
comment on column `T_MENU`.`parentid` is '父级菜单id ';
comment on column `T_MENU`.`path` is '页面路径';
comment on column `T_MENU`.`icon` is '图标';
comment on column `T_MENU`.`descr` is '描述';
comment on column `T_MENU`.`active` is '启用标志，0停用1启用';
comment on column `T_MENU`.`sort` is '排序字段';
comment on column `T_MENU`.`createtime` is '创建时间';
comment on column `T_MENU`.`lastmodifiedtime` is '最近更新时间';

--          --
--  T_ROLE  --
--          --
drop table IF EXISTS `T_ROLE`;
create TABLE `T_ROLE` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`name` varchar(60) NOT NULL,
`descr` varchar(128),
`createtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`lastmodifiedtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
comment on column `T_ROLE`.`id` is 'id';
comment on column `T_ROLE`.`name` is '角色名称';
comment on column `T_ROLE`.`descr` is '描述';
comment on column `T_ROLE`.`createtime` is '创建时间';
comment on column `T_ROLE`.`lastmodifiedtime` is '最近更新时间';

--          --
--  T_ROLE_USERBINDING  --
--          --
drop table IF EXISTS `T_ROLE_USERBINDING`;
create TABLE `T_ROLE_USERBINDING` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`userid` INT NOT NULL,
`roleid` INT NOT NULL,
`createtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`lastmodifiedtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
comment on column `T_ROLE_USERBINDING`.`id` is 'id';
comment on column `T_ROLE_USERBINDING`.`userid` is '用户id';
comment on column `T_ROLE_USERBINDING`.`roleid` is '角色id';
comment on column `T_ROLE_USERBINDING`.`createtime` is '创建时间';
comment on column `T_ROLE_USERBINDING`.`lastmodifiedtime` is '最近更新时间';

--          --
--  T_AUTH  --
--          --
drop table IF EXISTS `T_AUTH`;
create TABLE `T_AUTH` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`roleid` INT NOT NULL,
`menuid` INT NOT NULL,
`createtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
`lastmodifiedtime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
comment on column `T_AUTH`.`id` is 'id';
comment on column `T_AUTH`.`roleid` is '角色id';
comment on column `T_AUTH`.`menuid` is '菜单id';
comment on column `T_AUTH`.`createtime` is '创建时间';
comment on column `T_AUTH`.`lastmodifiedtime` is '最近更新时间';