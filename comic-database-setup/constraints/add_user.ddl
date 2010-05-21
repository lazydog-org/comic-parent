alter table comic_collection.user
	add index (create_user_id),
	add constraint user__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint user__user__fk2 foreign key (modify_user_id)
		references user (id);
