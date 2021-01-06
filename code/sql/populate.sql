use donors_club;

insert into users values (usr_id, "Joao", "joao@test.com", "264745562", "7wT5sbobQKTYpr", "Rua 1, Liboa", true);
insert into users values (usr_id, "Pedro", "pedro@test.com", "110044159", "qATnbUqyfQsMm6", "Rua 2, Liboa", true);
insert into users values (usr_id, "Marcos", "marcos@test.com", "616049269", "8j2uRwsuWqSau4", "Rua 3, Liboa", true);
insert into users values (usr_id, "Felipe", "felipe@test.com", "807309431", "rCvPzrRu6rrFyb", "Rua 4, Liboa", true);
insert into users values (usr_id, "Ana", "ana@test.com", "231330844", "Mh5NPC3Ck7FpmC", "Rua 5, Liboa", true);
insert into users values (usr_id, "Julia", "julia@test.com", "578330149", "vv5kyuFgQ82UKE", "Rua 6, Liboa", true);
insert into users values (usr_id, "Rafael", "rafael@test.com", "807440772", "mE2dFj5wk7bXZV", "Rua 7, Liboa", true);
insert into users values (usr_id, "Tiago", "tiago@test.com", "714008913", "7Kx9fdi5dNJj6a", "Rua 8, Liboa", true);
insert into users values (usr_id, "Helem", "helem@test.com", "484670977", "2ZnVF7pHo66jxD", "Rua 9, Liboa", true);

insert into ads values (ad_id, "Mochila", "Mochila escolar", "Escolar", "2021-01-06", true, 2);
insert into ads values (ad_id, "Sapato", "Sapado social bom estado", "Vestuario", "2021-01-04", true, 1);
insert into ads values (ad_id, "Jaqueta", "Jaqueta usada", "Vestuario", "2021-01-01", true, 5);
insert into ads values (ad_id, "Telemovel", "Telemovel ainda usavel", "Eletronicos", "2021-01-01", true, 1);
insert into ads values (ad_id, "Calculadora grafica", "Estou a doar porque nao uso", "Escolar", "2021-01-03", true, 2);
insert into ads values (ad_id, "Sofa 2 lugares", "Sofa em bom estado para precisa", "Moveis", "2021-01-02", true, 3);
insert into ads values (ad_id, "Rato computador", "Nao uso", "Eletronicos", "2021-01-06", true, 6);
insert into ads values (ad_id, "Livros", "tenho varios para doar", "Escolar", "2021-01-04", true, 2);
insert into ads values (ad_id, "Tenis nike", "Esta quase novo, nao uso", "Vestuario", "2021-01-02", true, 4);
insert into ads values (ad_id, "Radio", "Esta a juntar poeira", "Eletronicos", "2021-01-03", true, 1);
insert into ads values (ad_id, "Tinta cor branca", "Para quem precisar", "Construcao", "2021-01-04", true, 3);

insert into chats values (chat_id, 7, 2, 1, true);
insert into chats values (chat_id, 4, 3, 6, true);
insert into chats values (chat_id, 9, 2, 5, true);
insert into chats values (chat_id, 8, 1, 10, true);

insert into messages values (msg_id, "ola esta disponivel", "2020-01-03", 2, 4);
insert into messages values (msg_id, "sim esta", "2020-01-04", 2, 3);
insert into messages values (msg_id, "oi, ainda esta disponivel", "2020-01-04", 3, 9);
insert into messages values (msg_id, "ola ja nao esta", "2020-01-04", 3, 2);
insert into messages values (msg_id, "ola", "2020-01-05", 4, 8);
insert into messages values (msg_id, "oi tenho interesse", "2020-01-06", 1, 7);
insert into messages values (msg_id, "esta disponivel!", "2020-01-06", 1, 2);
insert into messages values (msg_id, "onde pego", "2020-01-06", 1, 7);
insert into messages values (msg_id, "me ligue 110044159", "2020-01-06", 1, 2);