alter table comic_collection.comic_creator
	add index (comic_id),
	add constraint comic_creator__comic__fk foreign key (comic_id)
		references comic (id),
	add index (creator_id),
	add constraint comic_creator__creator__fk foreign key (creator_id)
		references creator (id);
