create table comic_collection.have
	(id			int(10) unsigned not null auto_increment,
	 comic_id		int(10) unsigned not null,
	 comic_grade_id		int(10) unsigned not null,
	 location_id		int(10) unsigned not null,
	 purchase_price		float(9,2),
	 quantity		int(4) unsigned not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id)
	) type = innodb;
