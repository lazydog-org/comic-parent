create table comic.category
	(id			int(10) unsigned not null auto_increment,
	 name			varchar(50) not null,
	 primary key (id),
	 unique key (name)
	) type = innodb;
