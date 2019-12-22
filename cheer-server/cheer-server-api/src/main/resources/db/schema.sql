DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id`          INT(11)     NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `username`    VARCHAR(32) NOT NULL UNIQUE COMMENT '用户名',
  `password`    VARCHAR(32) NOT NULL COMMENT '加密后的密码',
  `email`       VARCHAR(32) NOT NULL UNIQUE COMMENT '邮箱',
  `create_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `update_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT ='用户表';