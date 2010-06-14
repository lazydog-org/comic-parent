create table comic_collection.comic
	(id			int(10) unsigned not null auto_increment,
	 number			int(10),
	 variant		varchar(1) not null,
	 print			tinyint(2) unsigned not null,
	 publish_date		date,
	 description		varchar(250),
	 cover_price		float(5,2),
	 comic_type_id		int(10) unsigned not null,
	 distribution_id	int(10) unsigned not null,
	 image_id		int(10) unsigned,
	 title_id		int(10) unsigned not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id)
	) type = innodb;
