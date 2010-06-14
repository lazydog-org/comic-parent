alter table comic_collection.imprint
	drop foreign key imprint__publisher__fk,
	drop foreign key imprint__application_user__fk1,
	drop foreign key imprint__application_user__fk2;
