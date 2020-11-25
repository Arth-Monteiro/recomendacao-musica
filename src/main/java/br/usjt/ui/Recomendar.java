package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.dao.AvaliacaoDAO;
import br.usjt.dao.MusicaDAO;
import br.usjt.dao.UserGeneroDAO;

import br.usjt.model.*;

public class Recomendar extends FramePrincipal {

    private static final long serialVersionUID = -6121388711140290739L;
    
    private JTable recomendacoesTable;
    private JLabel recomendacoesLabel;
    private JButton voltarButton;

    public Recomendar(User user) {
        initTelaRecomendar(user);
        this.setLocationRelativeTo(container);
    }

    private void initTelaRecomendar(User user) {
        Font fonteLabels = new Font("sansserif", Font.BOLD, 13);
        Color branco = Color.WHITE;

        recomendacoesLabel = criarJLabel("Essas são suas recomendações", fonteLabels, branco);

        recomendacoesTable = criarTabela(user);
        JScrollPane barraRolagem = new JScrollPane(recomendacoesTable);
        recomendacoesTable.setEnabled(false);

        voltarButton = criarJButton("VOLTAR");
        inicioButton.setText("LOG OUT");

        voltarButton.addActionListener(evt -> voltarButtonActionPerformed(evt, user));
        
        panel.add(recomendacoesLabel);
        panel.add(barraRolagem);

        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(recomendacoesLabel)
            .addComponent(barraRolagem, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                .addComponent(inicioButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                .addComponent(voltarButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                )  
            .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
        );
        
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(recomendacoesLabel)
            .addComponent(barraRolagem, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            .addGap(10)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(inicioButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(voltarButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        pack();
    }

    private void voltarButtonActionPerformed(ActionEvent evt, User user) {
        new UsuarioComum(user).setVisible(true);
        this.dispose();
    }

    private JTable criarTabela(User user) {
        String[] colunas = {"Música", "Artista", "Nota"};
        Musica[] musicas = buscarRecomendacoes(user);

        Object[][] data = new Object[musicas.length][colunas.length];
        for (int i=0; i < musicas.length; i++) {
            data[i][0] = musicas[i].getNomeMusica();
            data[i][1] = musicas[i].getNomeArtista();
            data[i][2] = musicas[i].getPosto();
        }
        return new JTable(data, colunas);
    }

    private Musica[] buscarRecomendacoes(User user) {
        try {
            Genero[] generosUser = new UserGeneroDAO().selectGeneros(user.getUserID());
            Avaliacao[] avaliacoes = new AvaliacaoDAO().selectAvaliacoes(user);
            Musica[] musicasAvaliadas = new Musica[avaliacoes.length];
            for (int i = 0; i < avaliacoes.length; i++) { 
                musicasAvaliadas[i] = avaliacoes[i].getMusica();
            }

            Musica[] musicas = new MusicaDAO().obterMusicasGeneros(generosUser, musicasAvaliadas, true);
            return musicas;

        } catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar músicas, tente novamente mais tarde.", "Músicas Indisponíveis", 0);
            e.printStackTrace();
            Musica[] musicas = {};
            return musicas;
		}
    }

    public static void main(String[] args) {
        try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Recomendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Recomendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Recomendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Recomendar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User user = new User("Teste", "testuser", "12345678", "R");
                user.setUserID(1);
                new Recomendar(user).setVisible(true);
			}
		});
    }
}