alter table comic_collection.title_category
	add index (category_id),
	add constraint title_category__category__fk foreign key (category_id)
		references category (id),
	add index (title_id),
	add constraint title_category__title__fk foreign key (title_id)
		references title (id);
