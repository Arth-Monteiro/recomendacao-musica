package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

// Herda a tela de Usuario
public class UsuarioAdm extends Usuario {

    private static final long serialVersionUID = 484857305672929201L;

    public UsuarioAdm(User adm) {
        super(adm); // Constroi a classe pai com as informacoes do usuario
        initTelaUsuarioAdm(adm);
        this.setLocationRelativeTo(container);
    }

    private void initTelaUsuarioAdm(User adm) {
        // Opcoes do que fazer
        String[] oQueFazer = {"Escolha uma opção...", 
                                "Cadastrar Usuário",            // Opcao 1
                                "Configurar Gêneros Musicais",  // Opcao 2
                                "Configurar Músicas",           // Opcao 3
                                "Alterar Senha",                // Opcao 4
                                "Excluir conta"};               // Opcao 5

        oQueFazerComboBox.setModel(new DefaultComboBoxModel<>(oQueFazer));
        
        // Cria "ouvinte de acoes" do que fazer para cada opcao
        oQueFazerComboBox.addActionListener(evt -> verificarOQueFazerActionPerformed(evt, adm));
    }

    private void verificarOQueFazerActionPerformed(ActionEvent evt, User adm) {
        // pega o index da opcao e leva para cada tela especifica
        int opcao = oQueFazerComboBox.getSelectedIndex();
        switch (opcao) {
            case 0: limparPanel(evt); break;
            case 1: new SignIn(adm).setVisible(true); this.dispose(); break; // Leva para a tela de Cadastro de Usuarios
            case 2: new ConfigurarGenero(adm).setVisible(true); this.dispose();break; // Leva para a tela de Configurar os Generos
            case 3: new ConfigurarMusica(adm).setVisible(true); this.dispose(); break; // Leva para a tela de Configurar as Músicas
            case 4: alterSenhaActionPerformed(evt, adm.getUserID()); break;
            case 5: excludeAccountActionPerformed(evt, adm.getUserID()); break;
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
			java.util.logging.Logger.getLogger(UsuarioAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UsuarioAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UsuarioAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UsuarioAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User adm = new User("testadm", "12345678");
                UserDAO userDao = new UserDAO();
                try {
                    if (userDao.login(adm)) {
                        new UsuarioAdm(adm).setVisible(true);
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
