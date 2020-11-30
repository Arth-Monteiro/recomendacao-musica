package br.usjt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Getter @Setter @AllArgsConstructor @EqualsAndHashCode
public class Genero {
    private int generoID;
    private String nomeGenero;

    public String toString() {
        return this.nomeGenero;
    }
}

/*
Através do lombok, criei todos os Getters e Setters para cada atributo.
Com o AllArgs, gerei um construtor que precisa de todos os atributos.
Manualmente criei um toString para disponibilizar de forma visual o registro da Avaliacao.
Com o EqualsAndHashCode, uso o comparados de objetos -> .equals(Genero)
*/