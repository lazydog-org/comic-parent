alter table comic_collection.application_user_preference
	drop foreign key application_user_preference__comic_grade__fk,
	drop foreign key application_user_preference__comic_type__fk,
	drop foreign key application_user_preference__distribution__fk,
	drop foreign key application_user_preference__image_type__fk,
	drop foreign key application_user_preference__publisher__fk,
	drop foreign key application_user_preference__title_type__fk,
	drop foreign key application_user_preference__application_user__fk1,
	drop foreign key application_user_preference__application_user__fk2;
