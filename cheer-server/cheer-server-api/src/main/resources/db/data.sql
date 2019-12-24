INSERT INTO `user` (`id`, `username`, `password`, `email`) VALUES (1, 'user1', '$2a$10$OXDE4NvatsbLm/9crn17Z.hU/MBG/CjNj8lWR.zWn.UOsQhQ50w92', 'user1@gmail.com');
INSERT INTO `user` (`id`, `username`, `password`, `email`) VALUES (2, 'user2', '$2a$10$OXDE4NvatsbLm/9crn17Z.hU/MBG/CjNj8lWR.zWn.UOsQhQ50w92', 'user2@gmail.com');

INSERT INTO `role` (`id`, `name`) VALUES (1, 'USER');
INSERT INTO `role` (`id`, `name`) VALUES (2, 'ADMIN');

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 2);

INSERT INTO `permission` (`id`, `name`, `url`) VALUES (1, '用户接口', '/user');

INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES (2, 1)