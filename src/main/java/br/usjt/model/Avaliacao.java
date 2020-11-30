package br.usjt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter @Setter @AllArgsConstructor
public class Avaliacao {
    private int avaliacaoID;
    private Musica musica;
    private User user;
    private int nota;

    public String toString() {
        return this.musica + ", Nota: " + this.nota;
    }
}

/*
Através do lombok, criei todos os Getters e Setters para cada atributo.
Com o AllArgs, gerei um construtor que precisa de todos os atributos.
Manualmente criei um toString para disponibilizar de forma visual o registro da Avaliacao.
*/