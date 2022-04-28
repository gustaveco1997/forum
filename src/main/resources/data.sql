INSERT INTO USUARIO(nome, email, senha, ativo) VALUES ('Gustavo', 'gustavo@gmail.com', '$2a$10$N/qxKZSTFqYmyURUcxyhO.ITToESo95/x37FeP91M6oV.jNni24KC', 1);
INSERT INTO USUARIO(nome, email, senha, ativo) VALUES ('Paula', 'paula@gmail.com', '$2a$10$N/qxKZSTFqYmyURUcxyhO.ITToESo95/x37FeP91M6oV.jNni24KC', 0);
INSERT INTO USUARIO(nome, email, senha, ativo) VALUES ('Paloma', 'teste', '$2a$10$N/qxKZSTFqYmyURUcxyhO.ITToESo95/x37FeP91M6oV.jNni24KC', 1);

INSERT INTO CURSO(nome, categoria) VALUES ('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES ('DynamoDB', 'Banco de Dados');
INSERT INTO CURSO(nome, categoria) VALUES ('MongoDB', 'Banco de Dados');
INSERT INTO CURSO(nome, categoria) VALUES ('Java', 'Programação');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 1', 'Erro no projeto', '2022-04-17', 'NAO_RESPONDIDO', 1,1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 2', 'Erro na lógica', '2022-04-18', 'NAO_SOLUCIONADO', 1,1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 3', 'Erro na compilação', '2022-04-19', 'SOLUCIONADO', 1,1);

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 4', 'Erro no projeto', '2022-04-17', 'NAO_RESPONDIDO', 1,2);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 5', 'Erro na compra e conta AWS', '2022-04-18', 'NAO_SOLUCIONADO', 1,2);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 6', 'Erro na criação o banco', '2022-04-19', 'SOLUCIONADO', 1,3);


INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 7', 'Erro no projeto em java', '2022-04-17', 'NAO_RESPONDIDO', 2,4);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 8', 'Erro no stream java', '2022-04-18', 'NAO_SOLUCIONADO', 2,4);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES ('Dúvida 9', 'Erro na criação', '2022-04-19', 'SOLUCIONADO', 1,3);

