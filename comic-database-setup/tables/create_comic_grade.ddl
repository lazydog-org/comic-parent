create table comic.comic_grade
	(id			int(10) unsigned not null auto_increment,
	 code			varchar(5) not null,
	 scale			float(3,1) not null,
	 name			varchar(20) not null,
	 primary key (id)
	) type = innodb;
