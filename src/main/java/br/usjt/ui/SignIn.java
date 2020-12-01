package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

// Herda o FramePrincipal
public class SignIn extends FramePrincipal {
    
    private static final long serialVersionUID = -6938217356257323852L;

    private JLabel nomeLabel, usrLabel, pwdLabel, confPwdJLabel;
    private JTextField nomeTextField, usrTextField;
    private JPasswordField pwdPasswordField, confPwdPasswordField;
	private JButton signInButton;
    private JCheckBox tipoUsrCheckBox;

    // Construtor da classe de cadastro. Uso o parametro user
    // para caso seja uma pessoa comum, ela sera, apos se cadastrar, redirecinada para a tela de userComum
    // e caso seja um adm, ela poderá definir se o user que ela esta cadastrando sera outro adm ou nao
    // além que ela sera redirecionada para a tela de userAdm dela mesma ao finalizar o cadastro.
    public SignIn(User user) {
        initTelaSignIn(user);
        this.setLocationRelativeTo(container);
    }

    private void initTelaSignIn(User user) {
        Font fonteLabels = null;
        Color branco = Color.WHITE;

        nomeLabel = criarJLabel("Digite seu primeiro nome: ", fonteLabels, branco);
        nomeTextField = criarJTextField(); // campo texto do nome do user

        usrLabel = criarJLabel("Digite ser Username: ", fonteLabels, branco);
        usrTextField = criarJTextField(); // campo texto do username do user

        pwdLabel = criarJLabel("Digite uma senha: ", fonteLabels, branco);
        pwdPasswordField = new JPasswordField(); // campo senha da senha do user

        confPwdJLabel = criarJLabel("Confirme sua senha: ", fonteLabels, branco);
        confPwdPasswordField = new JPasswordField(); // campo senha para confirmacao de senha do user

		signInButton = criarJButton("SIGN IN");
        tipoUsrCheckBox = new JCheckBox("Adm"); // CheckBox usado em caso de que o usuario sendo cadastrado
                                                // seja um adm

        tipoUsrCheckBox.setForeground(branco);

        // Adiciona um "ouvinte de acao" para finalizar o cadastro do user
        signInButton.addActionListener(evt -> signInButtonActionPerformed(evt, user));

        if (!(user.getTipoUser().equals("A"))) {
            tipoUsrCheckBox.setVisible(false);
        }

        // Adiciona componentes no panel
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

        // Define o layout do panel
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
                .addComponent(signInButton, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
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
                .addComponent(signInButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
            .addGap(10)
            .addGroup(
                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(darkModeButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(inicioButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addComponent(exitButton, GroupLayout.PREFERRED_SIZE,  GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
            )
        );

		pack(); // Agrupa tudo no paneil
    }

    // Acao para cadastrar usuario
    private void signInButtonActionPerformed(ActionEvent evt, User user) {
        String nome = nomeTextField.getText().toLowerCase();
        nome = nome.substring(0,1).toUpperCase() + nome.substring(1);
        String usr = usrTextField.getText();

        // pega a senha do usuário como char[] e converte para String
		String pwd = new String(pwdPasswordField.getPassword());
        String confPwd = new String(confPwdPasswordField.getPassword());

        // Testes dos campos para que o cadastro seja feito corretamente
        if (!pwd.equals(confPwd)) {
            JOptionPane.showMessageDialog(null, "Suas senhas não estão iguais!", "Senhas Divergentes", 0);
        } else if ((nome.isEmpty()) || (nome.length() > 50 )) {
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
                        // Se o usuario inicial for adm, vai para a tela de Adm, caso contrario,
                        // vai para a tela de userComum
                        if ((!user.getTipoUser().equals("A")) && (usuario.getTipoUser().equals("R"))) {
                            new UsuarioComum(usuario).setVisible(true);
                            this.dispose();
                        } else {
                            new UsuarioAdm(user).setVisible(true);
                            this.dispose();
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

		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User user = new User("", "", "", "R");
				new SignIn(user).setVisible(true);
			}
		});
    }
}