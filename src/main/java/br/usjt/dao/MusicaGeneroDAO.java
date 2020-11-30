package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;
import br.usjt.model.Genero;

public class MusicaGeneroDAO {
    // Para excluir um registro da tabela de relacao entre musica e genero atraves do genero_id
    public boolean excluirGenero(int generoID) throws Exception {
        String query = "DELETE FROM musicaGenero WHERE genero_id = ?";
        
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
    
    // Para excluir um registro da tabela de relacao entre musica e genero atraves do musica_id
    public boolean excluirMusica(int musicaID) throws Exception {
        String query = "DELETE FROM musicaGenero WHERE musica_id = ?";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, musicaID);
            stmt.execute(); 
            return true; // Genero excluido com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Genero não excluido devido algum problema
        }
    }

    // Para inserir um registro de relacao entre genero e musica
    public boolean inserirMusica(int musicaID, int generoID) {
        String query = "INSERT INTO musicaGenero (musica_id, genero_id) VALUES (?, ?)";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, musicaID);
            stmt.setInt(2, generoID);
            stmt.execute(); 
            return true; // Musica inserida com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Musica não inserida devido algum problema
        }
    }

    // Para atualizar um registro -- nao esta sendo usado no momento
    public boolean atualizarMusica(int musicaID, int generoID) {
        String query = "UPDATE musicaGenero SET genero_id = ? WHERE musica_id = ?";        

        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, generoID);
            stmt.setInt(2, musicaID);
            stmt.execute(); 
            return true; // Musica atualizada com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Musica não atualizada devido algum problema
        }
    }

    // Retorna todos os generos que uma musica esta relacionada
    public Genero[] buscarGenero(int musicaID) throws Exception {
        String query = "SELECT genero_id FROM musicaGenero WHERE musica_id = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            stmt.setInt(1, musicaID);
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
