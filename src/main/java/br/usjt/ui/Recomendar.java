package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.dao.AvaliacaoDAO;
import br.usjt.dao.MusicaDAO;
import br.usjt.dao.MusicaGeneroDAO;
import br.usjt.dao.UserGeneroDAO;

import br.usjt.model.User;
import br.usjt.model.Genero;
import br.usjt.model.Musica;

public class Recomendar extends FramePrincipal {

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

        String[] colunas = {"Música", "Artista", "Nota"};

        recomendacoesLabel = criarJLabel("Essas são suas recomendações", fonteLabels, branco);

        recomendacoesTable = new JTable();
        JScrollPane barraRolagem = new JScrollPane(recomendacoesTable);
        
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
            .addComponent(barraRolagem)
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
            .addComponent(barraRolagem)
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

    private void voltarButtonActionPerformed(ActionEvent evt, User adm) {
        new UsuarioAdm(adm).setVisible(true);
        this.dispose();
    }

    private Musica[] buscarRecomendacoes(User user) {
        try {
            UserGeneroDAO userGeneroDao = new UserGeneroDAO();
            Genero[] generosUser = userGeneroDao.selectGeneros(user.getUserID());
            Musica[] musicas = new MusicaDAO().obterMusicasTodosGeneros(generosUser);
            return musicas;
        } catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar gêneros, tente novamente mais tarde.", "Gêneros Indisponíveis", 0);
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

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User user = new User("Teste", "testuser", "12345678", "R");
                user.setUserID(1);
                new Recomendar(user).setVisible(true);
			}
		});
    }
}
