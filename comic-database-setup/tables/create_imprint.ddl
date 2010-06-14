create table comic_collection.imprint
	(id			int(10) unsigned not null auto_increment,
	 name			varchar(50) not null,
	 publisher_id		int(10) unsigned not null,
	 image_id		int(10) unsigned,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id),
	 unique key (name)
	) type = innodb;
