drop database if exists collections_test;
create database collections_test;
use collections_test;

create table app_user (
    app_user_id int primary key auto_increment,
    username varchar(50) not null unique,
    password_hash varchar(2048) not null,
    enabled bit not null default(1)
);

create table app_role (
    app_role_id int primary key auto_increment,
    `name` varchar(50) not null unique
);

create table app_user_role (
    app_user_id int not null,
    app_role_id int not null,
    constraint pk_app_user_role
        primary key (app_user_id, app_role_id),
    constraint fk_app_user_role_user_id
        foreign key (app_user_id)
        references app_user(app_user_id),
    constraint fk_app_user_role_role_id
        foreign key (app_role_id)
        references app_role(app_role_id)
);
    
create table collection (
    collection_id int primary key auto_increment,
    `name` varchar(50) not null,
    app_user_id int not null,
    `value` DECIMAL(19,4) not null,
    constraint fk_app_user_id
        foreign key(app_user_id)
        references app_user(app_user_id)
);

create table item (
	item_id int primary key auto_increment,
    `name` varchar(50) not null,
    `value` DECIMAL(19,4) not null,
    grade ENUM('UNGRADED', 'SEVEN', 'EIGHT', 'NINE', 'NINEFIVE', 'TEN') not null
);

create table collection_item (
	collection_id int not null,
    item_id int not null,
    is_sold boolean not null,
    listed_price DECIMAL(19,4) not null,
    constraint pk_collection_item
		primary key (collection_id, item_id),
        constraint fk_collection_id
			foreign key (collection_id)
            references collection(collection_id),
		constraint fk_item_id
			foreign key (item_id)
            references item(item_id)
);

