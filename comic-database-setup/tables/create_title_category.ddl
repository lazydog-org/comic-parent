create table comic_collection.title_category
	(category_id		int(10) unsigned not null,
	 title_id		int(10) unsigned not null,
	 primary key (category_id, title_id)
	) type = innodb;
