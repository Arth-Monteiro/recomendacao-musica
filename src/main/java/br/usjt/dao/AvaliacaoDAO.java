package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;
import br.usjt.model.Avaliacao;
import br.usjt.model.Musica;
import br.usjt.model.User;

public class AvaliacaoDAO {
    public boolean inserirAvaliacao(int userID, int musicaID, int nota) throws Exception {
        String query = "INSERT INTO avaliacoes (user_id, musica_id, nota_avaliacao) VALUES (?, ?, ?)";

        if (!verificarAvaliacao(userID, musicaID)) {
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
        } else {
            return false; // Genero não inserido devido já existir
        }
    }

    public boolean verificarAvaliacao(int userID, int musicaID) throws Exception{
        String query = "SELECT * FROM avaliacoes WHERE user_id = ? AND musica_id = ?";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, musicaID);
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    return true; // Caso encontre alguma avaliacao
                } else {
                    return false; // Caso nao encontre a avaliacao
                }
            }
        }
    }

    public Avaliacao[] selectAvaliacoes(User user) throws Exception {
        String query = "SELECT * FROM avaliacoes WHERE user_id = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            stmt.setInt(1, user.getUserID());
            try (ResultSet res = stmt.executeQuery()) {
                int totalDeGeneros = res.last() ? res.getRow() : 0;
                Avaliacao[] avaliacoes = new Avaliacao[totalDeGeneros];
                res.beforeFirst();
                int contador = 0;
                while (res.next()) {
                    int avaliacaoID = res.getInt("avaliacoes_id");
                    int musicaID = res.getInt("musica_id");
                    int nota = res.getInt("nota_avaliacao");

                    Musica musica = new MusicaDAO().selectMusica(musicaID);

                    avaliacoes[contador++] = new Avaliacao(avaliacaoID, musica, user, nota);
                }
                return avaliacoes;
            }
        }
    }

    public boolean alterarAvaliacao(int avaliacaoID, int nota) throws Exception {
        String query = "UPDATE avaliacoes SET nota_avaliacao = ? WHERE avaliacoes_id = ?";

        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, nota);
            stmt.setInt(2, avaliacaoID);
            stmt.execute();
            return true; // alterada com sucesso
        } catch (Exception e) {
            return false; // não alterada devido algum problema
        }
    }

    public boolean removerAvaliacao(int avaliacaoID) {
        String query = "DELETE FROM avaliacoes WHERE avaliacoes_id = ?";

        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, avaliacaoID);
            stmt.execute();
            return true; // alterada com sucesso
        } catch (Exception e) {
            return false; // não alterada devido algum problema
        }
    }
}
