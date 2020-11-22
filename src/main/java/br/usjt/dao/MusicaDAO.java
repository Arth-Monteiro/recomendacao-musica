package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;
import br.usjt.model.Musica;

public class MusicaDAO {

    public boolean verificarMusica(String nomeMusica) throws Exception {
        String query = "SELECT nome FROM musica WHERE nome_musica = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, nomeMusica);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return true; // Caso encontre algum nomeMusica
                } else {
                    return false; // Caso nao encontre esse nomeMusica
                }
            }
        }
    }

    
}
