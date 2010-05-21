create table comic_collection.comic_grade
	(id			int(10) unsigned not null auto_increment,
	 code			varchar(5) not null,
	 scale			float(3,1) not null,
	 name			varchar(20) not null,
	 create_time		datetime not null,
	 create_user_id		int(10) unsigned not null,
	 modify_time		datetime,
	 modify_user_id		int(10) unsigned,
	 primary key (id)
	) type = innodb;
