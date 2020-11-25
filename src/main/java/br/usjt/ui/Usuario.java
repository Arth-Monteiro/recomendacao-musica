package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

public class Usuario extends FramePrincipal {
 
    private static final long serialVersionUID = -7042799536988979356L;
    
    protected JLabel boasVindasLabel, oQueFazerLabel;
    protected JComboBox<String> oQueFazerComboBox;
    protected JLabel pwdLabel, confPwdJLabel;
    protected JPasswordField pwdPasswordField, confPwdPasswordField;
    protected JButton confResetPwdButton, confExcludeAccountButton;

    public Usuario(User user) {
        initTelaUsuario(user);
        this.setLocationRelativeTo(container);
    }

    private void initTelaUsuario(User user) {
        Font fonteLabels = new Font("sansserif", Font.BOLD, 13);
        Color branco = Color.WHITE;

        this.oQueFazerComboBox = new JComboBox<String>();

        this.boasVindasLabel = criarJLabel("Bem vindo, " + user.getNome() + "!", fonteLabels, branco);
        this.oQueFazerLabel = criarJLabel("O que deseja fazer?", fonteLabels, branco);
        
        inicioButton.setText("LOG OUT");
        this.confResetPwdButton = criarJButton("CONFIRMAR");
        this.confExcludeAccountButton = criarJButton("CONFIRMAR");

        this.pwdLabel = criarJLabel("Digite uma senha: ", fonteLabels, branco);
        this.pwdPasswordField = new JPasswordField();

        this.confPwdJLabel = criarJLabel("Confirme sua senha: ", fonteLabels, branco);
        this.confPwdPasswordField = new JPasswordField();

        confResetPwdButton.addActionListener(evt -> alterSenhaActionPerformed(evt,user.getUserID()));
        confExcludeAccountButton.addActionListener(evt -> excludeAccountActionPerformed(evt,user.getUserID()));

        panel.add(oQueFazerComboBox);
        panel.add(pwdLabel);
        panel.add(pwdPasswordField);
        panel.add(confPwdJLabel);
        panel.add(confPwdPasswordField);
        panel.add(confResetPwdButton);
        panel.add(confExcludeAccountButton);

        limparPanel(null);

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
                .addComponent(oQueFazerComboBox, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(GroupLayout.Alignment.LEADING, 
                layout.createSequentialGroup()
                .addComponent(pwdLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addComponent(pwdPasswordField, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(GroupLayout.Alignment.LEADING, 
                layout.createSequentialGroup()
                .addComponent(confPwdJLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addComponent(confPwdPasswordField, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(15)
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(confResetPwdButton)
                .addComponent(confExcludeAccountButton)
            )
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                .addComponent(inicioButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
            )  
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(boasVindasLabel)
            .addComponent(oQueFazerLabel)
            .addGap(30)
            .addComponent(oQueFazerComboBox)
            .addGap(20)
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
            .addComponent(confResetPwdButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            .addComponent(confExcludeAccountButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
            .addGap(10)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(inicioButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );

		pack();
    }
    
    protected void limparPanel(ActionEvent evt) {
        pwdLabel.setVisible(false);
        pwdPasswordField.setVisible(false);
        pwdPasswordField.setText("");
        confPwdJLabel.setVisible(false);
        confPwdPasswordField.setVisible(false);
        confPwdPasswordField.setText("");
        confResetPwdButton.setVisible(false);
        confExcludeAccountButton.setVisible(false);
    }

    protected void excludeAccountActionPerformed(ActionEvent evt, int userID) {
        
        // Limpar Panel Reset Senha
        if (confResetPwdButton.isVisible()) {
            pwdLabel.setVisible(false);
            pwdPasswordField.setVisible(false);
            pwdPasswordField.setText("");
            confPwdJLabel.setVisible(false);
            confPwdPasswordField.setVisible(false);
            confPwdPasswordField.setText("");
            confResetPwdButton.setVisible(false);
        }
        
        if (!confExcludeAccountButton.isVisible()){
            confExcludeAccountButton.setVisible(true);
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

    protected void alterSenhaActionPerformed(ActionEvent evt, int userID) {
        // Limpar Panel 
        if (confExcludeAccountButton.isVisible()) {
            confExcludeAccountButton.setVisible(false);
        }
        
        if (!confResetPwdButton.isVisible()){
            pwdLabel.setVisible(true);
            pwdPasswordField.setVisible(true);
            confPwdJLabel.setVisible(true);
            confPwdPasswordField.setVisible(true);
            confResetPwdButton.setVisible(true);
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
                        limparPanel(evt);
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
}