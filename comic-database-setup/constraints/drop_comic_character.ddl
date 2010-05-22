alter table comic_collection.comic_character
	drop foreign key comic_character__image__fk,
	drop foreign key comic_character__application_user__fk1,
	drop foreign key comic_character__application_user__fk2;
