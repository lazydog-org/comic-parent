create table comic.person
	(id			int(10) unsigned not null auto_increment,
	 last_name		varchar(50) not null,
	 first_name		varchar(50) not null,
	 primary key (id),
	 unique key (last_name, first_name)
	) type = innodb;
