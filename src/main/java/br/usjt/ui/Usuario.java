package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

public class Usuario extends FramePrincipal {

    private JLabel boasVindasLabel, oQueFazerLabel;
    private JComboBox<String> oQueFazerComboBox;
    private JLabel pwdLabel, confPwdJLabel;
    private JPasswordField pwdPasswordField, confPwdPasswordField;
    private JButton logOutButton, confirmarButton;

    public Usuario(User user) {
        initTelaUsuario(user);
        this.setLocationRelativeTo(container);
    }

    private void initTelaUsuario(User user) {
        Font fonteLabels = null;
        Color branco = Color.WHITE;
        String[] oQueFazer = {"1 - Avaliar Música", "2 - Alterar Avaliação", "3 - Receber Recomendação",
                              "4 - Alterar Senha", "5 - Excluir conta"};

        oQueFazerComboBox = new JComboBox<String>(oQueFazer);
        
        boasVindasLabel = criarJLabel("Bem vindo, " + user.getNome() + "!", fonteLabels, branco);
        oQueFazerLabel = criarJLabel("O que deseja fazer?", fonteLabels, branco);
        
        logOutButton = criarJButton("LOG OUT");
        confirmarButton = criarJButton("CONFIRMAR");

        pwdLabel = criarJLabel("Digite uma senha: ", fonteLabels, branco);
        pwdPasswordField = new JPasswordField();

        confPwdJLabel = criarJLabel("Confirme sua senha: ", fonteLabels, branco);
        confPwdPasswordField = new JPasswordField();

        confirmarButton.addActionListener(evt -> verificarOQueFazerActionPerformed(evt, user.getUserID()));
        oQueFazerComboBox.addActionListener(evt -> verificarOQueFazerActionPerformed(evt, user.getUserID()));
        logOutButton.addActionListener(evt -> logOutButtonActionPerformed(evt));

        panel.add(oQueFazerComboBox);
        panel.add(logOutButton);
        panel.add(pwdLabel);
        panel.add(pwdPasswordField);
        panel.add(confPwdJLabel);
        panel.add(confPwdPasswordField);
        panel.add(confirmarButton);

        confirmarButton.setVisible(false);
        pwdLabel.setVisible(false);
        pwdPasswordField.setVisible(false);
        confPwdJLabel.setVisible(false);
        confPwdPasswordField.setVisible(false);

        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(boasVindasLabel)
            )
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(oQueFazerLabel)
            )
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(oQueFazerComboBox)
            )
            .addGroup(GroupLayout.Alignment.LEADING, 
                layout.createSequentialGroup()
                .addComponent(pwdLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addComponent(pwdPasswordField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(GroupLayout.Alignment.LEADING, 
                layout.createSequentialGroup()
                .addComponent(confPwdJLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addComponent(confPwdPasswordField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(15)
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(confirmarButton)
            )
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                .addComponent(logOutButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
            )
            
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(boasVindasLabel)
            .addComponent(oQueFazerLabel)
            .addGap(30)
            .addComponent(oQueFazerComboBox)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pwdLabel)
                .addComponent(pwdPasswordField)
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(confPwdJLabel)
                .addComponent(confPwdPasswordField)
            )
            .addGap(10)
            .addComponent(confirmarButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
            .addGap(20)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(logOutButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );

		pack();
    }

    private void verificarOQueFazerActionPerformed(ActionEvent evt, int userID) {
        String opcao = (String) oQueFazerComboBox.getSelectedItem();
        switch (Integer.parseInt(opcao.substring(0, 1))) {
            // case 1: avaliarMusicaActionPerformed(evt); break;
            // case 2: alterAvalicaoActionPerformed(evt); break;
            // case 3: RecomendarActionPerformed(evt); break;
            case 4: alterSenhaActionPerformed(evt, userID); break;
            case 5: confirmarButtonActionPerformed(evt, userID); break;
        }
    }

    private void confirmarButtonActionPerformed(ActionEvent evt, int userID) {
        if (!confirmarButton.isVisible()){
            confirmarButton.setVisible(true);
        } else {
            UserDAO userDao = new UserDAO();
            try {
                userDao.excluirConta(userID);
                JOptionPane.showMessageDialog(null, "Conta excluída com sucesso!", "Exclusão de Conta", 1);
                Inicio inicio = new Inicio();
                inicio.setVisible(true);
                this.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                e.printStackTrace();
            }
        }
    }

    private void alterSenhaActionPerformed(ActionEvent evt, int userID) {
        if (!confirmarButton.isVisible()){
            pwdLabel.setVisible(true);
            pwdPasswordField.setVisible(true);
            confPwdJLabel.setVisible(true);
            confPwdPasswordField.setVisible(true);
            confirmarButton.setVisible(true);
        } else {
            String pwd = new String(pwdPasswordField.getPassword());
            String confPwd = new String(confPwdPasswordField.getPassword());
            if (!pwd.equals(confPwd)) {
                JOptionPane.showMessageDialog(null, "Suas senhas não estão iguais!", "Senhas Divergentes", 0);
            } else if ((pwd.isEmpty()) || (pwd.length() < 8) || (pwd.length() > 16 )) {
                JOptionPane.showMessageDialog(null, "Campo 'Senha' incorreto!", "Campo Incorreto", 0);
            } else {
                UserDAO userDao = new UserDAO();
                try {
                    if (userDao.atualizarSenha(pwd, userID)) {
                        pwdLabel.setVisible(false);
                        pwdPasswordField.setVisible(false);
                        pwdPasswordField.setText("");
                        confPwdJLabel.setVisible(false);
                        confPwdPasswordField.setVisible(false);
                        confPwdPasswordField.setText("");
                        confirmarButton.setVisible(false);
                        JOptionPane.showMessageDialog(null, "Senha alterada com sucesso.", "Senha alterada", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Senha não alterada.", "Senha não alterada", 0);
                    }
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                    e.printStackTrace();
                }
            }
        }
    }


    private void logOutButtonActionPerformed(ActionEvent evt) {
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
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
			java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User user = new User("testuser", "12345678");
                UserDAO userDao = new UserDAO();
                try {
                    if (userDao.login(user)) {
                        new Usuario(user).setVisible(true);
                    } else {
                        new Inicio().setVisible(true);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
                    e.printStackTrace();
                }
			}
		});
    }
}