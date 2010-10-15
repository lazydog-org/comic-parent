create table comic.title_publisher
	(publisher_id		int(10) unsigned not null,
	 title_id		int(10) unsigned not null,
	 primary key (publisher_id, title_id)
	) type = innodb;
