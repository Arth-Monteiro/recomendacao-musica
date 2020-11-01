package br.usjt.classes;

import lombok.Getter;

public class Genero {
    @Getter private String nomeGenero;

    public Genero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

}
