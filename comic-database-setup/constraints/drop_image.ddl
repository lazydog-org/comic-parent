alter table comic_collection.image
	drop foreign key image__image_type__fk,
	drop foreign key image__user__fk1,
	drop foreign key image__user__fk2;
