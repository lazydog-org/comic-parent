alter table comic_collection.comic
	add index (comic_type_id),
	add constraint comic__comic_type__fk foreign key (comic_type_id)
		references comic_type (id),
	add index (distribution_id),
	add constraint comic__distribution__fk foreign key (distribution_id)
		references distribution (id),
	add index (image_id),
	add constraint comic__image__fk foreign key (image_id)
		references image (id),
	add index (title_id),
	add constraint comic__title__fk foreign key (title_id)
		references title (id),
	add index (create_user_id),
	add constraint comic__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint comic__user__fk2 foreign key (modify_user_id)
		references user (id);
