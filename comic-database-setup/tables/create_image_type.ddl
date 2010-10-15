create table comic.image_type
	(id			int(10) unsigned not null auto_increment,
	 value			varchar(50) not null,
	 directory_path		varchar(100) not null,
	 primary key (id),
	 unique key (value),
	 unique key (directory_path)
	) type = innodb;
