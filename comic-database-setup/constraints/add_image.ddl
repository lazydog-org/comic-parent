alter table comic_collection.image
	add index (image_type_id),
	add constraint image__image_type__fk foreign key (image_type_id)
		references image_type (id),
	add index (create_user_id),
	add constraint image__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint image__user__fk2 foreign key (modify_user_id)
		references user (id);
