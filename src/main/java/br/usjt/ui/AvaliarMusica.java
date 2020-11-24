package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.model.Musica;
import br.usjt.model.Genero;
import br.usjt.dao.AvaliacaoDAO;
import br.usjt.dao.MusicaDAO;
import br.usjt.dao.UserGeneroDAO;

public class AvaliarMusica extends FramePrincipal {

    private static final long serialVersionUID = -6253463412460970549L;
    
    private JComboBox<String> oQueFazerComboBox;
    private JComboBox<Musica> musicasComboBox;
    private JComboBox<Genero> generosFavComboBox;
    private JCheckBox notaUmCheckBox, notaDoisCheckBox, notaTresCheckBox,
                        notaQuatroCheckBox, notaCincoCheckBox;
    private JLabel darNotaLabel, musicasLabel, generosLabel, alterarExlcuirLabel;
    private JButton avaliarButton, alterarButton, removerButton, voltarButton;
//TODO: mostrar avaliacoes
//TODO: Nao permitir usuario avaliar musicas ja avaliadas
    public AvaliarMusica(User user) {
        initTelaAvaliacao(user);
        this.setLocationRelativeTo(container);
    }

    private void initTelaAvaliacao(User user) {
        Font fonteLabels = new Font("sansserif", Font.BOLD, 13);
        Color branco = Color.WHITE;

        String[] oQueFazer = {"Escolha uma opção...",
                                "Avaliar Música",
                                "Alterar Avaliação",
                                "Excluir Avaliação"};

        oQueFazerComboBox = new JComboBox<String>(oQueFazer);
        musicasComboBox = new JComboBox<Musica>();
        generosFavComboBox = new JComboBox<Genero>();

        musicasLabel = criarJLabel("", fonteLabels, branco);
        generosLabel = criarJLabel("", fonteLabels, branco);
        darNotaLabel = criarJLabel("", fonteLabels, branco);
        alterarExlcuirLabel = criarJLabel("", fonteLabels, branco);

        notaUmCheckBox = new JCheckBox("1");
        notaDoisCheckBox = new JCheckBox("2");
        notaTresCheckBox = new JCheckBox("3");
        notaQuatroCheckBox = new JCheckBox("4");
        notaCincoCheckBox = new JCheckBox("5");

        avaliarButton = criarJButton("AVALIAR");
        alterarButton = criarJButton("ALTERAR");
        removerButton = criarJButton("REMOVER");
        voltarButton = criarJButton("VOLTAR");
        inicioButton.setText("LOG OUT");

        oQueFazerComboBox.addActionListener(evt -> verificarOQueFazerActionPerformed(evt, user));
        generosFavComboBox.addActionListener(evt -> verificarOQueFazerActionPerformed(evt, user));
        avaliarButton.addActionListener(evt -> adicionarAvaliacaoActionPerformed(evt, user));
        // alterarButton.addActionListener(evt -> alterarAvaliacaoActionPerformed(evt, user));
        // removerButton.addActionListener(evt -> excluirAvaliacaoActionPerformed(evt, user));
        voltarButton.addActionListener(evt -> voltarButtonActionPerformed(evt, user));
        notaUmCheckBox.addActionListener(evt -> verificarMarcacaoActionPerformed(evt));
        notaDoisCheckBox.addActionListener(evt -> verificarMarcacaoActionPerformed(evt));
        notaTresCheckBox.addActionListener(evt -> verificarMarcacaoActionPerformed(evt));
        notaQuatroCheckBox.addActionListener(evt -> verificarMarcacaoActionPerformed(evt));
        notaCincoCheckBox.addActionListener(evt -> verificarMarcacaoActionPerformed(evt));

        panel.add(oQueFazerComboBox);
        panel.add(generosFavComboBox);
        panel.add(musicasComboBox);
        panel.add(generosLabel);
        panel.add(musicasLabel);
        panel.add(darNotaLabel);
        panel.add(alterarExlcuirLabel);
        panel.add(notaUmCheckBox);
        panel.add(notaDoisCheckBox);
        panel.add(notaTresCheckBox);
        panel.add(notaQuatroCheckBox);
        panel.add(notaCincoCheckBox);
        panel.add(avaliarButton);
        panel.add(alterarButton);
        panel.add(removerButton);
        panel.add(voltarButton);
        
        buscarGenerosFavoritos(user);
        limparPanel(null);

        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(oQueFazerComboBox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(
                layout.createSequentialGroup()    
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(generosLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(generosFavComboBox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                )
            )      
            .addGroup(
                layout.createSequentialGroup()    
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(musicasLabel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(musicasComboBox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                )
            )      
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(darNotaLabel)   
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(notaUmCheckBox)   
                .addComponent(notaDoisCheckBox)   
                .addComponent(notaTresCheckBox)   
                .addComponent(notaQuatroCheckBox)   
                .addComponent(notaCincoCheckBox)   
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(avaliarButton)   
                .addComponent(alterarButton)   
                .addComponent(removerButton)   
            )
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
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(oQueFazerComboBox)   
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(generosLabel)
                .addComponent(generosFavComboBox)
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(musicasLabel)   
                .addComponent(musicasComboBox)   
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darNotaLabel)   
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(notaUmCheckBox)   
                .addComponent(notaDoisCheckBox)   
                .addComponent(notaTresCheckBox)   
                .addComponent(notaQuatroCheckBox)   
                .addComponent(notaCincoCheckBox)   
            )
            .addGap(10)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(avaliarButton)   
                .addComponent(alterarButton)   
                .addComponent(removerButton)   
            )
            .addGap(15)
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

    private void limparPanel(ActionEvent evt) {
        musicasComboBox.setVisible(false);
        generosFavComboBox.setVisible(false);
        generosLabel.setVisible(false);
        musicasLabel.setVisible(false);
        darNotaLabel.setVisible(false);
        alterarExlcuirLabel.setVisible(false);
        notaUmCheckBox.setSelected(false);
        notaDoisCheckBox.setSelected(false);
        notaTresCheckBox.setSelected(false);
        notaQuatroCheckBox.setSelected(false);
        notaCincoCheckBox.setSelected(false);
        notaUmCheckBox.setVisible(false);
        notaDoisCheckBox.setVisible(false);
        notaTresCheckBox.setVisible(false);
        notaQuatroCheckBox.setVisible(false);
        notaCincoCheckBox.setVisible(false);
        avaliarButton.setVisible(false);
        alterarButton.setVisible(false);
        removerButton.setVisible(false);
    }

    private Genero[] buscarGenerosFavoritos(User user) {
        try {
            UserGeneroDAO userGeneroDao = new UserGeneroDAO();
            Genero[] generosUser = userGeneroDao.selectGeneros(user.getUserID());
            generosFavComboBox.setModel(new DefaultComboBoxModel<>(generosUser));
            return generosUser;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar gêneros, tente novamente mais tarde.", "Gêneros Indisponíveis", 0);
            e.printStackTrace();
            Genero[] generoVazio = {};
            return generoVazio;
		}
    }

    private void buscarMusicas(User user) {
        Genero genero = (Genero) generosFavComboBox.getSelectedItem();
    
        try {
            MusicaDAO musicaDao = new MusicaDAO();
            Musica[] musicas = musicaDao.obterMusicasGeneros(genero.getGeneroID());
            musicasComboBox.setModel(new DefaultComboBoxModel<>(musicas));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar músicas, tente novamente mais tarde.", "Músicas Indisponíveis", 0);
            e.printStackTrace();
        }
    }



    private void verificarOQueFazerActionPerformed(ActionEvent evt, User user) {
        int opcao = oQueFazerComboBox.getSelectedIndex();
        switch (opcao) {
            case 0: limparPanel(evt); break;
            case 1: adicionarAvaliacaoActionPerformed(evt, user); break;
            //TODO: criar case 2 e case 3
        //     case 2: alterarAvaliacaoActionPerformed(evt, user); break;
        //     case 3: excluirAvaliacaoActionPerformed(evt, user); break;
        }
    }

    private void adicionarAvaliacaoActionPerformed(ActionEvent evt, User user) {
        // Limpar Panel
        if ((alterarButton.isVisible()) || (removerButton.isVisible())) {
            alterarExlcuirLabel.setVisible(false);
            alterarButton.setVisible(false);
            removerButton.setVisible(false);
        }

        
        generosLabel.setText("Selecione o Gênero: ");
        generosLabel.setVisible(true);
        generosFavComboBox.setVisible(true);
        buscarMusicas(user);

        if (!avaliarButton.isVisible()) {
            musicasLabel.setText("Selecione a Música: ");
            musicasLabel.setVisible(true);
            musicasComboBox.setVisible(true);
            darNotaLabel.setVisible(true);
            notaUmCheckBox.setVisible(true);
            notaDoisCheckBox.setVisible(true);
            notaTresCheckBox.setVisible(true);
            notaQuatroCheckBox.setVisible(true);
            notaCincoCheckBox.setVisible(true);
            avaliarButton.setVisible(true);
        } else {
            int nota;
            if (notaUmCheckBox.isSelected()) {
                nota = 1;
            } else if (notaDoisCheckBox.isSelected()) {
                nota = 2;
            } else if (notaTresCheckBox.isSelected()) {
                nota = 3;
            } else if (notaQuatroCheckBox.isSelected()) {
                nota = 4;
            } else if (notaCincoCheckBox.isSelected()) {
                nota = 5;
            } else {
                nota = 0;
            }

            Musica musica = (Musica) musicasComboBox.getSelectedItem();

            if (nota != 0) {
                try {
                    if (new AvaliacaoDAO().inserirAvaliacao(user.getUserID(), musica.getMusicaID(), nota)) {
                        limparPanel(evt);
                        JOptionPane.showMessageDialog(null, "Avaliação atribuída com sucesso.", "Bem sucedido", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Avaliação não atribuída.", "Mal sucedido", 0);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar músicas, tente novamente mais tarde.", "Músicas Indisponíveis", 0);
                    e.printStackTrace();
                }
            }
        }
    }

    private void voltarButtonActionPerformed(ActionEvent evt, User user) {
        new UsuarioComum(user).setVisible(true);
        this.dispose();
    }
    
    private void verificarMarcacaoActionPerformed(ActionEvent evt) {
                
        if (notaUmCheckBox.isSelected()) {
            notaDoisCheckBox.setEnabled(false);
            notaTresCheckBox.setEnabled(false);
            notaQuatroCheckBox.setEnabled(false);
            notaCincoCheckBox.setEnabled(false);
        } else if (notaDoisCheckBox.isSelected()) {
            notaUmCheckBox.setEnabled(false);
            notaTresCheckBox.setEnabled(false);
            notaQuatroCheckBox.setEnabled(false);
            notaCincoCheckBox.setEnabled(false);
        } else if (notaTresCheckBox.isSelected()) {
            notaUmCheckBox.setEnabled(false);
            notaDoisCheckBox.setEnabled(false);
            notaQuatroCheckBox.setEnabled(false);
            notaCincoCheckBox.setEnabled(false);
        } else if (notaQuatroCheckBox.isSelected()) {
            notaUmCheckBox.setEnabled(false);
            notaDoisCheckBox.setEnabled(false);
            notaTresCheckBox.setEnabled(false);
            notaCincoCheckBox.setEnabled(false);
        } else if (notaCincoCheckBox.isSelected()) {
            notaUmCheckBox.setEnabled(false);
            notaDoisCheckBox.setEnabled(false);
            notaTresCheckBox.setEnabled(false);
            notaQuatroCheckBox.setEnabled(false);
        } else {
            notaUmCheckBox.setEnabled(true);
            notaDoisCheckBox.setEnabled(true);
            notaTresCheckBox.setEnabled(true);
            notaQuatroCheckBox.setEnabled(true);
            notaCincoCheckBox.setEnabled(true);
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
			java.util.logging.Logger.getLogger(AvaliarMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(AvaliarMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(AvaliarMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(AvaliarMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User user = new User("Teste", "testuser", "12345678", "R");
                user.setUserID(1);
                new AvaliarMusica(user).setVisible(true);
			}
		});
    }
}
