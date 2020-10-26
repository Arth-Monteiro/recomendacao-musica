-- DROP DATABASE projeto_recomendacao;

CREATE DATABASE projeto_recomendacao;

USE projeto_recomendacao;

CREATE TABLE login (
login_id INT PRIMARY KEY AUTO_INCREMENT,
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

CREATE TABLE recomendacoes (
recomendacoes_id INT PRIMARY KEY AUTO_INCREMENT,
login_id INT,
musica_id INT,
nota_recomendacao INT NOT NULL,
FOREIGN KEY (login_id) REFERENCES login(login_id),
FOREIGN KEY (musica_id) REFERENCES musica(musica_id)
)