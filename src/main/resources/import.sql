
INSERT INTO tb_cliente (nome, email, cpf) VALUES ('Maria Silva', 'maria@gmail.com', '12345678900');
INSERT INTO tb_cliente (nome, email, cpf) VALUES ('João Souza', 'joao@gmail.com', '98765432100');
INSERT INTO tb_cliente (nome, email, cpf) VALUES ( 'Ulisses Batista', 'ulisses@gmail.com', '04528136546');
INSERT INTO tb_cliente (nome, email, cpf) VALUES ( 'Lais Alves', 'lais@gmail.com', '42102056805');

INSERT INTO tb_role (id, authority) VALUES (1, 'ROLE_USER');
INSERT INTO tb_role (id, authority) VALUES (2, 'ROLE_ADMIN');

INSERT INTO tb_auth_user (id, email, password, cliente_id) VALUES (1, 'maria@gmail.com', '$2a$10$HDY6WHx3YljY.Khb6Tmdu.zsToT/4VzMBsCPxWOxxY9kX.GrLJEci', 1);
INSERT INTO tb_auth_user (id, email, password, cliente_id) VALUES (2, 'joao@gmail.com', '$2a$10$HDY6WHx3YljY.Khb6Tmdu.zsToT/4VzMBsCPxWOxxY9kX.GrLJEci', 2);
INSERT INTO tb_auth_user (id, email, password, cliente_id) VALUES (3, 'ulisses@gmail.com', '$2a$10$HDY6WHx3YljY.Khb6Tmdu.zsToT/4VzMBsCPxWOxxY9kX.GrLJEci', 3);
INSERT INTO tb_auth_user (id, email, password, cliente_id) VALUES (4, 'lais@gmail.com', '$2a$10$HDY6WHx3YljY.Khb6Tmdu.zsToT/4VzMBsCPxWOxxY9kX.GrLJEci', 4);

INSERT INTO tb_auth_user_role (auth_user_id, role_id) VALUES (1, 1); -- Maria USER
INSERT INTO tb_auth_user_role (auth_user_id, role_id) VALUES (2, 1); -- João USER
INSERT INTO tb_auth_user_role (auth_user_id, role_id) VALUES (3, 2); -- Ulisses ADMIN
INSERT INTO tb_auth_user_role (auth_user_id, role_id) VALUES (4, 1); -- Lais USER



INSERT INTO tb_pedido (data, status, cliente_id) VALUES ('2025-08-26 10:00:00', 'PENDENTE', 1);
INSERT INTO tb_pedido (data, status, cliente_id) VALUES ('2025-08-26 15:30:00', 'PAGO', 1);
INSERT INTO tb_pedido (data, status, cliente_id) VALUES ('2025-08-27 09:00:00', 'CANCELADO', 2);

INSERT INTO tb_endereco_entrega (rua, cidade, cep, numero, pedido_id) VALUES ('Rua das Flores', 'Guarulhos', '01000-000', '123', 1);
INSERT INTO tb_endereco_entrega (rua, cidade, cep, numero, pedido_id) VALUES ('Av. Brasil', 'Rio de Janeiro', '20000-000', '456', 2);

INSERT INTO tb_categoria (nome) VALUES ('Alimentos');
INSERT INTO tb_categoria (nome) VALUES ('Bebidas');
INSERT INTO tb_categoria (nome) VALUES ('Higiene');
INSERT INTO tb_categoria (nome) VALUES ('Limpeza');

INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Arroz', 25.00, 100);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Feijão', 10.00, 200);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Macarrão', 5.20, 150);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Óleo de Soja', 7.80, 80);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Açúcar', 4.90, 120);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Café', 15.00, 60);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Farinha de Trigo', 6.40, 90);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Leite em Pó', 12.50, 70);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Manteiga', 9.90, 50);
INSERT INTO tb_produto (nome, preco, estoque) VALUES ('Sal', 3.20, 300);

INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (1, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (2, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (3, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (5, 1);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (7, 1);

INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (4, 4);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (10, 4);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (6, 2);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (8, 2);
INSERT INTO tb_produto_categoria (produto_id, categoria_id) VALUES (9, 3);

INSERT INTO tb_item_pedido (produto_id, quantidade, preco, pedido_id) VALUES (1, 5, 125.00, 1);
INSERT INTO tb_item_pedido (produto_id, quantidade, preco, pedido_id) VALUES (2, 3, 30.00, 2);
INSERT INTO tb_item_pedido (produto_id, quantidade, preco, pedido_id) VALUES (3,2, 10.40, 3);
INSERT INTO tb_item_pedido (produto_id, quantidade, preco, pedido_id) VALUES (10, 3, 9.60, 1);

INSERT INTO tb_pagamento (tipo, pagamento_status, data, pedido_status, pedido_id) VALUES ('CARTAO_CREDITO',  'APROVADO', '2025-08-29 14:30:00', 'ENVIADO' ,1);
INSERT INTO tb_pagamento (tipo, pagamento_status, data, pedido_status, pedido_id) VALUES ('PIX', 'PENDENTE', '2025-08-29 15:00:00', 'PENDENTE' ,2);
INSERT INTO tb_pagamento (tipo, pagamento_status, data, pedido_status, pedido_id) VALUES ('BOLETO', 'CANCELADO', '2025-08-29 16:00:00', 'CANCELADO', 3);




