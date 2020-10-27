package br.usjt.classes;

public class Musica {
    private int generoId;
    private String nomeMusica;
    private float posto;

    public Musica(int generoId, String nomeMusica) {
        this.generoId = generoId;
        this.nomeMusica = nomeMusica;
        // this.posto = calcularPosto();
    }

    // public float calcularPosto() {
    //     // retornar media das notas
    // }

    public int getGeneroId() {
        return generoId;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public float getPosto() {
        return posto;
    }
}
