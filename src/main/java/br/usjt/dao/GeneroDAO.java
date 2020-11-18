package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;
import br.usjt.model.Genero;

public class GeneroDAO {

    public Genero[] obterGeneros() throws Exception {
        String sql = "SELECT * FROM generos";
        
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet res = stmt.executeQuery()) {

			int totalDeGeneros = res.last() ? res.getRow() : 0;
			Genero[] generos = new Genero[totalDeGeneros];
			res.beforeFirst();
			int contador = 0;
			while (res.next()) {
				int generoID = res.getInt("id");
				String nomeGenero = res.getString("nome");
				generos[contador++] = new Genero(generoID, nomeGenero);
			}
			return generos;
		}
	}
    
    public boolean inserir(String nomeGenero) throws Exception {
        String query = "INSERT INTO genero (nome) VALUES (?)";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nomeGenero);
            stmt.execute(); 
            return true; // Genero cadastrado com sucesso
        } catch (Exception e) {
            return false; // Genero nao cadastrado devido algum problema
        }
    }

    public boolean excluir(int generoID) throws Exception {
        String query = "DELETE FROM users WHERE id = ?";
        
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
