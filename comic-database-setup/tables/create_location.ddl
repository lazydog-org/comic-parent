create table comic.location
	(id			int(10) unsigned not null auto_increment,
	 name			varchar(50) not null,
         uuid                   char(36) not null,
	 primary key (id)
	) type = innodb;
