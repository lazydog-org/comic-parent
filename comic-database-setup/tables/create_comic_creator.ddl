create table comic_collection.comic_creator
	(comic_id		int(10) unsigned not null,
	 creator_id		int(10) unsigned not null,
	 primary key (comic_id, creator_id)
	) type = innodb;
