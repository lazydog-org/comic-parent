alter table comic.publisher
	add index (image_id),
	add constraint publisher__image__fk
                foreign key (image_id)
		references image (id);
