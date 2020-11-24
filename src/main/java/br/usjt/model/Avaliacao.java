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
        return this.musica + ": " + this.nota;
    }
}
