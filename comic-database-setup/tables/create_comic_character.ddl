create table comic_collection.comic_character
	(character_id		int(10) unsigned not null,
	 comic_id		int(10) unsigned not null,
	 primary key (character_id, comic_id)
	) type = innodb;
