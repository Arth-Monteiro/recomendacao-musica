package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;

public class Inicio extends FramePrincipal {

    private static final long serialVersionUID = -6627393089076807210L;

    private JLabel messageLabel;
	private JButton signInButton, loginButton;
    
    public Inicio() {
        initTelaInicio();
        this.setLocationRelativeTo(container);
    }

    private void initTelaInicio() {
        Font fontLabel = new Font("sansserif", Font.BOLD, 16);
        messageLabel = criarJLabel("Bem vindo ao Sistema de Recomendação Musical ACME", fontLabel, null);
        
		signInButton = criarJButton("SIGN IN");
		loginButton = criarJButton("LOGIN");

        signInButton.addActionListener(evt -> signInButtonActionPerformed(evt));
		loginButton.addActionListener(evt -> loginButtonActionPerformed(evt));

        panel.add(messageLabel);
		panel.add(signInButton);
        panel.add(loginButton);
        
        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(messageLabel)
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(signInButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(loginButton,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(GroupLayout.Alignment.CENTER, 
                layout.createSequentialGroup()
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup()
            .addComponent(messageLabel,GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(signInButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );
        pack();
    }

    private void signInButtonActionPerformed(ActionEvent evt) {
        User user = new User("", "", "", "R");
        SignIn signIn = new SignIn(user);
        signIn.setVisible(true);
        this.dispose();
    }
    
    private void loginButtonActionPerformed(ActionEvent evt) {
        Login login = new Login();
        login.setVisible(true);
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
			java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Inicio().setVisible(true);
			}
		});
    }
}