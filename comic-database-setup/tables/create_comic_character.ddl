create table comic.comic_character
	(comic_id		int(10) unsigned not null,
         comic_character_id	int(10) unsigned not null,
	 primary key (comic_id, comic_character_id)
	) type = innodb;
