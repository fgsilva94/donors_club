create database donors_club;

use donors_club;

create table users (usr_id int not null auto_increment,
						usr_name varchar(60) not null,
						usr_email varchar(60) not null,
						usr_password varchar(30) not null,
						usr_city_id int not null,
						usr_active boolean default true,
						primary key (usr_id));

create table adposts (ad_id int not null auto_increment,
						ad_title varchar(30) not null,
						ad_description varchar(150) not null,
						ad_subcategory_id int not null,
						ad_pub_date datetime not null,
						ad_active boolean default true,
						ad_owner_id int not null,
						primary key (ad_id));

create table chats (chat_id int not null auto_increment,
						chat_ad_id int not null,
						chat_usr_id int not null,
						chat_active boolean default true,
						chat_date date not null,
						primary key (chat_id));

create table messages (msg_id int not null auto_increment,
						msg_text text not null,
						msg_time datetime not null,
						msg_chat_id int not null,
						msg_sender_id int not null,
						primary key (msg_id));

create table wishlist (wl_id int not null auto_increment,
						wl_usr_id int not null,
						wl_ad_id int not null,
						wl_date datetime not null,
						wl_active boolean default true,
						primary key (wl_id));

create table categories (cat_id int not null auto_increment,
						cat_name varchar(30),
						primary key (cat_id));

create table subcategories (subc_id int not null auto_increment,
						subc_name varchar(30) not null,
						subc_cat_id int not null,
						primary key (subc_id));

create table districts (dis_id int not null auto_increment,
						dis_name varchar(30) not null,
                        primary key (dis_id));

create table cities (city_id int not null auto_increment,
						city_name varchar(30) not null,
                        city_dis_id int not null,
                        primary key (city_id));

-- foreign keys
alter table users add constraint users_fk_city
            foreign key (usr_city_id) references cities(city_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table adposts add constraint ads_fk_users
            foreign key (ad_owner_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table adposts add constraint ads_fk_subcategories
            foreign key (ad_subcategory_id) references subcategories(subc_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table chats add constraint chats_fk_ads
            foreign key (chat_ad_id) references adposts(ad_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table chats add constraint chats_fk_users
            foreign key (chat_usr_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table wishlist add constraint wishlist_fk_users
            foreign key (wl_usr_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table wishlist add constraint wishlist_fk_ads
            foreign key (wl_ad_id) references adposts(ad_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table messages add constraint messages_fk_users
            foreign key (msg_sender_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table messages add constraint messages_fk_chats
            foreign key (msg_chat_id) references chats(chat_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table subcategories add constraint subcategories_fk_categories
            foreign key (subc_cat_id) references categories(cat_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table cities add constraint cities_fk_districts
            foreign key (city_dis_id) references districts(dis_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

-- Uniques
alter table users add constraint uc_users unique (usr_email);

alter table chats add constraint uc_chats unique (chat_ad_id, chat_usr_id);