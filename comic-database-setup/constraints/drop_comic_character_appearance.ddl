alter table comic_collection.comic_character_appearance
        drop foreign key comic_character_appearance__comic__fk,
	drop foreign key comic_character_appearance__comic_character__fk;
