create table comic_collection.comic_trait
	(comic_id		int(10) unsigned not null,
	 trait_id		int(10) unsigned not null,
	 primary key (comic_id, trait_id)
	) type = innodb;
