package br.usjt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;

@Getter @RequiredArgsConstructor 
public class User {
    @Setter int userID;
    @NonNull @Setter private String nome;
    @NonNull private String username;
    @NonNull @Setter private String password;
    @NonNull @Setter private String tipoUser; // R = Regular, A = Administrador

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

/*
Através do lombok, criei todos os Getters e Setters para cada atributo.
Com o RequiredArgs, gerei um construtor que precisa apenas dos atributos que tiverem
    @NonNull na frente
Manualmente fiz um construtor que precisa apenas do username e do password
*/
