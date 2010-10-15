alter table comic.user_preference
	drop foreign key user_preference__comic_grade__fk,
	drop foreign key user_preference__comic_type__fk,
	drop foreign key user_preference__distribution__fk,
	drop foreign key user_preference__image_type__fk,
	drop foreign key user_preference__publisher__fk,
	drop foreign key user_preference__title_type__fk;
