USE projeto_recomendacao;

INSERT INTO users (nome, username, senha, tipo) VALUES 
('Teste', 'testuser', '12345678', 'R'), -- id 1
('Teste', 'testadm', '12345678', 'A');  -- id 2

INSERT INTO genero (nome) VALUES 
('Rock'),   -- id 1
('Pop'),    -- id 2
('Mpb'),    -- id 3
('Reggae'); -- id 4

INSERT INTO musica (nome_musica, nome_artista) VALUES
('Thriller', 'Michael Jackson'),            -- id 1
('Maps', 'Maroon V'),                       -- id 2
('Someone you Loved', 'Lewis Capaldi'),     -- id 3
('When I Was Yout Man', 'Bruno Mars'),      -- id 4
('I See Fire', 'Ed Sheeran'),               -- id 5
('Run to the Hills', 'Iron Maiden'),        -- id 6
('Highway to Hell', 'AC/DC'),               -- id 7
('Sweet Child o Mine', 'Guns N Roses'),     -- id 8
('One', 'Metallica'),                       -- id 9
('Another One Bites the Dust', 'Queen'),    -- id 10
('Velha Infância', 'Tribalhistas'),         -- id 11
('Amei te Ver', 'Tiago Iorc'),              -- id 12
('Trem Bala', 'Ana Viela, Luan Santana'),   -- id 13
('Aquarela', 'Toquinho'),                   -- id 14
('De Janeiro a Janeiro', 'Roberto Carlos'), -- id 15
('Saudades do Tempo', 'Maneva'),            -- id 16
('Naticongo', 'Natiruts'),                  -- id 17
('Um Anjo do Céu', 'Maskavo'),              -- id 18
('Beija-Flor', 'Natiruts'),                 -- id 19
('Me Namora', 'Edu Ribeiro');               -- id 20

INSERT INTO musicaGenero (musica_id, genero_id) VALUES
(1, 2), (2, 2), (3, 2), (4, 2), (5, 2),         -- pop
(6, 1), (7, 1), (8, 1), (9, 1), (10, 1),        -- rock
(11, 3), (12, 3), (13, 3), (14, 3), (15, 3),    -- mpb
(16, 4), (17, 4), (18, 4), (19, 4), (20, 4);    -- reggae

INSERT INTO userGenero (user_id, genero_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4);

INSERT INTO avaliacoes (user_id, musica_id, nota_avaliacao) VALUES
(1, 1, 5), (1, 2, 5), (1, 3, 4), (1, 4, 5), (1, 5, 4),
(1, 6, 4), (1, 7, 5), (1, 8, 3), (1, 9, 5), (1, 10, 4),
(1, 11, 3), (1, 12, 4), (1, 13, 4), (1, 14, 3), (1, 15, 5),
(1, 16, 4), (1, 17, 4), (1, 18, 5), (1, 19, 4), (1, 20, 5);