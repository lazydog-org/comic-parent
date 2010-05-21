alter table comic_collection.want
	add index (comic_id),
	add constraint want__comic__fk foreign key (comic_id)
		references comic (id),
	add index (create_user_id),
	add constraint want__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint want__user__fk2 foreign key (modify_user_id)
		references user (id);
