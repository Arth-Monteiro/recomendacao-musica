package br.usjt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Getter @Setter 
@RequiredArgsConstructor 
@AllArgsConstructor
@NoArgsConstructor
public class Musica {
    private int musicaID;
    @NonNull private String nomeMusica;
    @NonNull private String nomeArtista;
    private float posto;

    public String toString() {
        return this.nomeMusica + " - " + this.nomeArtista;
    }
}
