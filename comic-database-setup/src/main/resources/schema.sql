use comic;

create table image_type(
    id                          int(10) unsigned not null auto_increment,
    value			varchar(50) not null,
    directory_path		varchar(100) not null,
    constraint pk_image_type_id
        primary key (id),
    constraint uk_image_type_value
        unique key (value),
    constraint uk_image_type_directory_path
        unique key (directory_path)
) engine = innodb;

create table image(
    id                          int(10) unsigned not null auto_increment,
    label			varchar(100),
    file_name                   varchar(100) not null,
    image_type_id		int(10) unsigned not null,
    constraint pk_image_id
        primary key (id),
    constraint uk_image_label
        unique key (label),
    constraint uk_image_file_name
        unique key (file_name),
    constraint fk_image_image_type_id
        foreign key (image_type_id)
        references image_type (id)
) engine = innodb;

create table title_type(
    id                          int(10) unsigned not null auto_increment,
    value			varchar(50) not null,
    constraint pk_title_type_id 
        primary key (id),
    unique key (value)
) engine = innodb;

create table title(
    id                          int(10) unsigned not null auto_increment,
    name			varchar(100) not null,
    volume			tinyint(2) unsigned not null,
    publish_start_date          date,
    publish_end_date            date,
    image_id                    int(10) unsigned,
    title_type_id		int(10) unsigned not null,
    constraint pk_title_id
        primary key (id),
    constraint fk_title_image_id
        foreign key (image_id)
        references image (id),
    constraint fk_title_title_type_id
        foreign key (title_type_id)
        references title_type (id)
) engine = innodb;

create table category(
    id                          int(10) unsigned not null auto_increment,
    name			varchar(50) not null,
    constraint pk_category_id 
        primary key (id),
    constraint uk_comic_name
        unique key (name)
) engine = innodb;

create table title_category(
    category_id                 int(10) unsigned not null,
    title_id                    int(10) unsigned not null,
    constraint pk_title_category_category_id_title_id 
        primary key (category_id, title_id),
    constraint fk_title_category_category_id
        foreign key (category_id)
        references category (id),
    constraint fk_title_category_title_id
        foreign key (title_id)
        references title (id)
) engine = innodb;

create table publisher(
    id                          int(10) unsigned not null auto_increment,
    name			varchar(50) not null,
    image_id                    int(10) unsigned,
    constraint pk_publisher_id
        primary key (id),
    constraint uk_publisher_name
        unique key (name),
    constraint fk_publisher_image_id
        foreign key (image_id)
        references image (id)
) engine = innodb;

create table imprint(
    id                          int(10) unsigned not null auto_increment,
    name			varchar(50) not null,
    image_id                    int(10) unsigned,
    constraint pk_imprint_id
        primary key (id),
    constraint uk_imprint_name
        unique key (name),
    constraint fk_imprint_image_id
        foreign key (image_id)
        references image (id)
) engine = innodb;

create table publisher_imprint(
    imprint_id                  int(10) unsigned not null,
    publisher_id		int(10) unsigned not null,
    constraint pk_publisher_imprint_imprint_id_publisher_id
        primary key (imprint_id, publisher_id),
    constraint fk_publisher_imprint_imprint_id
        foreign key (imprint_id)
        references imprint (id),
    constraint fk_publisher_imprint_publisher_id
        foreign key (publisher_id)
        references publisher (id)
) engine = innodb;

create table title_publisher(
    publisher_id		int(10) unsigned not null,
    title_id                    int(10) unsigned not null,
    constraint pk_title_publisher_publisher_id_title_id 
        primary key (publisher_id, title_id),
    constraint fk_title_publisher_publisher_id
        foreign key (publisher_id)
        references publisher (id),
    constraint fk_title_publisher_title_id
        foreign key (title_id)
        references title (id)
) engine = innodb;

create table comic_type(
    id                          int(10) unsigned not null auto_increment,
    value                       varchar(50) not null,
    constraint pk_comic_type_id
        primary key (id),
    constraint uk_comic_type_value
        unique key (value)
) engine = innodb;

create table distribution(
    id                          int(10) unsigned not null auto_increment,
    value                       varchar(50) not null,
    constraint pk_distribution_id
        primary key (id),
    constraint uk_distribution_value
        unique key (value)
) engine = innodb;

create table comic(
    id                          int(10) unsigned not null auto_increment,
    number			int(10),
    variant                     varchar(1) not null,
    print			tinyint(2) unsigned not null,
    publish_date		date,
    description                 varchar(250),
    cover_price                 float(5,2),
    comic_type_id		int(10) unsigned not null,
    distribution_id             int(10) unsigned not null,
    image_id                    int(10) unsigned,
    title_id                    int(10) unsigned not null,
    constraint pk_comic_id 
        primary key (id),
    constraint fk_comic_comic_type_id
        foreign key (comic_type_id)
        references comic_type (id),
    constraint fk_comic_distribution_id
        foreign key (distribution_id)
        references distribution (id),
    constraint fk_comic_image_id
        foreign key (image_id)
        references image (id),
    constraint fk_comic_title_id
        foreign key (title_id)
        references title (id)
) engine = innodb;

create table comic_character(
    id                          int(10) unsigned not null auto_increment,
    name			varchar(50) not null,
    image_id                    int(10) unsigned,
    constraint pk_comic_character_id 
        primary key (id),
    constraint uk_comic_character_name
        unique key (name),
    constraint fk_comic_character_image_id
        foreign key (image_id)
        references image (id)
) engine = innodb;

create table comic_character_appearance(
    comic_id                    int(10) unsigned not null,
    comic_character_id          int(10) unsigned not null,
    constraint pk_comic_character_appearance_comic_id_comic_character_id
        primary key (comic_id, comic_character_id),
    constraint fk_comic_character_appearance_comic_id
        foreign key (comic_id)
        references comic (id),
    constraint fk_comic_character_appearance_comic_character_id
        foreign key (comic_character_id)
        references comic_character (id)
) engine = innodb;

create table person(
    id                          int(10) unsigned not null auto_increment,
    last_name                   varchar(50) not null,
    first_name                  varchar(50) not null,
    constraint pk_person_id
        primary key (id),
    constraint uk_person_last_name_first_name
        unique key (last_name, first_name)
) engine = innodb;

create table profession(
    id                          int(10) unsigned not null auto_increment,
    value			varchar(50) not null,
    constraint pk_profession_id
        primary key (id),
    constraint uk_profession_value
        unique key (value)
) engine = innodb;

create table creator(
    id                          int(10) unsigned not null auto_increment,
    person_id                   int(10) unsigned not null,
    profession_id		int(10) unsigned not null,
    constraint pk_creator_id
        primary key (id),
    constraint fk_creator_person_id
        foreign key (person_id)
        references person (id),
    constraint fk_creator_profession_id
        foreign key (profession_id)
        references profession (id)
) engine = innodb;

create table comic_creator(
    comic_id                    int(10) unsigned not null,
    creator_id                  int(10) unsigned not null,
    constraint pk_comic_creator_comic_id_creator_id
        primary key (comic_id, creator_id),
    constraint fk_comic_creator_comic_id
        foreign key (comic_id)
        references comic (id),
    constraint fk_comic_creator_creator_id
        foreign key (creator_id)
        references creator (id)
) engine = innodb;

create table trait(
    id                          int(10) unsigned not null auto_increment,
    value			varchar(50) not null,
    constraint pk_trait_id
        primary key (id),
    constraint uk_trait_value
        unique key (value)
) engine = innodb;

create table comic_trait(
    comic_id                    int(10) unsigned not null,
    trait_id                    int(10) unsigned not null,
    constraint pk_comic_trait_comic_id_trait_id
        primary key (comic_id, trait_id),
    constraint fk_comic_trait_comic_id
        foreign key (comic_id)
        references comic (id),
    constraint fk_comic_trait_trait_id
        foreign key (trait_id)
        references trait (id)
) engine = innodb;

create table comic_grade(
    id                          int(10) unsigned not null auto_increment,
    code			varchar(5) not null,
    scale			float(3,1) not null,
    name			varchar(20) not null,
    constraint pk_comic_grade_id
        primary key (id)
) engine = innodb;

create table location(
    id                          int(10) unsigned not null auto_increment,
    name			varchar(50) not null,
    uuid                        char(36) not null,
    constraint pk_location_id
        primary key (id)
) engine = innodb;

create table have(
    id                          int(10) unsigned not null auto_increment,
    comic_id                    int(10) unsigned not null,
    comic_grade_id		int(10) unsigned not null,
    location_id                 int(10) unsigned not null,
    purchase_price		float(9,2),
    quantity                    int(4) unsigned not null,
    uuid                        char(36) not null,
    constraint pk_have_id
        primary key (id),
    constraint fk_have_comic_id
        foreign key (comic_id)
        references comic (id),
    constraint fk_have_comic_grade_id
        foreign key (comic_grade_id)
        references comic_grade (id),
    constraint fk_have_location_id
        foreign key (location_id)
        references location (id)
) engine = innodb;

create table want(
    id                          int(10) unsigned not null auto_increment,
    comic_id                    int(10) unsigned not null,
    uuid                        char(36) not null,
    constraint pk_want_id
        primary key (id),
    constraint want_comic_id
        foreign key (comic_id)
        references comic (id)
) engine = innodb;

create table user_preference(
    id                          int(10) unsigned not null auto_increment,
    minimum_publish_date	date not null,
    comic_grade_id		int(10) unsigned not null,
    comic_type_id		int(10) unsigned not null,
    distribution_id             int(10) unsigned not null,
    image_type_id		int(10) unsigned not null,
    publisher_id		int(10) unsigned not null,
    title_type_id		int(10) unsigned not null,
    uuid                        char(36) not null,
    constraint pk_user_preference_id
        primary key (id),
    constraint fk_user_preference_comic_grade_id
        foreign key (comic_grade_id)
        references comic_grade (id),
    constraint fk_user_preference_comic_type_id
        foreign key (comic_type_id)
        references comic_type (id),
    constraint fk_user_preference_distribution_id
        foreign key (distribution_id)
        references distribution (id),
    constraint fk_user_preference_image_type_id
        foreign key (image_type_id)
        references image_type (id),
    constraint fk_user_preference_publisher_id
        foreign key (publisher_id)
        references publisher (id),
    constraint fk_user_preference_title_type_id
        foreign key (title_type_id)
        references title_type (id)
) engine = innodb;
