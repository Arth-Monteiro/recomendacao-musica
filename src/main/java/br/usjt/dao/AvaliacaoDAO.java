package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;

public class AvaliacaoDAO {
    public boolean inserirAvaliacao(int userID, int musicaID, int nota) {
        String query = "INSERT INTO avaliacoes (user_id, musica_id, nota_avaliacao) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, musicaID);
            stmt.setInt(3, nota);
            stmt.execute(); 
            return true; // Genero inserida com sucesso
        } catch (Exception e) {
            return false; // Genero não inserida devido algum problema
        }
    }

    
}
