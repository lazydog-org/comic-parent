alter table comic.comic_trait
	drop foreign key comic_trait__comic__fk,
	drop foreign key comic_trait__trait__fk;
