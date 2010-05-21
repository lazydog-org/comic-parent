create table comic_collection.image_type
	(id			int(10) unsigned not null auto_increment,
	 value			varchar(50) not null,
	 directory_path		varchar(100) not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id),
	 unique key (value),
	 unique key (directory_path)
	) type = innodb;
