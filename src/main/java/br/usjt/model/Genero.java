package br.usjt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

@Getter @Setter @AllArgsConstructor
public class Genero {
    private int generoID;
    private String nomeGenero;

    public String toString() {
        return this.nomeGenero;
    }
}