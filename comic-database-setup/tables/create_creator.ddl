create table comic.creator
	(id			int(10) unsigned not null auto_increment,
	 person_id		int(10) unsigned not null,
	 profession_id		int(10) unsigned not null,
	 primary key (id)
	) type = innodb;
