alter table comic_collection.have
	drop foreign key have__comic__fk,
	drop foreign key have__comic_grade__fk,
	drop foreign key have__location__fk,
	drop foreign key have__application_user__fk1,
	drop foreign key have__application_user__fk2;
