package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;
import br.usjt.model.User;

public class UserDAO {

    public boolean login(User user) throws Exception {
        // Query para verificar se existe usuario
        String query = "SELECT * FROM users WHERE username = ? AND senha = ?"; 
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            // Define variaveis na query
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            // Executa a Query
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    int userID = res.getInt("id");
                    String tipoUser = res.getString("tipo");

                    user.setUserID(userID);
                    user.setTipoUser(tipoUser);
                    
                    return true; // Caso encontre algum usuario
                } else {
                    return false; // Caso nao encontre usuario
                }
            }
        }   
    }

    public boolean verificarUsername(String username) throws Exception {
        // Query para verificar se existe nome de usuario
        String query = "SELECT * FROM users WHERE username = ?"; 
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);

            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return true; // Caso encontre algum username
                } else {
                    return false; // Caso nao encontre esse username
                }
            }
        }
    }

    public boolean signin(User user) throws Exception {
        // Query para cadastrar usuario
        String query = "INSERT INTO users (username, senha, tipo) VALUES (?, ?, ?)";

        if (!verificarUsername(user.getUsername())) {
            try (Connection conn = ConnectionFactory.obterConexao(); 
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                        
                stmt.setString(1, user.getUsername());
                stmt.setString(2, user.getPassword());
                if (user.getTipoUser() == null) {
                    stmt.setString(3, "R");
                } else {
                    stmt.setString(3, user.getTipoUser());
                }

                stmt.execute();
                return true; // Usuario cadastrado com sucesso
            }
        } else {
            return false; // Usuario nao cadastrado pois ja existe o username
        }
    }

    public boolean excluirConta(int userID) throws Exception {
        // Query para deletar usuario
        String query = "DELETE FROM users WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.execute(); 
            return true; // Usuario excluido com sucesso
        } catch (Exception e) {
            return false; // Usuario nao excluido devido algum problema
        }
    }

    public boolean atualizarSenha(User user) throws Exception {
        String query = "UPDATE users SET senha = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, user.getPassword());
            stmt.setInt(2, user.getUserID());
            stmt.execute();
            return true; // Senha alterada com sucesso
        } catch (Exception e) {
            return false; // Senha não alterada devido algum problema
        }
    }
}