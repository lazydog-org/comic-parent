alter table comic_collection.title
	add index (image_id),
	add constraint title__image__fk foreign key (image_id)
		references image (id),
	add index (title_type_id),
	add constraint title__title_type__fk foreign key (title_type_id)
		references title_type (id),
	add index (create_user_id),
	add constraint title__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint title__user__fk2 foreign key (modify_user_id)
		references user (id);
