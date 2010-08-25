alter table comic_collection.publisher_imprint
	drop foreign key publisher_imprint__imprint__fk,
	drop foreign key publisher_imprint__publisher__fk;
