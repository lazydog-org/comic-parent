alter table comic_collection.publisher
	drop foreign key publisher__image__fk,
	drop foreign key publisher__application_user__fk1,
	drop foreign key publisher__application_user__fk2;
