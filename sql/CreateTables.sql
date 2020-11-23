-- DROP DATABASE projeto_recomendacao;

CREATE DATABASE projeto_recomendacao;

USE projeto_recomendacao;

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50),
    username VARCHAR(20) NOT NULL UNIQUE,
    senha VARCHAR(16),
    tipo CHAR(1)
);

INSERT INTO users (nome, username, senha, tipo) VALUES 
('Teste', 'testuser', '12345678', 'R'),
('Teste', 'testadm', '12345678', 'A');

CREATE TABLE genero (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE musica (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome_musica VARCHAR(255),
    nome_artista VARCHAR(255),
    posto FLOAT
);

CREATE TABLE musicaGenero (
    id INT PRIMARY KEY AUTO_INCREMENT,
    musica_id INT,
    genero_id INT,
    FOREIGN KEY (musica_id)
        REFERENCES musica(id),
    FOREIGN KEY (genero_id)
        REFERENCES genero(id)
)

CREATE TABLE userGenero (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    genero_id INT,
    FOREIGN KEY (user_id)
        REFERENCES users(id),
    FOREIGN KEY (genero_id)
        REFERENCES genero(id)
)

CREATE TABLE avaliacoes (
    avaliacoes_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    musica_id INT,
    nota_avaliacao FLOAT NOT NULL,
    FOREIGN KEY (user_id)
         REFERENCES users(id),
    FOREIGN KEY (musica_id) 
        REFERENCES musica(id)
)

SET SQL_SAFE_UPDATES = 0;

DELIMITER $$
CREATE TRIGGER update_posto
	AFTER INSERT
	ON avaliacoes FOR EACH ROW
BEGIN
UPDATE musica AS dest
    INNER JOIN 
		(SELECT SUM(nota_avaliacao)/contagem AS posto, avaliacoes.musica_id FROM avaliacoes
		INNER JOIN (
			SELECT musica_id, COUNT(musica_id) AS contagem FROM avaliacoes
			GROUP BY musica_id
		) A 
		ON A.musica_id = avaliacoes.musica_id
		GROUP BY avaliacoes.musica_id
	) AS src ON src.musica_id = dest.id
	SET dest.posto = src.posto
    WHERE dest.id = src.musica_id;
END;