alter table comic_collection.comic_creator
	drop foreign key comic_creator__comic__fk,
	drop foreign key comic_creator__creator__fk;