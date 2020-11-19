package br.usjt.ui.inicio;

// import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignInTela extends JFrame {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel panel;
    private JTextField nomeTextField;
    private JTextField usrTextField;
    private JPasswordField pwdPasswordField;
    private JPasswordField confPwdPasswordField;
	private JButton signInButton;
    private JButton exitButton;
    // End of variables declaration//GEN-END:variables
    
    public SignInTela() {
		super("Sign In");
		initComponents();
		this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        panel = new JPanel();
        nomeTextField = new JTextField();
        usrTextField = new JTextField();
        pwdPasswordField = new JPasswordField();
        confPwdPasswordField = new JPasswordField();
		signInButton = new JButton();
        exitButton = new JButton();
        
		Container cont = getContentPane();
		cont.setLayout(new GridBagLayout());
        add(panel);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        nomeTextField.setBorder(BorderFactory.createTitledBorder("Digite seu 1º nome"));
        usrTextField.setBorder(BorderFactory.createTitledBorder("Digite seu username"));
		pwdPasswordField.setBorder(BorderFactory.createTitledBorder("Digite sua senha"));
		confPwdPasswordField.setBorder(BorderFactory.createTitledBorder("Confirme a senha"));
        
        signInButton.setText("Sign in");
		signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });

        exitButton.setText("Sair");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        panel.add(nomeTextField);
        panel.add(usrTextField);
        panel.add(pwdPasswordField);
        panel.add(confPwdPasswordField);
        panel.add(signInButton);
        panel.add(exitButton);
        
    }

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
				new SignInTela().setVisible(true);
			}
		});
	}
}
