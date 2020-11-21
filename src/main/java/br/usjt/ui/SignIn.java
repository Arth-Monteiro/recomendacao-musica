package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

public class SignIn extends FramePrincipal {
    
    private static final long serialVersionUID = -6938217356257323852L;

    private JLabel nomeLabel, usrLabel, pwdLabel, confPwdJLabel;
    private JTextField nomeTextField, usrTextField;
    private JPasswordField pwdPasswordField, confPwdPasswordField;
	private JButton signInButton;
    private JCheckBox tipoUsrCheckBox;

    // public SignIn(String tipoUser) {
    public SignIn(User user) {
        // initTelaSignIn(tipoUser);
        initTelaSignIn(user);
        this.setLocationRelativeTo(container);
    }

    // private void initTelaSignIn(String tipoUser) {
    private void initTelaSignIn(User user) {
        Font fonteLabels = null;
        Color branco = Color.WHITE;

        nomeLabel = criarJLabel("Digite seu primeiro nome: ", fonteLabels, branco);
        nomeTextField = criarJTextField();

        usrLabel = criarJLabel("Digite ser Username: ", fonteLabels, branco);
        usrTextField = criarJTextField();

        pwdLabel = criarJLabel("Digite uma senha: ", fonteLabels, branco);
        pwdPasswordField = new JPasswordField();

        confPwdJLabel = criarJLabel("Confirme sua senha: ", fonteLabels, branco);
        confPwdPasswordField = new JPasswordField();

		signInButton = criarJButton("SIGN IN");
        tipoUsrCheckBox = new JCheckBox("Adm");

        tipoUsrCheckBox.setForeground(branco);

        signInButton.addActionListener(evt -> signInButtonActionPerformed(evt, user));

        // if (!(tipoUser.equals("A"))) {
        //     tipoUsrCheckBox.setVisible(false);
        // }
        if (!(user.getTipoUser().equals("A"))) {
            tipoUsrCheckBox.setVisible(false);
        }

        panel.add(nomeTextField);
        panel.add(nomeTextField);

        panel.add(usrLabel);
        panel.add(usrTextField);

        panel.add(pwdLabel);
        panel.add(pwdPasswordField);

        panel.add(confPwdJLabel);
        panel.add(confPwdPasswordField);

        panel.add(signInButton);
        panel.add(tipoUsrCheckBox);

        GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addGroup(GroupLayout.Alignment.LEADING, 
                layout.createSequentialGroup()
                .addComponent(nomeLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addComponent(nomeTextField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
            )
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
            .addGroup(GroupLayout.Alignment.LEADING, 
                layout.createSequentialGroup()
                .addComponent(confPwdJLabel, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
                .addComponent(confPwdPasswordField, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(tipoUsrCheckBox)
            )
            .addGroup(
                layout.createSequentialGroup()
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(signInButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(nomeLabel)
                .addComponent(nomeTextField)
            )
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
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(confPwdJLabel)
                .addComponent(confPwdPasswordField)
            )
            .addGap(10)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(tipoUsrCheckBox)
            )
            .addGap(20)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(signInButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );

		pack();
    }

    private void signInButtonActionPerformed(ActionEvent evt, User user) {
        String nome = nomeTextField.getText().toLowerCase();
        nome = nome.substring(0,1).toUpperCase() + nome.substring(1);
        String usr = usrTextField.getText();

        // pega a senha do usuário como char[] e converte para String
		String pwd = new String(pwdPasswordField.getPassword());
        String confPwd = new String(confPwdPasswordField.getPassword());

        if (!pwd.equals(confPwd)) {
            JOptionPane.showMessageDialog(null, "Suas senhas não estão iguais!", "Senhas Divergentes", 0);
        } else if ((nome.isEmpty()) || (nome.length() > 16 )) {
            JOptionPane.showMessageDialog(null, "Campo 'Primeiro Nome' incorreto!", "Campo Incorreto", 0);
        } else if ((usr.isEmpty()) || (usr.length() > 20 )) {
            JOptionPane.showMessageDialog(null, "Campo 'Username' incorreto!", "Campo Incorreto", 0);
        } else if ((pwd.isEmpty()) || (pwd.length() < 8) || (pwd.length() > 16 )) {
            JOptionPane.showMessageDialog(null, "Campo 'Senha' incorreto!", "Campo Incorreto", 0);
        } else {
            String tipoUsr = (tipoUsrCheckBox.isSelected()) ? "A" : "R";
            try {
                UserDAO userDao = new UserDAO();
                User usuario = new User(nome, usr, pwd, tipoUsr);
                if (!userDao.verificarUsername(usuario.getUsername())) {
                    if (userDao.signin(usuario)) {
                        userDao.login(usuario);
                        //Ir para tela de usuario dependendo do tipo
                        if (usuario.getTipoUser().equals("R")) {
                            Usuario usuarioTela = new Usuario(usuario);
                            usuarioTela.setVisible(true);
                            this.dispose();
                        } else {
                            // AdmTela admTela = new AdmTela(user);
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
			java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SignIn.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                // new SignIn("R").setVisible(true);
                User user = new User("", "", "", "R");
				new SignIn(user).setVisible(true);
			}
		});
    }
}
