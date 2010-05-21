alter table comic_collection.imprint
	drop foreign key imprint__publisher__fk,
	drop foreign key imprint__user__fk1,
	drop foreign key imprint__user__fk2;
