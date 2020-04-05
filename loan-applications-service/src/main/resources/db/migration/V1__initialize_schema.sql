


CREATE TABLE IF NOT EXISTS `LOAN` (
	`id` varchar(100) NOT NULL PRIMARY KEY,
	`customerId` varchar(100) not null,
	`amount` double not null,
	`duration` int not null
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;


