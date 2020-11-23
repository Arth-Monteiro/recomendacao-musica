package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.model.Genero;
import br.usjt.dao.GeneroDAO;
import br.usjt.dao.UserGeneroDAO;

import java.util.Arrays;
import java.util.List;

public class GenerosFavoritos extends FramePrincipal {

    private static final long serialVersionUID = -6253463412460970549L;
    
    private JComboBox<String> oQueFazerComboBox;
    private JComboBox<Genero> generosFavoritosComboBox;
    private JLabel adicionarGenerosLabel, generosFavoritosLabel;
    private JList<Genero> generosList;
    private JScrollPane selectGeneros;
    private JButton adicionarButton, removerButton, voltarButton;

    public GenerosFavoritos(User user) {
        initTelaGenerosFavoritos(user);
        this.setLocationRelativeTo(container);
    }

    private void initTelaGenerosFavoritos(User user) {
        Font fonteLabels = new Font("sansserif", Font.BOLD, 13);
        Color branco = Color.WHITE;

        String[] oQueFazer = {"Escolha uma opção...",
                                "Adicionar Gêneros Favoritos",
                                "Visualizar Gêneros Favoritos",
                                "Excluir Gêneros Favoritos"};

        oQueFazerComboBox = new JComboBox<String>(oQueFazer);
        generosFavoritosComboBox = new JComboBox<Genero>();

        adicionarGenerosLabel = criarJLabel("", fonteLabels, branco);
        generosList = new JList<Genero>(buscarGenerosNaoFavoritos(user));
        selectGeneros = new JScrollPane();
        selectGeneros.setViewportView(generosList);

        generosFavoritosLabel = criarJLabel("", fonteLabels, branco);

        adicionarButton = criarJButton("CADASTRAR");
        removerButton = criarJButton("REMOVER");
        voltarButton = criarJButton("VOLTAR");
        inicioButton.setText("LOG OUT");
        
        oQueFazerComboBox.addActionListener(evt -> verificarOQueFazerActionPerformed(evt, user));
        adicionarButton.addActionListener(evt -> adicionarGenerosActionPerformed(evt, user));
        removerButton.addActionListener(evt -> excluirGenerosActionPerformed(evt));
        voltarButton.addActionListener(evt -> voltarButtonActionPerformed(evt, user));

        panel.add(oQueFazerComboBox);
        panel.add(generosFavoritosComboBox);
        panel.add(adicionarGenerosLabel);
        panel.add(selectGeneros);
        panel.add(generosFavoritosLabel);
        panel.add(adicionarButton);
        panel.add(removerButton);
        panel.add(voltarButton);

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
                .addComponent(generosFavoritosLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(generosFavoritosComboBox, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(adicionarGenerosLabel)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(selectGeneros)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(adicionarButton, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
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
            .addComponent(generosFavoritosLabel)
            .addComponent(generosFavoritosComboBox)
            .addComponent(adicionarGenerosLabel)
            .addComponent(selectGeneros, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(adicionarButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(removerButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
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
        generosFavoritosComboBox.setVisible(false);
        adicionarGenerosLabel.setVisible(false);
        selectGeneros.setVisible(false);
        generosFavoritosLabel.setVisible(false);
        adicionarButton.setVisible(false);
        removerButton.setVisible(false);
    }

    private void buscarGenerosFavoritos(User user) {
        try {
            UserGeneroDAO userGeneroDao = new UserGeneroDAO();
            Genero[] generosUser = userGeneroDao.selectGeneros(user.getUserID());
            generosFavoritosComboBox.setModel(new DefaultComboBoxModel<>(generosUser));
            
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar gêneros, tente novamente mais tarde.", "Gêneros Indisponíveis", 0);
			e.printStackTrace();
		}
    }

    private Genero[] buscarGenerosNaoFavoritos(User user) {

        try {

            GeneroDAO generoDao = new GeneroDAO();
            UserGeneroDAO userGeneroDao = new UserGeneroDAO();

            Genero[] generos = generoDao.obterGeneros();
            Genero[] generosUser = userGeneroDao.selectGeneros(user.getUserID());
            int totalGenerosNaoFavoritos = generos.length - generosUser.length;
            Genero[] generosNaoFavoritos = new Genero[totalGenerosNaoFavoritos];

            int contador = 0;
            for (int i = 0; i < generos.length; i++) {
                if (!Arrays.asList(generosUser).contains(generos[i])) {
                    generosNaoFavoritos[contador++] = generos[i];
                }    
            }
           
            return generosNaoFavoritos;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao buscar gêneros, tente novamente mais tarde.", "Gêneros Indisponíveis", 0);
            e.printStackTrace();
            Genero[] generos = {};
            return generos;
        }
    }

    private void verificarOQueFazerActionPerformed(ActionEvent evt, User user) {
        int opcao = oQueFazerComboBox.getSelectedIndex();
        switch (opcao) {
            case 0: limparPanel(evt); break;
            case 1: adicionarGenerosActionPerformed(evt, user); break;
            case 2: buscarGenerosFavoritos(user); visualizarGenerosFavActionPerformed(evt) ; break;
            case 3: buscarGenerosFavoritos(user); excluirGenerosActionPerformed(evt); break;
        }
    }

    private void adicionarGenerosActionPerformed(ActionEvent evt, User user) {
        // Limpar Panel
        if((removerButton.isVisible()) || (generosFavoritosComboBox.isVisible())) {
            removerButton.setVisible(false);
            generosFavoritosComboBox.setVisible(false);
            generosFavoritosLabel.setVisible(false);
        }

        if (!adicionarButton.isVisible()) {
            adicionarGenerosLabel.setText("<html>Selecione o Gênero<br>(Ctrl para mais de um item):</html>");
            adicionarGenerosLabel.setVisible(true);
            selectGeneros.setVisible(true);
            adicionarButton.setVisible(true);
        } else {

            List<Genero> generos = generosList.getSelectedValuesList();
            try {
                for (int i = 0; i < generos.size(); i++) {
                    new UserGeneroDAO().inserirGenero(user.getUserID(), generos.get(i).getGeneroID());
                }
                limparPanel(evt);   
                JOptionPane.showMessageDialog(null, "Gêneros cadastrados com sucesso", "Bem sucedido", 1);    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                e.printStackTrace();
            } 
            
        }
    }

    private void visualizarGenerosFavActionPerformed(ActionEvent evt) {
        // Limpar Panel
        if ((adicionarButton.isVisible()) || (removerButton.isVisible())) {
            adicionarGenerosLabel.setVisible(false);
            selectGeneros.setVisible(false);
            adicionarButton.setVisible(false);
            removerButton.setVisible(false);
        }

        generosFavoritosLabel.setText("Seus Gêneros favoritos: ");
        generosFavoritosLabel.setVisible(true);
        generosFavoritosComboBox.setVisible(true);
    }

    private void excluirGenerosActionPerformed(ActionEvent evt) {
        // Limpar Panel
        if ((adicionarButton.isVisible()) || (generosFavoritosComboBox.isVisible())) {
            adicionarGenerosLabel.setVisible(false);
            selectGeneros.setVisible(false);
            adicionarButton.setVisible(false);
        }

        if (!removerButton.isVisible()) {
            generosFavoritosLabel.setText("Selecione o Gênero a remover: ");
            generosFavoritosLabel.setVisible(true);
            generosFavoritosComboBox.setVisible(true);

            removerButton.setVisible(true);
        } else {
            Genero genero = (Genero) generosFavoritosComboBox.getSelectedItem();
            try {
                if (new UserGeneroDAO().excluirGenero(genero.getGeneroID())) {
                    limparPanel(evt);
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

    private void voltarButtonActionPerformed(ActionEvent evt, User user) {
        new UsuarioComum(user).setVisible(true);
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
			java.util.logging.Logger.getLogger(GenerosFavoritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GenerosFavoritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GenerosFavoritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GenerosFavoritos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User user = new User("Teste", "testuser", "12345678", "R");
                user.setUserID(1);
                new GenerosFavoritos(user).setVisible(true);
			}
		});
    }
}
