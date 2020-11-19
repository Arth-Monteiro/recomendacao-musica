package br.usjt.ui.inicio;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

public class SignInTela extends JFrame {
    
    private static final long serialVersionUID = 8375820291505276171L;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel panel;
    private JTextField nomeTextField;
    private JTextField usrTextField;
    private JPasswordField pwdPasswordField;
    private JPasswordField confPwdPasswordField;
	private JButton signInButton;
    private JButton exitButton;
    private JCheckBox tipoUsrCheckBox;
    // End of variables declaration//GEN-END:variables
    
    public SignInTela(String tipoUser) {
        super("Sign In");
		initComponents(tipoUser);
		this.setLocationRelativeTo(null);
    }

    private void initComponents(String tipoUser) {
        panel = new JPanel();
        nomeTextField = new JTextField();
        usrTextField = new JTextField();
        pwdPasswordField = new JPasswordField();
        confPwdPasswordField = new JPasswordField();
		signInButton = new JButton();
        exitButton = new JButton();
        tipoUsrCheckBox = new JCheckBox("Adm");

        setPreferredSize(new Dimension(500, 400));
        
		Container cont = getContentPane();
		cont.setLayout(new GridBagLayout());
        add(panel);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        nomeTextField.setBorder(BorderFactory.createTitledBorder("Digite seu 1º nome"));
        usrTextField.setBorder(BorderFactory.createTitledBorder("Digite seu username"));
		pwdPasswordField.setBorder(BorderFactory.createTitledBorder("Digite sua senha"));
		confPwdPasswordField.setBorder(BorderFactory.createTitledBorder("Confirmar senha"));
        
        signInButton.setText("Sign in");
		exitButton.setText("Sair");

        signInButton.addActionListener(evt -> signInButtonActionPerformed(evt));
        exitButton.addActionListener(evt -> exitButtonActionPerformed(evt));

        if (!(tipoUser.equals("A"))) {
            tipoUsrCheckBox.setVisible(false);
        }

        panel.add(nomeTextField);
        panel.add(usrTextField);
        panel.add(pwdPasswordField);
        panel.add(confPwdPasswordField);
        panel.add(signInButton);
        panel.add(exitButton);
        panel.add(tipoUsrCheckBox);

        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                .addComponent(usrTextField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                .addComponent(pwdPasswordField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                .addComponent(confPwdPasswordField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
                .addComponent(tipoUsrCheckBox)
                .addGroup(
                    layout.createSequentialGroup()
                        .addComponent(signInButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                )
        );

        layout.setVerticalGroup(
           layout.createSequentialGroup()
                .addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addComponent(usrTextField, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addComponent(pwdPasswordField, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addComponent(confPwdPasswordField, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                .addComponent(tipoUsrCheckBox)
                .addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(signInButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                )
        );

		pack();
        
    }

    private void exitButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_exitButtonActionPerformed
		this.dispose();
    }// GEN-LAST:event_exitButtonActionPerformed
    
    private void signInButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_loginButtonActionPerformed
        String nome = nomeTextField.getText();
        String usr = usrTextField.getText();
        // pega a senha do usuário como char[] e converte para String
		String pwd = new String(pwdPasswordField.getPassword());
        String confPwd = new String(confPwdPasswordField.getPassword());
        // String tipoUser = (String) tipoUserComboBox.getSelectedItem();
        if (!pwd.equals(confPwd)) {
            JOptionPane.showMessageDialog(null, "Suas senhas não estão iguais!", "Senhas Divergentes", 0);
        } else if ((nome.isEmpty()) || (nome.length() > 16 )) {
            JOptionPane.showMessageDialog(null, "Campo 'Primeiro Nome' incorreto!", "Campo Incorreto", 0);
        } else if ((usr.isEmpty()) || (usr.length() > 20 )) {
            JOptionPane.showMessageDialog(null, "Campo 'Username' incorreto!", "Campo Incorreto", 0);
        } else if ((pwd.isEmpty()) || (pwd.length() <= 8) || (pwd.length() > 16 )) {
            JOptionPane.showMessageDialog(null, "Campo 'Senha' incorreto!", "Campo Incorreto", 0);
        } else {
            // String tipo = (tipoUser =="Regular") ? "R" : "A";
            try {
                UserDAO userDao = new UserDAO();
                User usuario = new User(nome, usr, pwd, "R");
                if (!userDao.verificarUsername(usuario.getUsername())) {
                    if (userDao.signin(usuario)) {
                        userDao.login(usuario);
                        //Ir para tela de usuario dependendo do tipo
                        if (usuario.getTipoUser().equals("R")) {
                            // UsuarioTela usuarioTela = new UsuarioTela(usuario);
                            // usuarioTela.setVisible(true);
                            // this.dispose();
                        } else {
                            // AdmTela admTela = new AdmTela(usuario);
                            // admTela.setVisible(true);
                            // this.dispose();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Username já existe!", "Username Existente", 0);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
			    e.printStackTrace();
            }
            
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
			java.util.logging.Logger.getLogger(SignInTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SignInTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SignInTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SignInTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SignInTela("R").setVisible(true);
			}
		});
	}
}
