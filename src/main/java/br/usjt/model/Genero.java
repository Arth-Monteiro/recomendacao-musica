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