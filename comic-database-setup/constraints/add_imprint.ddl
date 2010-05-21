alter table comic_collection.imprint
	add index (publisher_id),
	add constraint imprint__publisher__fk foreign key (publisher_id)
		references publisher (id),
	add index (create_user_id),
	add constraint imprint__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint imprint__user__fk2 foreign key (modify_user_id)
		references user (id);
