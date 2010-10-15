alter table comic.image
	add index (image_type_id),
	add constraint image__image_type__fk
                foreign key (image_type_id)
		references image_type (id);
