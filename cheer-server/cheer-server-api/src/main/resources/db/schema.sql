DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
  `id`          BIGINT(64)  NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `username`    VARCHAR(64) NOT NULL UNIQUE COMMENT '用户名',
  `password`    VARCHAR(64) NOT NULL COMMENT '加密后的密码',
  `email`       VARCHAR(64) NOT NULL UNIQUE COMMENT '邮箱',
  `status`      INT(2)      NOT NULL DEFAULT '1' COMMENT '状态，启用-1，禁用-0',
  `create_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `update_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '上次更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT = '用户表';

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
  `id`          BIGINT(64)  NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `name`        VARCHAR(64) NOT NULL COMMENT '角色名',
  `description` VARCHAR(256)         DEFAULT NULL COMMENT '描述',
  `create_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
  `update_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '更新时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT = '角色表';

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
  `id`         BIGINT(64)  NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
  `name`       VARCHAR(64) NOT NULL COMMENT '权限名',
  `url`        VARCHAR(1024) DEFAULT NULL COMMENT '接口地址',
  `permission` VARCHAR(64)   DEFAULT NULL COMMENT '权限表达式',
  `method`     VARCHAR(64)   DEFAULT NULL COMMENT '方法'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT = '权限表';

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
  `role_id`       BIGINT(64) NOT NULL COMMENT '角色主键',
  `permission_id` BIGINT(64) NOT NULL COMMENT '权限主键',
  PRIMARY KEY (`role_id`, `permission_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '角色权限关系表';

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
  `user_id` BIGINT(64) NOT NULL COMMENT '用户主键',
  `role_id` BIGINT(64) NOT NULL COMMENT '角色主键',
  PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '用户角色关系表';