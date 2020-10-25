DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `permission`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `rolename`;

CREATE TABLE role(
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT, 
  `role_name` varchar(50) NOT NULL,  
  PRIMARY KEY (`role_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


CREATE TABLE user(
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,   
  `role_id` int(11) unsigned NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`),  
FOREIGN KEY(`role_id`) REFERENCES `role` (`role_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;


CREATE TABLE `permission` (
`permission_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
`role_id` int(11) unsigned NOT NULL,
`permission_name` varchar(50) NOT NULL,
PRIMARY KEY(`permission_id`),
KEY `role_id` (`role_id`),
CONSTRAINT `items_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 
CREATE TABLE `rolename` (
`role_name_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
`role_name` varchar(50) NOT NULL,
PRIMARY KEY(`role_name_id`)
);

INSERT INTO role (role_id, role_name) VALUES
(1, 'ADMIN'),
(2, 'USER');

INSERT INTO user (user_id, role_id, username, password) VALUES 
(1,1, 'Talar', 'root'),
(2,2, 'Paul', 'test');

INSERT INTO permission(permission_id, role_id, permission_name) VALUES
(1,1,'Create user'),
(2,1,'Update user'),
(3,1,'Delete user'),
(4,1,'List of users'),
(5,2,'Create user');

INSERT INTO rolename(role_name_id, role_name) VALUES
(1,'ADMIN'),
(2,'USER');