package br.usjt.ui.inicio;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class InicioTela extends JFrame {

	private static final long serialVersionUID = -1294657104404327135L;

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private JPanel panel;
	private JButton signInButton;
    private JButton loginButton;
	private JButton exitButton;
	// End of variables declaration//GEN-END:variables
	
	/**
	 * Creates new form InicioTela
	 */
    public InicioTela() {
		super("Recomendação de Músicas");
		initComponents();
		this.setLocationRelativeTo(null);
    }
    
    private void initComponents() {

		panel = new JPanel();
		signInButton = new JButton();
		loginButton = new JButton();
		exitButton = new JButton();


		Container cont = getContentPane();
		cont.setLayout(new GridBagLayout());
		add(panel);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		signInButton.setText("Sign in");
		signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                signInButtonActionPerformed(evt);
            }
        });
        
		loginButton.setText("Login");
		loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });
        
        exitButton.setText("Sair");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
		});
		
		panel.add(signInButton);
		panel.add(loginButton);
		panel.add(exitButton);

		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
		layout.setHorizontalGroup(
			layout.createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(
				layout.createSequentialGroup().addGroup(
					layout.createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(
						layout.createSequentialGroup()
							.addComponent(signInButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addComponent(loginButton,  GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					)
					.addComponent(exitButton,  GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
				)
			)
		);

		layout.setVerticalGroup(
			layout.createSequentialGroup().addGroup(
				layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
					.addComponent(signInButton,  GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addComponent(loginButton,  GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
			).addComponent(exitButton,  GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
		);

		setPreferredSize(new Dimension(500, 400));
		pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void exitButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_exitButtonActionPerformed
		this.dispose();
    }// GEN-LAST:event_exitButtonActionPerformed
    
    private void signInButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_loginButtonActionPerformed
        // SignInTela signIn = new SignInTela();
        // signIn.setVisible(true);
        // this.dispose();
    }// GEN-LAST:event_loginButtonActionPerformed
    
    private void loginButtonActionPerformed(ActionEvent evt) {// GEN-FIRST:event_loginButtonActionPerformed
        // LoginTela login = new LoginTela();
        // login.setVisible(true);
        // this.dispose();
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
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(InicioTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(InicioTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(InicioTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(InicioTela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new InicioTela().setVisible(true);
			}
		});
	}
}
