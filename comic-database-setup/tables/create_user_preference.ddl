create table comic_collection.user_preference
	(id			int(10) unsigned not null auto_increment,
	 minimum_publish_date	date not null,
	 comic_grade_id		int(10) unsigned not null,
	 comic_type_id		int(10) unsigned not null,
	 distribution_id	int(10) unsigned not null,
	 image_type_id		int(10) unsigned not null,
	 publisher_id		int(10) unsigned not null,
	 title_type_id		int(10) unsigned not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id)
	) type = innodb;
