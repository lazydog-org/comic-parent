create table comic_collection.creator
	(id			int(10) unsigned not null auto_increment,
	 person_id		int(10) unsigned not null,
	 profession_id		int(10) unsigned not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id)
	) type = innodb;
