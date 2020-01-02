INSERT INTO `user` (`id`, `username`, `password`, `email`) VALUES (1, 'picongzhi', '$2a$10$OXDE4NvatsbLm/9crn17Z.hU/MBG/CjNj8lWR.zWn.UOsQhQ50w92', 'picongzhi@gmail.com');
INSERT INTO `user` (`id`, `username`, `password`, `email`) VALUES (2, 'unusualmay', '$2a$10$OXDE4NvatsbLm/9crn17Z.hU/MBG/CjNj8lWR.zWn.UOsQhQ50w92', 'unusualmay@gmail.com');
INSERT INTO `user` (`id`, `username`, `password`, `email`) VALUES (3, 'user', '$2a$10$OXDE4NvatsbLm/9crn17Z.hU/MBG/CjNj8lWR.zWn.UOsQhQ50w92', 'user@gmail.com');

INSERT INTO `role` (`id`, `name`) VALUES (1, 'ADMIN');
INSERT INTO `role` (`id`, `name`) VALUES (2, 'USER');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (3, 2);

INSERT INTO `permission` (`id`, `name`, `url`) VALUES (1, '管理员权限', '/**');
INSERT INTO `permission` (`id`, `name`, `url`) VALUES (2, '普通用户权限', '/user/**');

INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES (2, 2);

INSERT INTO `category` (`name`, `description`, `user_id`) VALUES ('测试分类1', '测试分类1', 1);
INSERT INTO `category` (`name`, `description`, `user_id`) VALUES ('测试分类2', '测试分类2', 1);

INSERT INTO `blog` (`title`, `user_id`, `category_id`) VALUES ('测试博客', 1, 1);
INSERT INTO `blog` (`title`, `user_id`, `category_id`) VALUES ('测试博客', 1, 1);
INSERT INTO `blog` (`title`, `user_id`, `category_id`) VALUES ('测试博客', 1, 2);
INSERT INTO `blog` (`title`, `is_public`, `user_id`, `category_id`) VALUES ('测试博客', 0, 1, 1);
INSERT INTO `blog` (`title`, `is_public`, `user_id`, `category_id`) VALUES ('测试博客', 0, 1, 1);