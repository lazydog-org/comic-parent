alter table comic_collection.comic_character
	add index (character_id),
	add constraint comic_character__character__fk foreign key (character_id)
		references `character` (id),
	add index (comic_id),
	add constraint comic_character__comic__fk foreign key (comic_id)
		references comic (id);
