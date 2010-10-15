create table comic.image
	(id			int(10) unsigned not null auto_increment,
	 label			varchar(100),
	 file_name		varchar(100) not null,
	 image_type_id		int(10) unsigned not null,
	 primary key (id),
	 unique key (label),
	 unique key (file_name)
	) type = innodb;
