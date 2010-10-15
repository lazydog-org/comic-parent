create table comic.user_preference
	(id			int(10) unsigned not null auto_increment,
	 minimum_publish_date	date not null,
	 comic_grade_id		int(10) unsigned not null,
	 comic_type_id		int(10) unsigned not null,
	 distribution_id	int(10) unsigned not null,
	 image_type_id		int(10) unsigned not null,
	 publisher_id		int(10) unsigned not null,
	 title_type_id		int(10) unsigned not null,
         uuid                   char(36) not null,
	 primary key (id)
	) type = innodb;
