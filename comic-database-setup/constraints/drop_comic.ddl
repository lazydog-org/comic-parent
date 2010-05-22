alter table comic_collection.comic
	drop foreign key comic__comic_type__fk,
	drop foreign key comic__distribution__fk,
	drop foreign key comic__image__fk,
	drop foreign key comic__title__fk,
	drop foreign key comic__application_user__fk1,
	drop foreign key comic__application_user__fk2;
