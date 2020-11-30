package br.usjt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.usjt.conexao.ConnectionFactory;
import br.usjt.model.Musica;
import br.usjt.model.Genero;

public class MusicaDAO {
    
    // Para verificar se ja existe o nome da musica
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

    // Para inserir um novo registro de uma musica
    public boolean inserir(Musica musica) throws Exception {
        String query = "INSERT INTO musica (nome_musica, nome_artista) VALUES (?, ?)";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, musica.getNomeMusica());
            stmt.setString(2, musica.getNomeArtista());
            stmt.execute(); 
            return true; // Genero cadastrado com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Genero nao cadastrado devido algum problema
        }
    }

    // Para buscar uma musica especifica pelo nome dela e do artista
    public void selectMusica(Musica musica) throws Exception {
        String query = "SELECT * FROM musica WHERE nome_musica = ? AND nome_artista = ?";
        
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, musica.getNomeMusica());
            stmt.setString(2, musica.getNomeArtista());

            try (ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    int musicaID = res.getInt("id");
                    String nomeMusica = res.getString("nome_musica");
                    String nomeArtista = res.getString("nome_artista");
                    float posto = res.getFloat("posto");

                    musica.setMusicaID(musicaID);
                    musica.setNomeMusica(nomeMusica);
                    musica.setNomeArtista(nomeArtista);
                    musica.setPosto(posto);
                }
            }
        }
    }

    // Para buscar a musica pelo id dela
    public Musica selectMusica(int musicaID) throws Exception {
        String query = "SELECT * FROM musica WHERE id = ?";
        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, musicaID);

            Musica musica = new Musica();
            try (ResultSet res = stmt.executeQuery()) {
                while (res.next()) {
                    String nomeMusica = res.getString("nome_musica");
                    String nomeArtista = res.getString("nome_artista");
                    float posto = res.getFloat("posto");
                    musica = new Musica(musicaID, nomeMusica, nomeArtista, posto);
                    // return musica;
                }
                return musica;
            }
        }
    }

    // Para buscar todas as Musicas
    public Musica[] obterMusicas() throws Exception {
        String query = "SELECT * FROM musica ORDER BY nome_musica";
        
		try (Connection conn = ConnectionFactory.obterConexao();
				PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet res = stmt.executeQuery()) {

			int totalDeMusicas = res.last() ? res.getRow() : 0;
			Musica[] musicas = new Musica[totalDeMusicas];
			res.beforeFirst();
			int contador = 0;
			while (res.next()) {
                int musicaID = res.getInt("id");
				String nomeMusica = res.getString("nome_musica");
                String nomeArtista = res.getString("nome_artista");
                float posto = res.getFloat("posto");
                
				musicas[contador++] = new Musica(musicaID, nomeMusica, nomeArtista, posto);
			}
			return musicas;
		}
    }

    // Para atualizar a musica, mudando nome dela e do artista -- nao esta sendo usada no momento
    public boolean alterar(Musica musica) throws Exception {
        String query = "UPDATE musica SET nome_musica = ?, nome_artista = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.obterConexao(); 
        PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1,  musica.getNomeMusica());
            stmt.setString(2, musica.getNomeArtista());
            stmt.setInt(3, musica.getMusicaID());
            stmt.execute(); 
            return true; // Genero alterado com sucesso
        } catch (Exception e) {
            e.printStackTrace();
            return false; // Genero nao alterado devido algum problema
        }
    }

    // Para excluir uma musica pelo id dela
    public boolean excluir(int musicaID) throws Exception {
        String query = "DELETE FROM musica WHERE id = ?";
        
        if (new MusicaGeneroDAO().excluirMusica(musicaID)) {
            try (Connection conn = ConnectionFactory.obterConexao(); 
                    PreparedStatement stmt = conn.prepareStatement(query)) {
                
                stmt.setInt(1, musicaID);
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

    // Para buscar todas as musicas de um genero especifico
    public Musica[] obterMusicasGeneros(int generoID) throws Exception {
        String query = "SELECT musica.id as id, musica.nome_musica as nome_musica,"
                        + " musica.nome_artista as nome_artista, musica.posto as posto"
                        + " FROM musica"
                        + " INNER JOIN musicaGenero"
                        + " ON musica.id = musicaGenero.musica_id"
                        + " WHERE musicaGenero.genero_id = ?"
                        + " GROUP BY musica.id"
                        + " ORDER BY musica.nome_musica";

        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            stmt.setInt(1, generoID);
        
            try (ResultSet res = stmt.executeQuery()) {
                int totalDeMusicas = res.last() ? res.getRow() : 0;
                Musica[] musicas = new Musica[totalDeMusicas];
                res.beforeFirst();
                int contador = 0;
                while (res.next()) {
                    int musicaID = res.getInt("id");
                    String nomeMusica = res.getString("nome_musica");
                    String nomeArtista = res.getString("nome_artista");
                    float posto = res.getFloat("posto");
                    
                    musicas[contador++] = new Musica(musicaID, nomeMusica, nomeArtista, posto);
                }
                return musicas;
            }
            
        }
    }

    // Para buscar todas as musicas de diversos generos. O parametro de filtro,
    // caso true filtra somente as musicas que ja foram avaliadas com alguma nota,
    // caso false retorna as musicas avaliadas e as nao avaliadas
    public Musica[] obterMusicasGeneros(Genero[] generos, Musica[] musicasAva, boolean nullFilter) throws Exception {
        
        String listaGeneros = "";  
        if (generos.length != 0) {
            for (int i = 0; i < generos.length; i++) {
                if (i + 1 == generos.length) {
                    listaGeneros += generos[i].getGeneroID();
                } else {
                    listaGeneros += generos[i].getGeneroID() + ",";
                }
            }
        } else {
            listaGeneros += 0;
        }

        String listaMusicas = "";
        if (musicasAva.length != 0) {
            for (int i = 0; i < musicasAva.length; i++) {
                if (i + 1 == musicasAva.length) {
                    listaMusicas += musicasAva[i].getMusicaID();
                } else {
                    listaMusicas += musicasAva[i].getMusicaID() + ",";
                }
            }
        } else {
            listaMusicas += 0;
        }

        String filter = (nullFilter) ? " AND musica.posto IS NOT NULL": "";
        String query = "SELECT musica.id as id, musica.nome_musica as nome_musica,"
                        + " musica.nome_artista as nome_artista, musica.posto as posto"
                        + " FROM musica"
                        + " INNER JOIN musicaGenero"
                        + " ON musica.id = musicaGenero.musica_id"
                        + " WHERE musicaGenero.genero_id IN (" + listaGeneros + ")"
                        + " AND musicaGenero.musica_id NOT IN (" + listaMusicas + ")"
                        + filter
                        + " GROUP BY musica.id"
                        + " ORDER BY musica.posto DESC";

        try (Connection conn = ConnectionFactory.obterConexao(); 
                PreparedStatement stmt = conn.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            try (ResultSet res = stmt.executeQuery()) {
                int totalDeMusicas = res.last() ? res.getRow() : 0;
                Musica[] musicas = new Musica[totalDeMusicas];
                res.beforeFirst();
                int contador = 0;
                while (res.next()) {
                    int musicaID = res.getInt("id");
                    String nomeMusica = res.getString("nome_musica");
                    String nomeArtista = res.getString("nome_artista");
                    float posto = res.getFloat("posto");
                    
                    musicas[contador++] = new Musica(musicaID, nomeMusica, nomeArtista, posto);
                }
                return musicas;
            }   
        }
    }
}