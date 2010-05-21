alter table comic_collection.have
	add index (comic_id),
	add constraint have__comic__fk foreign key (comic_id)
		references comic (id),
	add index (comic_grade_id),
	add constraint have__comic_grade__fk foreign key (comic_grade_id)
		references comic_grade (id),
	add index (location_id),
	add constraint have__location__fk foreign key (location_id)
		references location (id),
	add index (create_user_id),
	add constraint have__user__fk1 foreign key (create_user_id)
		references user (id),
	add index (modify_user_id),
	add constraint have__user__fk2 foreign key (modify_user_id)
		references user (id);
