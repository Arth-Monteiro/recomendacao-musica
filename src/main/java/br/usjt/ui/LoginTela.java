package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

public class LoginTela extends JFrame {

    private static final long serialVersionUID = 8375820291505276171L;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel panel;
    private JTextField usrTextField;
    private JPasswordField pwdPasswordField;
    private JButton loginButton;
    private JButton exitButton;
    // End of variables declaration//GEN-END:variables
    
    public LoginTela() {
        super("Login");
		initComponents();
		this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        panel = new JPanel();
        usrTextField = new JTextField();
        pwdPasswordField = new JPasswordField();
		loginButton = new JButton();
        exitButton = new JButton();

        setPreferredSize(new Dimension(500, 400));
        
		Container cont = getContentPane();
		cont.setLayout(new GridBagLayout());
        add(panel);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        usrTextField.setBorder(BorderFactory.createTitledBorder("Digite seu username"));
		pwdPasswordField.setBorder(BorderFactory.createTitledBorder("Digite sua senha"));
        
        loginButton.setText("Login");
		exitButton.setText("Sair");

        loginButton.addActionListener(evt -> loginButtonActionPerformed(evt));
        exitButton.addActionListener(evt -> exitButtonActionPerformed(evt));

        panel.add(usrTextField);
        panel.add(pwdPasswordField);
        panel.add(loginButton);
        panel.add(exitButton);

        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                   .addComponent(usrTextField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                   .addComponent(pwdPasswordField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                    .addGroup(
                        layout.createSequentialGroup()
                            .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                            .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                    )
        );

        layout.setVerticalGroup(
           layout.createSequentialGroup()
                .addComponent(usrTextField, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addComponent(pwdPasswordField, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                )
        );

		pack();
        
    }

    private void exitButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_exitButtonActionPerformed
		this.dispose();
    }// GEN-LAST:event_exitButtonActionPerformed
    
    private void loginButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_loginButtonActionPerformed
        String usr = usrTextField.getText();
        // pega a senha do usuário como char[] e converte para String
        String pwd = new String(pwdPasswordField.getPassword());
        
        try {
            UserDAO userDao = new UserDAO();
            User usuario = new User(usr, pwd);
            if (userDao.login(usuario)) {
                if (usuario.getTipoUser().equals("R")) {
                    // UsuarioTela usuarioTela = new UsuarioTela(usuario);
                    // usuarioTela.setVisible(true);
                    // this.dispose();
                } else {
                    // AdmTela admTela = new AdmTela(usuario);
                    // admTela.setVisible(true);
                    // this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username ou senha incorretas!", "Informação Incorreta", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
            e.printStackTrace();
        }
    }// GEN-LAST:event_loginButtonActionPerformed

    public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(LoginTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(LoginTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(LoginTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(LoginTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new LoginTela().setVisible(true);
			}
		});
	}
}
