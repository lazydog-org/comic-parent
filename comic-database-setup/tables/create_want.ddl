create table comic.want
	(id			int(10) unsigned not null auto_increment,
	 comic_id		int(10) unsigned not null,
         uuid                   char(36) not null,
	 primary key (id)
	) type = innodb;
