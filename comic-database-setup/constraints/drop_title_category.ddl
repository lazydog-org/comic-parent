alter table comic_collection.title_category
	drop foreign key title_category__category__fk,
	drop foreign key title_category__title__fk;
