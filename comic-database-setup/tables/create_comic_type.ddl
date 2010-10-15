create table comic.comic_type
	(id			int(10) unsigned not null auto_increment,
	 value			varchar(50) not null,
	 primary key (id),
	 unique key (value)
	) type = innodb;
