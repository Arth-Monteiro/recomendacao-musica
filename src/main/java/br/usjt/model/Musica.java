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

/*
Através do lombok, criei todos os Getters e Setters para cada atributo.
Com o AllArgs, gerei um construtor que precisa de todos os atributos.
Com o RequiredArgs, gerei um construtor que precisa apenas dos atributos que tiverem
    @NonNull na frente
Com o NoArgsConstructos, gerei um construtor que nao precisa de nenhum atributo.
    Serve apenas para instanciar o objeto novo.
Manualmente criei um toString para disponibilizar de forma visual o registro da Avaliacao.
*/