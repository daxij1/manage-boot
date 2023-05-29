-- 特别要注意，h2的列名要用``，否则查询会有各种问题
DROP TABLE IF EXISTS `T_USER`;
CREATE TABLE `T_USER` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `username` varchar(60) UNIQUE NOT NULL,
  `password` varchar(60) NOT NULL,
  `avator` varchar(60) NOT NULL,
  `nickname` varchar(60)
);
COMMENT ON COLUMN `T_USER`.`id` IS 'id';
COMMENT ON COLUMN `T_USER`.`username` IS '用户名';
COMMENT ON COLUMN `T_USER`.`password` IS '密码';
COMMENT ON COLUMN `T_USER`.`avator` IS '头像';
COMMENT ON COLUMN `T_USER`.`nickname` IS '昵称';

-- 密钥kuaidi100@2023，密码123456
INSERT INTO `T_USER` VALUES (1, '19372577', '4ab204881d06aee06d4fc4053dfe49ae', '', '吴彦祖');