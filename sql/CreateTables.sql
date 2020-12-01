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
);

CREATE TABLE userGenero (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    genero_id INT,
    FOREIGN KEY (user_id)
        REFERENCES users(id),
    FOREIGN KEY (genero_id)
        REFERENCES genero(id)
);

CREATE TABLE avaliacoes (
    avaliacoes_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    musica_id INT,
    nota_avaliacao FLOAT NOT NULL,
    FOREIGN KEY (user_id)
         REFERENCES users(id),
    FOREIGN KEY (musica_id) 
        REFERENCES musica(id)
);


SET SQL_SAFE_UPDATES = 0;

DELIMITER $$
CREATE TRIGGER update_posto_insert
	AFTER INSERT
	ON avaliacoes FOR EACH ROW
BEGIN
UPDATE musica AS dest
    INNER JOIN (
		SELECT SUM(nota_avaliacao)/COUNT(musica_id) AS posto, musica_id FROM avaliacoes
		GROUP BY avaliacoes.musica_id
	) AS src ON src.musica_id = dest.id
	SET dest.posto = src.posto
    WHERE dest.id = src.musica_id;
END$$

CREATE TRIGGER update_posto_update
	AFTER UPDATE
	ON avaliacoes FOR EACH ROW
BEGIN
UPDATE musica AS dest
    INNER JOIN (
		SELECT SUM(nota_avaliacao)/COUNT(musica_id) AS posto, musica_id FROM avaliacoes
		GROUP BY avaliacoes.musica_id
	) AS src ON src.musica_id = dest.id
	SET dest.posto = src.posto
    WHERE dest.id = src.musica_id;
END$$

-- DELIMITER $$
-- CREATE TRIGGER update_posto_delete
-- 	AFTER DELETE
-- 	ON avaliacoes FOR EACH ROW
-- BEGIN
-- UPDATE musica AS dest
--     INNER JOIN 
-- 		SELECT SUM(nota_avaliacao)/COUNT(musica_id) AS posto, musica_id FROM avaliacoes
--      GROUP BY avaliacoes.musica_id
-- 	) AS src ON src.musica_id = dest.id
-- 	SET dest.posto = src.posto
--     WHERE dest.id = src.musica_id;
-- END;

DELIMITER $
CREATE TRIGGER delete_genero_musica_avaliacoes
	AFTER DELETE 
    ON genero FOR EACH ROW
BEGIN
	DELETE FROM avaliacoes
	WHERE avaliacoes.musica_id IN (
		SELECT musica.id AS id FROM musica 
		LEFT JOIN musicaGenero
		ON musica.id = musicaGenero.musica_id
		WHERE musicaGenero.id IS NULL
		);
	DELETE musica FROM musica
    LEFT JOIN musicaGenero
    ON musica.id = musicaGenero.musica_id
	WHERE musicaGenero.id IS NULL;
END$