-- Obtem todos as conversar de um usuario dado o seu id
select c.*
from chats c
inner join users u on c.chat_usr_id = u.usr_id
inner join adposts ad on c.chat_ad_id = ad.ad_owner_id
where u.usr_id = 4;

-- Obtem todas as mensagens de um anuncio, considerando o usuario cliente
select m.*
from messages m
inner join chats c on m.msg_chat_id = c.chat_id
where c.chat_ad_id = 1 and c.chat_usr_id = 7;

-- Obtem todos os anuncios que estao em lisboa, ou qualquer outro Concelho trocando o conteudo do operador LIKE
select ad.*
from adposts ad
inner join users u on ad.ad_owner_id = u.usr_id
inner join cities c on u.usr_city_id = c.city_id
where c.city_name like 'lisbon%';

-- Obtem todos os anuncios que estao em lisboa, ou qualquer outro Concelho trocando o conteudo do operador LIKE,
-- e tambem os anuncios que pertencem a uma categoria ou subcategoria
select ad.*
from adposts ad
inner join users u on ad.ad_owner_id = u.usr_id
inner join cities c on u.usr_city_id = c.city_id
inner join subcategories s on ad.ad_subcategory_id = s.subc_id
inner join categories cat on s.subc_cat_id = cat.cat_id
where c.city_name like 'lisbon%' and (s.subc_name like 'papelaria%' or cat.cat_name like 'papelaria%');

-- Obtem o numero numero de vezes que um anuncio esta marcado como favorito por usuarios
select count(*) as 'Marked as favorite'
from wishlist w
inner join adposts ad on w.wl_ad_id = ad.ad_id
where ad.ad_id =  1;

-- obtem os dados simplificados dos chats de um usuario
SELECT c.chat_id as id, ad.ad_title as adTitle, IF(c.chat_usr_id = 1, ow.usr_name, u.usr_name) as user, 
(SELECT MAX(msg_time) FROM messages WHERE msg_chat_id = c.chat_id) as updated
FROM chats c
INNER JOIN adposts ad ON c.chat_ad_id = ad.ad_id
INNER JOIN users u ON c.chat_usr_id = u.usr_id
INNER JOIN users ow ON ad.ad_owner_id = ow.usr_id
WHERE c.chat_usr_id = 1 OR ad.ad_owner_id = 1;