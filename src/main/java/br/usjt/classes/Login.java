package br.usjt.classes;

public class Login {
    private String username;
    private String senha;

    public Login(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUsername() {
        return username;
    }

    public String getSenha() {
        return senha;
    }

    // public void logar() {

    // }
    
}
