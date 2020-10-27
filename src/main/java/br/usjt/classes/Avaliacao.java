package br.usjt.classes;


// CREATE TABLE avaliacoes (
// avaliacoes_id INT PRIMARY KEY AUTO_INCREMENT,
// login_id INT,
// musica_id INT,
// nota_avaliacao INT NOT NULL,
// FOREIGN KEY (login_id) REFERENCES login(login_id),
// FOREIGN KEY (musica_id) REFERENCES musica(musica_id)
// )

public class Avaliacao {
    private int loginId;
    private int musicaId;
    private int notaAvaliacao;

    // public Avaliacao(Int loginId,)
}
