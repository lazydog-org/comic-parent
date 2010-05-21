alter table comic_collection.user_preference
	add index (comic_grade_id),
	add constraint user_preference__comic_grade__fk foreign key (comic_grade_id)
		references comic_grade (id),
	add index (comic_type_id),
	add constraint user_preference__comic_type__fk foreign key (comic_type_id)
		references comic_type (id),
	add index (distribution_id),
	add constraint user_preference__distribution__fk foreign key (distribution_id)
		references distribution (id),
	add index (image_type_id),
	add constraint user_preference__image_type__fk foreign key (image_type_id)
		references image_type (id),
	add index (publisher_id),
	add constraint user_preference__publisher__fk foreign key (publisher_id)
		references publisher (id),
	add index (title_type_id),
	add constraint user_preference__title_type__fk foreign key (title_type_id)
		references title_type (id),
	add index (create_user_id),
	add constraint user_preference__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint user_preference__user__fk2 foreign key (modify_user_id)
		references user (id);
