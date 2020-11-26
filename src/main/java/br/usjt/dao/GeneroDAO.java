package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;
import br.usjt.model.Genero;

public class GeneroDAO {

    public Genero[] obterGeneros() throws Exception {
        String query = "SELECT * FROM genero ORDER BY nome";
        
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
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
    
    public boolean verificarGenero(String nomeGenero) throws Exception {
        String query = "SELECT nome FROM genero WHERE nome = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nomeGenero);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return true; // Caso encontre algum nomeGenero
                } else {
                    return false; // Caso nao encontre esse nomeGenero
                }
            }
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
            e.printStackTrace();
            return false; // Genero nao cadastrado devido algum problema
        }
    }

    public boolean excluir(int generoID) throws Exception {
        String query = "DELETE FROM genero WHERE id = ?";
        
        if ((new MusicaGeneroDAO().excluirGenero(generoID)) && (new UserGeneroDAO().excluirGenero(generoID))){
            try (Connection conn = ConnectionFactory.obterConexao(); 
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                
                stmt.setInt(1, generoID);
                stmt.execute(); 
                return true; // Genero excluido com sucesso
            } catch (Exception e) {
                e.printStackTrace();
                return false; // Genero não excluido devido algum problema
            }
        } else {
            return false;
        }
    }

    public boolean alterar(Genero genero) throws Exception {
        String query = "UPDATE genero SET nome = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.obterConexao(); 
        PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, genero.getNomeGenero());
            stmt.setInt(2, genero.getGeneroID());
            stmt.execute(); 
            return true; // Genero alterado com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Genero nao alterado devido algum problema
        }
    }

    public Genero select(int generoID) throws Exception {
        String query = "SELECT * FROM genero WHERE id = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, generoID);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    int generoId = res.getInt("id");
                    String nomeGenero = res.getString("nome");
                    return new Genero(generoId, nomeGenero);
                }
            }        
            return new Genero(0, null);
        }
    }
}
