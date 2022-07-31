BEGIN;

create table if not exists users (usr_id serial not null,
						usr_name varchar(60) not null,
						usr_email varchar(60) not null,
						usr_password varchar(30) not null,
						usr_city_id int not null,
						usr_active boolean default true,
						primary key (usr_id));

create table if not exists adposts (ad_id serial not null,
						ad_title varchar(30) not null,
						ad_description varchar(150) not null,
						ad_subcategory_id int not null,
						ad_pub_date timestamp without time zone not null,
						ad_active boolean default true,
						ad_owner_id int not null,
						primary key (ad_id));

create table if not exists chats (chat_id serial not null,
						chat_ad_id int not null,
						chat_usr_id int not null,
						chat_active boolean default true,
						chat_date date not null,
						primary key (chat_id));

create table if not exists messages (msg_id serial not null,
						msg_text text not null,
						msg_time timestamp without time zone not null,
						msg_chat_id int not null,
						msg_sender_id int not null,
						primary key (msg_id));

create table if not exists wishlist (wl_id serial not null,
						wl_usr_id int not null,
						wl_ad_id int not null,
						wl_date timestamp without time zone not null,
						wl_active boolean default true,
						primary key (wl_id));

create table if not exists categories (cat_id serial not null,
						cat_name varchar(30),
						primary key (cat_id));

create table if not exists subcategories (subc_id serial not null,
						subc_name varchar(30) not null,
						subc_cat_id int not null,
						primary key (subc_id));

create table if not exists districts (dis_id serial not null,
						dis_name varchar(30) not null,
                        primary key (dis_id));

create table if not exists cities (city_id serial not null,
						city_name varchar(30) not null,
                        city_dis_id int not null,
                        primary key (city_id));

-- foreign keys
alter table if exists users
      add foreign key (usr_city_id) references cities(city_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists adposts
      add foreign key (ad_owner_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists adposts
      add foreign key (ad_subcategory_id) references subcategories(subc_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists chats
      add foreign key (chat_ad_id) references adposts(ad_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists chats
      add foreign key (chat_usr_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists wishlist
      add foreign key (wl_usr_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists wishlist
      add foreign key (wl_ad_id) references adposts(ad_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists messages
      add foreign key (msg_sender_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists messages
      add foreign key (msg_chat_id) references chats(chat_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists subcategories
      add foreign key (subc_cat_id) references categories(cat_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table if exists cities
      add foreign key (city_dis_id) references districts(dis_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Uniques
alter table if exists users add constraint uc_users unique (usr_email);

alter table if exists chats add constraint uc_chats unique (chat_ad_id, chat_usr_id);

-- Index
create index i_usr_email on users(usr_email, usr_password);

create index i_ad_title on adposts(ad_title);

END;

-- Stored Procedures
CREATE OR REPLACE PROCEDURE disableUser(id int)
language plpgsql as
$$
BEGIN

UPDATE users 
SET usr_active = 0
WHERE usr_id = id;

UPDATE adposts 
SET ad_active = 0
WHERE ad_owner_id = id;

UPDATE wishlist 
SET wl_active = 0
WHERE wl_usr_id = id;

UPDATE chats c
SET c.chat_active = 0
FROM adposts ad
INNER JOIN users ow ON ad.ad_owner_id = ow.usr_id
WHERE c.chat_ad_id = ad.ad_id AND (c.chat_usr_id = id OR ow.usr_id = id);

END
$$;