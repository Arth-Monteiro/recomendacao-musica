package br.usjt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.ToString;

@Getter @AllArgsConstructor @ToString 
public class Genero {
    @Setter int generoID;
    private String nomeGenero;
}