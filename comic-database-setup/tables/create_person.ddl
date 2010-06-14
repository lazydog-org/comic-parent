create table comic_collection.person
	(id			int(10) unsigned not null auto_increment,
	 last_name		varchar(50) not null,
	 first_name		varchar(50) not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id),
	 unique key (last_name, first_name)
	) type = innodb;
