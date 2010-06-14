alter table comic_collection.image
	drop foreign key image__image_type__fk,
	drop foreign key image__application_user__fk1,
	drop foreign key image__application_user__fk2;
