


CREATE TABLE IF NOT EXISTS `LOAN` (
	`id` int NOT NULL PRIMARY KEY,
	`customerId` int not null,
	`amount` float(2) not null,
	`duration` int not null
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;


