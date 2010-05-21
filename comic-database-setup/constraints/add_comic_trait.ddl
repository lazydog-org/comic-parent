alter table comic_collection.comic_trait
	add index (comic_id),
	add constraint comic_trait__comic__fk foreign key (comic_id)
		references comic (id),
	add index (trait_id),
	add constraint comic_trait__trait__fk foreign key (trait_id)
		references trait (id);
