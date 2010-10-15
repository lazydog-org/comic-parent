create table comic.have
	(id			int(10) unsigned not null auto_increment,
	 comic_id		int(10) unsigned not null,
	 comic_grade_id		int(10) unsigned not null,
	 location_id		int(10) unsigned not null,
	 purchase_price		float(9,2),
	 quantity		int(4) unsigned not null,
         uuid                   char(36) not null,
	 primary key (id)
	) type = innodb;
