alter table comic_collection.character
	drop foreign key character__image__fk,
	drop foreign key character__user__fk1,
	drop foreign key character__user__fk2;
