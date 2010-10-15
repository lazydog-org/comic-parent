alter table comic.want
	add index (comic_id),
	add constraint want__comic__fk
                foreign key (comic_id)
		references comic (id);
