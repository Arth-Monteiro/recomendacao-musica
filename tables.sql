-- DROP DATABASE projeto_recomendacao;

CREATE DATABASE projeto_recomendacao;

USE projeto_recomendacao;

CREATE TABLE users (
user_id INT PRIMARY KEY AUTO_INCREMENT,
username VARCHAR(20) NOT NULL UNIQUE,
senha VARCHAR(16)
);

CREATE TABLE genero (
genero_id INT PRIMARY KEY AUTO_INCREMENT,
nome_genero VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE musica (
musica_id INT PRIMARY KEY AUTO_INCREMENT,
genero_id INT,
nome_musica VARCHAR(255),
posto FLOAT,
FOREIGN KEY (genero_id) REFERENCES genero(genero_id)
);

CREATE TABLE avaliacoes (
avaliacoes_id INT PRIMARY KEY AUTO_INCREMENT,
user_id INT,
musica_id INT,
nota_avaliacao INT NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (musica_id) REFERENCES musica(musica_id)
)