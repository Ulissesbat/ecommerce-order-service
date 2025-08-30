
INSERT INTO tb_cliente (nome, email, cpf) VALUES ('Maria Silva', 'maria@gmail.com', '12345678900');
INSERT INTO tb_cliente (nome, email, cpf) VALUES ('João Souza', 'joao@gmail.com', '98765432100');
INSERT INTO tb_cliente (nome, email, cpf) VALUES ( 'Ulisses Batista', 'ulisses@gmail.com', '04528136546');
INSERT INTO tb_cliente (nome, email, cpf) VALUES ( 'Lais Alves', 'lais@gmail.com', '42102056805');

INSERT INTO tb_pedido (data, status, cliente_id) VALUES ('2025-08-26 10:00:00', 'PENDENTE', 1);
INSERT INTO tb_pedido (data, status, cliente_id) VALUES ('2025-08-26 15:30:00', 'PAGO', 1);
INSERT INTO tb_pedido (data, status, cliente_id) VALUES ('2025-08-27 09:00:00', 'CANCELADO', 2);

INSERT INTO tb_endereco_entrega (rua, cidade, cep, numero, pedido_id) VALUES ('Rua das Flores', 'Guarulhos', '01000-000', '123', 1);
INSERT INTO tb_endereco_entrega (rua, cidade, cep, numero, pedido_id) VALUES ('Av. Brasil', 'Rio de Janeiro', '20000-000', '456', 2);

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

INSERT INTO tb_Intem_pedido (produto_id, quantidade, preco, pedido_id) VALUES (1, 5, 125.00, 1);
INSERT INTO tb_Intem_pedido (produto_id, quantidade, preco, pedido_id) VALUES (2, 3, 30.00, 2);
INSERT INTO tb_Intem_pedido (produto_id, quantidade, preco, pedido_id) VALUES (3,2, 10.40, 3);
INSERT INTO tb_Intem_pedido (produto_id, quantidade, preco, pedido_id) VALUES (10, 3, 9.60, 1);

INSERT INTO tb_pagamento (tipo, status, data, pedido_id) VALUES ('CARTAO_CREDITO', 'PAGO', '2025-08-29 14:30:00', 1);
INSERT INTO tb_pagamento (tipo, status, data, pedido_id) VALUES ('PIX', 'PENDENTE', '2025-08-29 15:00:00', 2);
INSERT INTO tb_pagamento (tipo, status, data, pedido_id) VALUES ('BOLETO', 'CANCELADO', '2025-08-29 16:00:00', 3);




