alter table comic.comic_character
	add index (image_id),
	add constraint comic_character__image__fk
                foreign key (image_id)
		references image (id);
