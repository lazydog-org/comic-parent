create table comic_collection.location
	(id			int(10) unsigned not null auto_increment,
	 name			varchar(50) not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id)
	) type = innodb;
