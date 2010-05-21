alter table comic_collection.title_publisher
	add index (publisher_id),
	add constraint title_publisher__publisher__fk foreign key (publisher_id)
		references publisher (id),
	add index (title_id),
	add constraint title_publisher__title__fk foreign key (title_id)
		references title (id);
