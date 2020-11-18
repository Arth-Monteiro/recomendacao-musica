package br.usjt.model;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Getter @RequiredArgsConstructor @ToString
public class User {
    @Setter int userID;
    @NonNull private String username;
    @NonNull @Setter private String password;
    @NonNull @Setter private String tipoUser; // R = Regular, A = Administrador, null
}

