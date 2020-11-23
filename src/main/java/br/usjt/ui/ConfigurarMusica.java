package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

import br.usjt.model.User;
import br.usjt.model.Genero;
import br.usjt.model.Musica;
import br.usjt.dao.GeneroDAO;
import br.usjt.dao.MusicaDAO;
import br.usjt.dao.MusicaGeneroDAO;

public class ConfigurarMusica extends FramePrincipal {

    private JComboBox<String> oQueFazerComboBox;
    private JComboBox<Musica> musicasComboBox;
    private JList<Genero> generosList;
    private JScrollPane associarGeneros;
    private JButton cadastrarButton, alterarButton, removerButton, voltarButton;
    private JLabel musicaLabel, artistaLabel, selecionarMusicaLabel, selecionarGeneroLabel;
    private JTextField musicaTextField, artistaTextField;

    public ConfigurarMusica(User adm) {
        initTelaConfigurarMusicas(adm);
        this.setLocationRelativeTo(container);
    }

    private void initTelaConfigurarMusicas(User adm) {
        Font fonteLabels = new Font("sansserif", Font.BOLD, 13);
        Color branco = Color.WHITE;

        String[] oQueFazer = {"Escolha uma opção...", 
                                "Cadastrar Música",
                                "Verificar Músicas",
                                "Alterar Cadastro Música", 
                                "Excluir Música"};

        oQueFazerComboBox = new JComboBox<String>(oQueFazer);
        musicasComboBox = new JComboBox<Musica>();

        cadastrarButton = criarJButton("CADASTRAR");
        alterarButton = criarJButton("ALTERAR");
        removerButton = criarJButton("REMOVER");
        voltarButton = criarJButton("VOLTAR");
        inicioButton.setText("LOG OUT");

        selecionarMusicaLabel = criarJLabel("Selecione a Música à alterar: ", fonteLabels, branco);
        selecionarGeneroLabel = criarJLabel("<html>Selecione o Gênero<br>(Ctrl para mais de um item):</html>", fonteLabels, branco);

        musicaLabel = criarJLabel("", fonteLabels, branco);
        musicaTextField = criarJTextField();

        artistaLabel = criarJLabel("",  fonteLabels, branco);
        artistaTextField = criarJTextField();

        generosList = new JList<Genero>(buscarGeneros());
        associarGeneros = new JScrollPane();
        associarGeneros.setViewportView(generosList);

        oQueFazerComboBox.addActionListener(evt -> verificarOQueFazerActionPerformed(evt));
        cadastrarButton.addActionListener(evt -> cadastrarMusicaActionPerformed(evt));
        alterarButton.addActionListener(evt -> alterarCadMusicaActionPerformed(evt));
        removerButton.addActionListener(evt -> excluirMusicaActionPerformed(evt));
        voltarButton.addActionListener(evt -> voltarButtonActionPerformed(evt, adm));

        panel.add(oQueFazerComboBox);
        panel.add(musicasComboBox);
        panel.add(cadastrarButton);
        panel.add(alterarButton);
        panel.add(removerButton);
        panel.add(voltarButton);
        panel.add(selecionarMusicaLabel);
        panel.add(musicaLabel);
        panel.add(musicaTextField);
        panel.add(artistaLabel);
        panel.add(artistaTextField);
        panel.add(voltarButton);
        panel.add(selecionarGeneroLabel);
        panel.add(associarGeneros);

        limparPanel(null);

        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(oQueFazerComboBox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            .addGroup(
                layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(selecionarMusicaLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                )   
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(musicasComboBox, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                )
            )
            // Cadastro Música e Alterar Cadastro Música
            .addGroup(
                layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(musicaLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                )   
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(musicaTextField, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                )
            )
            // Artista
            .addGroup(
                layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(artistaLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                )   
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(artistaTextField, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                )
            )
            .addGroup(
                layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(selecionarGeneroLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                )
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(associarGeneros, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                )
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(cadastrarButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                .addComponent(alterarButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
                .addComponent(removerButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)    
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
            .addComponent(oQueFazerComboBox)
            .addGap(10)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(selecionarMusicaLabel)
                .addComponent(musicasComboBox)
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(musicaLabel)
                .addComponent(musicaTextField)
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(artistaLabel)
                .addComponent(artistaTextField)
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(selecionarGeneroLabel)
                .addComponent(associarGeneros, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(cadastrarButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(alterarButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(removerButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
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

    private void limparPanel(ActionEvent evt) {
        musicasComboBox.setVisible(false);
        cadastrarButton.setVisible(false);
        alterarButton.setVisible(false);
        removerButton.setVisible(false);
        selecionarMusicaLabel.setVisible(false);
        musicaLabel.setVisible(false);
        musicaTextField.setVisible(false);
        artistaLabel.setVisible(false);
        artistaTextField.setVisible(false);
        selecionarGeneroLabel.setVisible(false);
        associarGeneros.setVisible(false);
    }

    private void verificarOQueFazerActionPerformed(ActionEvent evt) {
        int opcao = oQueFazerComboBox.getSelectedIndex();
        switch (opcao) {
            case 0: limparPanel(evt); break;
            case 1: cadastrarMusicaActionPerformed(evt); break;
            case 2: verificarMusicaActionPerformed(evt); break;
            case 3: alterarCadMusicaActionPerformed(evt); break;
            case 4: excluirMusicaActionPerformed(evt); break;
        }
    }

    private void cadastrarMusicaActionPerformed(ActionEvent evt) {
        if ((alterarButton.isVisible()) || (removerButton.isVisible()) || (musicasComboBox.isVisible())) {
            musicasComboBox.setVisible(false);
            alterarButton.setVisible(false);
            removerButton.setVisible(false);
            selecionarMusicaLabel.setVisible(false);
        }

        if (!cadastrarButton.isVisible()) {
            musicaLabel.setText("Insira o nome da Música: ");
            musicaLabel.setVisible(true);
            musicaTextField.setText("");
            musicaTextField.setVisible(true);
            artistaLabel.setText("Insira o nome do Artista: ");
            artistaLabel.setVisible(true);
            artistaTextField.setText("");
            artistaTextField.setVisible(true);
            selecionarGeneroLabel.setVisible(true);
            associarGeneros.setVisible(true);
            cadastrarButton.setVisible(true);
        } else {
            List<Genero> generos = generosList.getSelectedValuesList();
            String nomeMusica = musicaTextField.getText();
            String nomeArtista = artistaTextField.getText();

            if ((nomeMusica.isEmpty()) || (nomeMusica.length() > 255 )) {
                JOptionPane.showMessageDialog(null, "Campo 'Nome Musica' incorreto!", "Campo Incorreto", 0);
            } else if ((nomeArtista.isEmpty()) || (nomeArtista.length() > 255 )) {
                JOptionPane.showMessageDialog(null, "Campo 'Nome Artista' incorreto!", "Campo Incorreto", 0);
            } else if (generos.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nenhum genero associado!", "Campo Incorreto", 0);
            } else {
                Musica musica = new Musica(nomeMusica, nomeArtista);
                MusicaDAO musicaDao = new MusicaDAO();
                MusicaGeneroDAO musicaGeneroDao = new MusicaGeneroDAO();
                try {
                    if (musicaDao.inserir(musica)) {
                        musicaDao.selectMusica(musica);
                        for (int i=0; i< generos.size(); i++) {
                            musicaGeneroDao.inserirMusica(musica.getMusicaID(), generos.get(i).getGeneroID());
                            if (i + 1 == generos.size()) {
                                limparPanel(evt);   
                                JOptionPane.showMessageDialog(null, "Música cadastrada com sucesso", "Bem sucedido", 1);    
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Música não cadastrada", "Mal sucedido", 0);    
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                    e.printStackTrace();
                } 
            }
        }
    }

    private void verificarMusicaActionPerformed(ActionEvent evt) {
        limparPanel(evt);
        buscarMusicas();
        musicasComboBox.setVisible(true);
    }

    private void buscarMusicas() {
        try {
            MusicaDAO musicaDao = new MusicaDAO();
            Musica[] musicas = musicaDao.obterMusicas();
			musicasComboBox.setModel(new DefaultComboBoxModel<>(musicas));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar músicas, tente novamente mais tarde.", "Músicas Indisponíveis", 0);
			e.printStackTrace();
		}
    }

    private Genero[] buscarGeneros() {
        try {
			GeneroDAO generoDao = new GeneroDAO();
            Genero[] generos = generoDao.obterGeneros();
            return generos;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar gêneros, tente novamente mais tarde.", "Gêneros Indisponíveis", 0);
            e.printStackTrace();
            Genero[] generos = {};
            return generos;
        }
    }

    private void alterarCadMusicaActionPerformed(ActionEvent evt) {
        // Limpar panel
        if ((cadastrarButton.isVisible()) || (removerButton.isVisible())) {
            cadastrarButton.setVisible(false);
            removerButton.setVisible(false);            
        }

        if (!alterarButton.isVisible()) {
            buscarMusicas();
            musicasComboBox.setVisible(true);
            selecionarMusicaLabel.setVisible(true);
            musicaLabel.setText("Insira o novo nome da Música: ");
            musicaLabel.setVisible(true);
            musicaTextField.setText("");
            musicaTextField.setVisible(true);
            artistaLabel.setText("Insira o novo nome do Artista: ");
            artistaLabel.setVisible(true);
            artistaTextField.setText("");
            artistaTextField.setVisible(true);         
            alterarButton.setVisible(true);

        // } else if (!associarGeneros.isVisible()) {

        //     selecionarGeneroLabel.setVisible(true);
        //     associarGeneros.setVisible(true);
        //     try {
        //         Musica musica = (Musica) musicasComboBox.getSelectedItem();
        //         Genero[] generosSelect = new MusicaGeneroDAO().buscarGenero(musica.getMusicaID());
        //         for (int i = 0; i < generosSelect.length; i++) {
        //             generosList.setSelectedValue(generosSelect[i], true);
        //         }
        //         alterarButton.setText("CONFIRMAR");
        //     } catch (Exception e) {
        //         JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
        //         e.printStackTrace();
        //     }
        } else {
            Musica musica = (Musica) musicasComboBox.getSelectedItem();
            // List<Genero> generos = generosList.getSelectedValuesList();
            String novoNomeMusica = musicaTextField.getText();
            String novoNomeArtista = artistaTextField.getText();

            if ((novoNomeMusica.isEmpty()) || (novoNomeMusica.length() > 255 )) {
                JOptionPane.showMessageDialog(null, "Campo 'Nome Musica' incorreto!", "Campo Incorreto", 0);
            } else if ((novoNomeArtista.isEmpty()) || (novoNomeArtista.length() > 255 )) {
                JOptionPane.showMessageDialog(null, "Campo 'Nome Artista' incorreto!", "Campo Incorreto", 0);
            } else {
                musica.setNomeMusica(novoNomeMusica);
                musica.setNomeArtista(novoNomeArtista);
                try {
                    if (new MusicaDAO().alterar(musica)) {
                        limparPanel(evt);
                        JOptionPane.showMessageDialog(null, "Música alterado com sucesso.", "Bem sucedido", 1);    
                    } else {
                        JOptionPane.showMessageDialog(null, "Música não alterado.", "Mal sucedido", 0);    
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                    e.printStackTrace();
                }    
            }
        }
    }

    private void excluirMusicaActionPerformed(ActionEvent evt) {
        // Limpar panel
        if ((cadastrarButton.isVisible()) || (alterarButton.isVisible())) {
            cadastrarButton.setVisible(false);
            alterarButton.setVisible(false);
            musicaLabel.setVisible(false);
            musicaTextField.setVisible(false);
            artistaLabel.setVisible(false);
            artistaTextField.setVisible(false);
            selecionarGeneroLabel.setVisible(false);
            associarGeneros.setVisible(false);
        }

        if (!removerButton.isVisible()) {
            buscarGeneros();
            selecionarMusicaLabel.setText("Selecione a Música à excluir: ");
            selecionarMusicaLabel.setVisible(true);
            musicasComboBox.setVisible(true);
            removerButton.setVisible(true);
        } else {
            Musica musica = (Musica) musicasComboBox.getSelectedItem();
            int musicaID = musica.getMusicaID();
            try {
                if (new MusicaDAO().excluir(musicaID)) {
                    limparPanel(evt);
                    JOptionPane.showMessageDialog(null, "Música excluída com sucesso.", "Bem sucedido", 1);    
                } else {
                    JOptionPane.showMessageDialog(null, "Música não excluído.", "Mal sucedido", 0);    
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                e.printStackTrace();
            }    
        }
    }
    
    private void voltarButtonActionPerformed(ActionEvent evt, User adm) {
        new UsuarioAdm(adm).setVisible(true);
        this.dispose();
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
			java.util.logging.Logger.getLogger(ConfigurarMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ConfigurarMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ConfigurarMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ConfigurarMusica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User adm = new User("Teste", "testadm", "12345678", "A");
                new ConfigurarMusica(adm).setVisible(true);
			}
		});
    }
}