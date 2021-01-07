create database donors_club;

use donors_club;

create table users (usr_id int not null auto_increment,
					 usr_name varchar(60) not null,
					 usr_email varchar(60) not null,
					 usr_phone_number varchar(20) not null,
					 usr_password varchar(30) not null,
					 usr_address varchar(60) not null,
					 usr_active boolean default true,
					 primary key (usr_id));

create table ads (ad_id int not null auto_increment,
					 ad_title varchar(30) not null,
					 ad_description varchar(150) not null,
					 ad_category varchar(20) not null,
					 ad_publication_date datetime not null,
					 ad_active boolean default true,
                     ad_owner_id int not null,
					 primary key (ad_id));

create table chats (chat_id int not null auto_increment,
					 chat_customer_id int not null,
					 chat_ad_owner_id int not null,
                     chat_ad_id int not null,
					 chat_active boolean default true,
					 primary key (chat_id));

create table messages (msg_id int not null auto_increment,
					 msg_content varchar(100) not null,
					 msg_date datetime not null,
                     msg_chat_id int not null,
                     msg_sender_id int not null,
					 primary key (msg_id));

-- foreign keys

alter table ads add constraint ads_fk_users
            foreign key (ad_owner_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table chats add constraint chats_fk_ads
            foreign key (chat_ad_id) references ads(ad_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table chats add constraint chats_fk_users_owner
            foreign key (chat_ad_owner_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table chats add constraint chats_fk_users_customer
            foreign key (chat_customer_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table messages add constraint messages_fk_users
            foreign key (msg_sender_id) references users(usr_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;

alter table messages add constraint messages_fk_chats
            foreign key (msg_chat_id) references chats(chat_id) 
			ON DELETE NO ACTION ON UPDATE NO ACTION;
