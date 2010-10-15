create table comic.imprint
	(id			int(10) unsigned not null auto_increment,
	 name			varchar(50) not null,
	 image_id		int(10) unsigned,
	 primary key (id),
	 unique key (name)
	) type = innodb;
