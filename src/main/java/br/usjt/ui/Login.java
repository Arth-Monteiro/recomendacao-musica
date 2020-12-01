package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

// Herda o Frame Principal
public class Login extends FramePrincipal {

    private static final long serialVersionUID = -8935881705739072112L;

    private JLabel usrLabel, pwdLabel;
    private JTextField usrTextField;
    private JPasswordField pwdPasswordField;
    private JButton loginButton;
    
    public Login() {
        initTelaLogin();
        this.setLocationRelativeTo(container);
    }

    private void initTelaLogin() {
        Font fonteLabels = new Font("sansserif", Font.BOLD, 13);
        Color corLabels = Color.WHITE;

        // Campos de username
        usrLabel = criarJLabel("Digite seu Username: ", fonteLabels, corLabels);
        usrTextField = criarJTextField();

        // Campos de password   
        pwdLabel = criarJLabel("Digite sua Senha: ", fonteLabels, corLabels);
        pwdPasswordField = new JPasswordField();

		loginButton = criarJButton("LOGIN");
        loginButton.addActionListener(evt -> loginButtonActionPerformed(evt));

        // Adiciona os componentes no panel
        panel.add(usrLabel);
        panel.add(usrTextField);

        panel.add(pwdLabel);
        panel.add(pwdPasswordField);

        panel.add(loginButton);

        // Define layout do panel como GroupLayout
        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

        // Cria gaps entre componentes e containers automaticamente
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
        
        // Organiza componentes no panel Horizontalmente
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(GroupLayout.Alignment.LEADING, 
                layout.createSequentialGroup()
                .addComponent(usrLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addComponent(usrTextField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(GroupLayout.Alignment.LEADING, 
                layout.createSequentialGroup()
                .addComponent(pwdLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addComponent(pwdPasswordField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(inicioButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );

        // Organiza componentes no panel Verticalmente
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(usrLabel)
                .addComponent(usrTextField)
            )
           .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(pwdLabel)
                .addComponent(pwdPasswordField)
            )
            .addGap(20)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                
                .addComponent(loginButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                
            )
            .addGap(10)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(inicioButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );

		pack(); // Agrupa tudo no panel
    }

    // Funcao para login
    private void loginButtonActionPerformed(ActionEvent evt) {
        String usr = usrTextField.getText();
        // pega a senha do usuário como char[] e converte para String
        String pwd = new String(pwdPasswordField.getPassword());
        
        try {
            UserDAO userDao = new UserDAO();
            User user = new User(usr, pwd);

            // Caso encontre o username e a senha, leva para a tela especifica do user
            // se for comum, vai para o UsuarioComum, se for adm, vai para o UsuarioAdm
            if (userDao.login(user)) {
                if (user.getTipoUser().equals("R")) {
                    new UsuarioComum(user).setVisible(true);
                    this.dispose();
                } else {
                    new UsuarioAdm(user).setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Username ou senha incorretas!", "Informação Incorreta", 0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas técnicos. Tente novamente mais tarde.", "Problemas de conexão", 0);
            e.printStackTrace();
        }
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
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Login().setVisible(true);
			}
		});
    }
}