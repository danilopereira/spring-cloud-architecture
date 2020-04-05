


CREATE TABLE IF NOT EXISTS `CUSTOMER` (
	`id` int NOT NULL PRIMARY KEY,
	`userId` varchar(100) not null,
	`firstName` varchar(100) not null,
	`lastName` varchar(100) not null,
	`email` varchar(100) not null,
	`phone` varchar(100) not null
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;


