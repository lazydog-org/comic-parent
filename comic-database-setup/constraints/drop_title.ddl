alter table comic_collection.title
	drop foreign key title__image__fk,
	drop foreign key title__title_type__fk,
	drop foreign key title__application_user__fk1,
	drop foreign key title__application_user__fk2;
