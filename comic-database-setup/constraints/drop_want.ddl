alter table comic_collection.want
	drop foreign key want__comic__fk,
	drop foreign key want__application_user__fk1,
	drop foreign key want__application_user__fk2;
