alter table comic.comic_character
        drop foreign key comic_character__comic__fk,
	drop foreign key comic_character__character__fk;
