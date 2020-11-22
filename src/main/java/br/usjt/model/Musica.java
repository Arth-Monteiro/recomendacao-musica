package br.usjt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

@Getter @Setter @RequiredArgsConstructor
public class Musica {
    private int musicaID;
    @NonNull private String nomeMusica;
    @NonNull private String nomeArtista;
    private float posto;
}
