
insert into tb_categoria(nome,criado_em) values ('livro',NOW());
insert into tb_categoria(nome,criado_em) values ('Notebookes',NOW());
insert into tb_categoria(nome,criado_em) values ('Computadores',NOW());
insert into tb_categoria(nome,criado_em) values ('Brinquedos',NOW());

insert into tb_produto(nome, descricao, preco, img_url, criado_em) values ('Livro', 'Livro de Spring Boot Avançado com JPA', 120.50, 'https://exemplo.com/img/livro.jpg', NOW());
insert into tb_produto(nome, descricao, preco, img_url, criado_em) values ('Notebooks', 'Notebook Gamer 16GB RAM 512GB SSD', 4500.00, 'https://exemplo.com/img/notebook.jpg', NOW());
insert into tb_produto(nome, descricao, preco, img_url, criado_em) values ('Computadores', 'PC de Mesa Completo para Escritório', 2500.00, 'https://exemplo.com/img/pc.jpg', NOW());
insert into tb_produto(nome, descricao, preco, img_url, criado_em) values ('Brinquedos', 'Quebra-cabeça de 1000 peças educativo', 89.90, 'https://exemplo.com/img/brinquedo.jpg', NOW());

insert into tb_produto_categoria(id_produto, id_categoria) values (1, 1);
insert into tb_produto_categoria(id_produto, id_categoria) values (1, 2);
insert into tb_produto_categoria(id_produto, id_categoria) values (2, 1);
insert into tb_produto_categoria(id_produto, id_categoria) values (3, 3);
insert into tb_produto_categoria(id_produto, id_categoria) values (4, 4);

insert into tb_perfil(nome) values ('ROLE_ADMINISTRADOR');
insert into tb_perfil(nome) values ('ROLE_VENDEDOR');
insert into tb_perfil(nome) values ('ROLE_CLIENTE');

insert into tb_usuario(nome, telefone, email, senha, criado_em) values ('Kauan', '3799122-2222','kauan.eduardosilveira3@gmail.com',123456,NOW());
insert into tb_usuario(nome, telefone, email, senha, criado_em) values ('Maria', '3799122-3333','maria@gmail.com',123456,NOW());

insert into tb_usuario_perfil(id_usuario, id_perfil) values (1, 1);
insert into tb_usuario_perfil(id_usuario, id_perfil) values (1, 2);
insert into tb_usuario_perfil(id_usuario, id_perfil) values (1, 3);
insert into tb_usuario_perfil(id_usuario, id_perfil) values (2, 3);
