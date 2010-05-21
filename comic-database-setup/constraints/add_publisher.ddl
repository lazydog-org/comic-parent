alter table comic_collection.publisher
	add index (image_id),
	add constraint publisher__image__fk foreign key (image_id)
		references image (id),
	add index (create_user_id),
	add constraint publisher__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint publisher__user__fk2 foreign key (modify_user_id)
		references user (id);
