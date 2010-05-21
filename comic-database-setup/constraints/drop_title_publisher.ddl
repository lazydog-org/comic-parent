alter table comic_collection.title_publisher
	drop foreign key title_publisher__publisher__fk,
	drop foreign key title_publisher__title__fk;
