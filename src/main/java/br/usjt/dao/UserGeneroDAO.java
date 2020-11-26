package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;

import br.usjt.model.Genero;

public class UserGeneroDAO {
    public boolean inserirGenero(int userID, int generoID) {
        String query = "INSERT INTO userGenero (user_id, genero_id) VALUES (?, ?)";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, generoID);
            stmt.execute(); 
            return true; // Genero inserida com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Genero não inserida devido algum problema
        }
    }

    public boolean excluirGenero(int generoID) throws Exception {
        String query = "DELETE FROM userGenero WHERE genero_id = ?";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, generoID);
            stmt.execute(); 
            return true; // Genero excluido com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Genero não excluido devido algum problema
        }
    }

    public boolean excluirUser(int userID) throws Exception {
        String query = "DELETE FROM userGenero WHERE user_ID = ?";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.execute(); 
            return true; // Genero excluido com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Genero não excluido devido algum problema
        }
    }

    public Genero[] selectGeneros(int userID) throws Exception {
        String query = "SELECT genero_id FROM userGenero WHERE user_id = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            stmt.setInt(1, userID);
            try (ResultSet res = stmt.executeQuery()) {
                int totalDeGeneros = res.last() ? res.getRow() : 0;
                Genero[] generos = new Genero[totalDeGeneros];
                res.beforeFirst();
                int contador = 0;
                while (res.next()) {
                    int generoID = res.getInt("genero_id");
                    Genero genero = new GeneroDAO().select(generoID);
                    generos[contador++] = genero;
                }
                return generos;
            }
        }
    }
}
