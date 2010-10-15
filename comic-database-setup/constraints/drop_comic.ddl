alter table comic.comic
	drop foreign key comic__comic_type__fk,
	drop foreign key comic__distribution__fk,
	drop foreign key comic__image__fk,
	drop foreign key comic__title__fk;
