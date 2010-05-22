alter table comic_collection.creator
	add index (person_id),
	add constraint creator__person__fk
                foreign key (person_id)
		references person (id),
	add index (profession_id),
	add constraint creator__profession__fk
                foreign key (profession_id)
		references profession (id),
	add index (create_user_id),
	add constraint creator__application_user__fk1
                foreign key (create_user_id)
		references application_user (id),
	add index (modify_user_id),
	add constraint creator__application_user__fk2
                foreign key (modify_user_id)
		references application_user (id);