alter table comic.character
	add index (image_id),
	add constraint character__image__fk
                foreign key (image_id)
		references image (id);
