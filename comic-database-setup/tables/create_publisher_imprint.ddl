create table comic.publisher_imprint
	(imprint_id		int(10) unsigned not null,
         publisher_id		int(10) unsigned not null,
	 primary key (imprint_id, publisher_id)
	) type = innodb;