create table comic_collection.image
	(id			int(10) unsigned not null auto_increment,
	 label			varchar(100),
	 file_name		varchar(100) not null,
	 image_type_id		int(10) unsigned not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id),
	 unique key (label),
	 unique key (file_name)
	) type = innodb;
