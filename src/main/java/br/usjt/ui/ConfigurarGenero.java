package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.model.Genero;
import br.usjt.dao.GeneroDAO;

public class ConfigurarGenero extends FramePrincipal {

    private static final long serialVersionUID = -3156715740517404854L;
    
    private JComboBox<String> oQueFazerComboBox;
    private JComboBox<Genero> generosComboBox;
    private JButton cadastrarButton, alterarButton, removerButton, voltarButton;
    private JLabel generoLabel, selecionarLabel;
    private JTextField generoTextField;

    public ConfigurarGenero(User adm) {
        initTelaConfigurarGeneros(adm);
        this.setLocationRelativeTo(container);
    }

    private void initTelaConfigurarGeneros(User adm) {

        Font fonteLabels = new Font("sansserif", Font.BOLD, 13);
        Color branco = Color.WHITE;

        oQueFazerComboBox = new JComboBox<String>();
        generosComboBox = new JComboBox<Genero>();
        comboBoxoQueFazer();

        cadastrarButton = criarJButton("CADASTRAR");
        alterarButton = criarJButton("ALTERAR");
        removerButton = criarJButton("REMOVER");
        voltarButton = criarJButton("VOLTAR");
        inicioButton.setText("LOG OUT");

        selecionarLabel = criarJLabel("Selecione o Gênero à alterar: ", fonteLabels, branco);
        generoLabel = criarJLabel("", fonteLabels, branco);
        generoTextField = criarJTextField();
         

        oQueFazerComboBox.addActionListener(evt -> verificarOQueFazerActionPerformed(evt));
        cadastrarButton.addActionListener(evt -> cadastrarGeneroActionPerformed(evt));
        alterarButton.addActionListener(evt -> alterarCadGeneroActionPerformed(evt));
        removerButton.addActionListener(evt -> excluirGeneroActionPerformed(evt));
        voltarButton.addActionListener(evt -> voltarButtonActionPerformed(evt, adm));

        panel.add(oQueFazerComboBox);
        panel.add(generosComboBox);
        panel.add(cadastrarButton);
        panel.add(alterarButton);
        panel.add(removerButton);
        panel.add(voltarButton);
        panel.add(selecionarLabel);
        panel.add(generoLabel);
        panel.add(generoTextField);
        panel.add(voltarButton);

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
                    .addComponent(selecionarLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                )   
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(generosComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                )
            )
            // Cadastro Gênero e Alterar Cadastro Gênero
            .addGroup(
                layout.createSequentialGroup()
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(generoLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
                )   
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(generoTextField, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                )
            )
            .addComponent(cadastrarButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
            .addComponent(alterarButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
            .addComponent(removerButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(selecionarLabel)
                .addComponent(generosComboBox)
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(generoLabel)
                .addComponent(generoTextField)
            )
            .addComponent(cadastrarButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(alterarButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(removerButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            .addGap(10)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(inicioButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(voltarButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                )
            .addComponent(exitButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
        
    }

    private void comboBoxoQueFazer() {
        
        String[] oQueFazer = {"Escolha uma opção...", 
                                "Cadastrar Gênero",
                                "Verificar Gêneros",
                                "Alterar Cadastro Gênero", 
                                "Excluir Gênero"};

        oQueFazerComboBox.setModel(new DefaultComboBoxModel<>(oQueFazer));
    }

    private void buscarGeneros() {
        try {
			GeneroDAO generoDao = new GeneroDAO();
            Genero[] generos = generoDao.obterGeneros();
			generosComboBox.setModel(new DefaultComboBoxModel<>(generos));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar gêneros, tente novamente mais tarde.", "Gêneros Indisponíveis", 0);
			e.printStackTrace();
		}
    }

    private void limparPanel(ActionEvent evt) {
        generosComboBox.setVisible(false);
        cadastrarButton.setVisible(false);
        alterarButton.setVisible(false);
        removerButton.setVisible(false);
        selecionarLabel.setVisible(false);
        generoLabel.setVisible(false);
        generoTextField.setVisible(false);
    }

    private void verificarOQueFazerActionPerformed(ActionEvent evt) {
        int opcao = oQueFazerComboBox.getSelectedIndex();
        switch (opcao) {
            case 0: limparPanel(evt); break;
            case 1: cadastrarGeneroActionPerformed(evt); break;
            case 2: verificarGeneroActionPerformed(evt); break;
            case 3: alterarCadGeneroActionPerformed(evt); break;
            case 4: excluirGeneroActionPerformed(evt); break;
        }
    }

    private void cadastrarGeneroActionPerformed(ActionEvent evt) {
        // Limpar Panel
        if ((alterarButton.isVisible()) || (removerButton.isVisible()) || (generosComboBox.isVisible())) {
            alterarButton.setVisible(false);
            removerButton.setVisible(false);
            generosComboBox.setVisible(false);
            selecionarLabel.setVisible(false);
        }
        
        if (!cadastrarButton.isVisible()) {
            generoLabel.setText("Insira o nome do Gênero: ");
            generoLabel.setVisible(true);
            generoTextField.setText("");
            generoTextField.setVisible(true);
            cadastrarButton.setVisible(true);
        } else {
            String nomeGenero = generoTextField.getText().toLowerCase();
            nomeGenero = nomeGenero.substring(0,1).toUpperCase() + nomeGenero.substring(1);
            if ((nomeGenero.isEmpty()) || (nomeGenero.length() > 50 )) {
                JOptionPane.showMessageDialog(null, "Campo 'Nome Genero' incorreto!", "Campo Incorreto", 0);
            } else {
                try {
                    GeneroDAO generoDao = new GeneroDAO();
                    if (!generoDao.verificarGenero(nomeGenero)) {
                        if(generoDao.inserir(nomeGenero)) {
                            limparPanel(evt);
                            comboBoxoQueFazer();
                            JOptionPane.showMessageDialog(null, "Gênero cadastrado com sucesso", "Bem sucedido", 1);
                        } else {
                            JOptionPane.showMessageDialog(null, "Gênero não cadastrado!", "Mal sucedido", 0);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Gênero já existente", "Já existente", 0);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                    e.printStackTrace();
                }       
            }
        }
    }

    private void verificarGeneroActionPerformed(ActionEvent evt) {
        limparPanel(evt);
        buscarGeneros();
        generosComboBox.setVisible(true);
    }

    private void alterarCadGeneroActionPerformed(ActionEvent evt) {
        // Limpar panel
        if ((cadastrarButton.isVisible()) || (removerButton.isVisible())) {
            cadastrarButton.setVisible(false);
            removerButton.setVisible(false);
        }

        if (!alterarButton.isVisible()) {
            buscarGeneros();
            generosComboBox.setVisible(true);
            selecionarLabel.setVisible(true);
            generoLabel.setText("Insira o novo nome do Gênero: ");
            generoLabel.setVisible(true);
            generoTextField.setText("");
            generoTextField.setVisible(true);
            alterarButton.setVisible(true);
        } else {
            
            Genero genero = (Genero) generosComboBox.getSelectedItem();
            String novoNomeGenero = generoTextField.getText();
            if ((novoNomeGenero.isEmpty()) || (novoNomeGenero.length() > 50 )) {
                JOptionPane.showMessageDialog(null, "Campo 'Nome Genero' incorreto!", "Campo Incorreto", 0);
            } else {
                genero.setNomeGenero(novoNomeGenero);
                try {
                    if (new GeneroDAO().alterar(genero)) {
                        limparPanel(evt);
                        comboBoxoQueFazer();
                        JOptionPane.showMessageDialog(null, "Gênero alterado com sucesso.", "Bem sucedido", 1);    
                    } else {
                        JOptionPane.showMessageDialog(null, "Gênero não alterado.", "Mal sucedido", 0);    
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                    e.printStackTrace();
                }    
            }
        }
    }

    private void excluirGeneroActionPerformed(ActionEvent evt) {
        // Limpar panel
        if ((cadastrarButton.isVisible()) || (alterarButton.isVisible())) {
            cadastrarButton.setVisible(false);
            alterarButton.setVisible(false);
            generoLabel.setVisible(false);
            generoTextField.setVisible(false);
        }

        if (!removerButton.isVisible()) {
            buscarGeneros();
            selecionarLabel.setText("Selecione o Gênero à excluir: ");
            selecionarLabel.setVisible(true);
            generosComboBox.setVisible(true);
            removerButton.setVisible(true);
        } else {
            Genero genero = (Genero) generosComboBox.getSelectedItem();
            int generoID = genero.getGeneroID();
            try {
                if (new GeneroDAO().excluir(generoID)) {
                    limparPanel(evt);
                    comboBoxoQueFazer();
                    JOptionPane.showMessageDialog(null, "Gênero excluído com sucesso.", "Bem sucedido", 1);    
                } else {
                    JOptionPane.showMessageDialog(null, "Gênero não excluído.", "Mal sucedido", 0);    
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
			java.util.logging.Logger.getLogger(ConfigurarGenero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ConfigurarGenero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ConfigurarGenero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ConfigurarGenero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User adm = new User("Teste", "testadm", "12345678", "A");
                new ConfigurarGenero(adm).setVisible(true);
			}
		});
    }
}