create table comic.title
	(id			int(10) unsigned not null auto_increment,
	 name			varchar(100) not null,
	 volume			tinyint(2) unsigned not null,
	 publish_start_date	date,
	 publish_end_date	date,
	 image_id		int(10) unsigned,
	 title_type_id		int(10) unsigned not null,
	 primary key (id)
	) type = innodb;
