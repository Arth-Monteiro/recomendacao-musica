package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
// import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;

public class MusicaGeneroDAO {
    public boolean excluirGenero(int generoID) throws Exception {
        String query = "DELETE FROM musicaGenero WHERE genero_id = ?";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, generoID);
            stmt.execute(); 
            return true; // Genero excluido com sucesso
        } catch (Exception e) {
            return false; // Genero não excluido devido algum problema
        }
    }
}
