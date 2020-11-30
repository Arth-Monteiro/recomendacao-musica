package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;
import br.usjt.model.User;

public class UserDAO {

    // Para realizar login, caso haja o username e a senha em algum registro, seta os outros campos de user.
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
                    String nome = res.getString("nome");
                    int userID = res.getInt("id");
                    String tipoUser = res.getString("tipo");

                    user.setNome(nome);
                    user.setUserID(userID);
                    user.setTipoUser(tipoUser);
                    
                    return true; // Caso encontre algum usuario
                } else {
                    return false; // Caso nao encontre usuario
                }
            }
        }   
    }

    // Verifica se já existe o username ou se é um novo
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

    // Para inserir um usuario novo
    public boolean signin(User user) throws Exception {
        // Query para cadastrar usuario
        String query = "INSERT INTO users (nome, username, senha, tipo) VALUES (?, ?, ?, ?)";
            try (Connection conn = ConnectionFactory.obterConexao(); 
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                
                stmt.setString(1, user.getNome());
                stmt.setString(2, user.getUsername());
                stmt.setString(3, user.getPassword());
                stmt.setString(4, user.getTipoUser());

                stmt.execute();
                return true; // Usuario cadastrado com sucesso
            } catch (Exception e) {
                e.printStackTrace();
                return false; // Usuario não cadastrado
            }
    }

    // Exclui o registro do usuario.
    public boolean excluirConta(int userID) throws Exception {
        // Query para deletar usuario
        String query = "DELETE FROM users WHERE id = ?";
        
        if ((new AvaliacaoDAO().alterarAvaliacao(userID)) && (new UserGeneroDAO().excluirUser(userID))) {
            try (Connection conn = ConnectionFactory.obterConexao(); 
                    PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, userID);
                stmt.execute(); 
                return true; // Usuario excluido com sucesso
            } catch (Exception e) {
                return false; // Usuario nao excluido devido algum problema
            }
        } else {
            return false; // Usuario nao excluido devido algum problema
        }
    }

    // Atualiza a senha do registro
    public boolean atualizarSenha(String password, int userID) throws Exception {
        String query = "UPDATE users SET senha = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, password);
            stmt.setInt(2, userID);
            stmt.execute();
            return true; // Senha alterada com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Senha não alterada devido algum problema
        }
    }
}