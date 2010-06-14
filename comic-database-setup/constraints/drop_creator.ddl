alter table comic_collection.creator
	drop foreign key creator__person__fk,
	drop foreign key creator__profession__fk,
	drop foreign key creator__application_user__fk1,
	drop foreign key creator__application_user__fk2;
