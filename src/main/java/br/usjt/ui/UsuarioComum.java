package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import br.usjt.model.User;
import br.usjt.dao.UserDAO;

public class UsuarioComum extends Usuario {

    private static final long serialVersionUID = 2013351195869556815L;

    public UsuarioComum(User user) {
        super(user);
        initTelaUsuarioComum(user);
        this.setLocationRelativeTo(container);
    }

    private void initTelaUsuarioComum(User user) {

        String[] oQueFazer = {"Escolha uma opção...", 
                                "Configurar Gêneros Favoritos",
                                "Avaliar Música", 
                                "Receber Recomendação", 
                                "Alterar Senha", 
                                "Excluir conta"};

        oQueFazerComboBox.setModel(new DefaultComboBoxModel<>(oQueFazer));
       
        oQueFazerComboBox.addActionListener(evt -> verificarOQueFazerActionPerformed(evt, user));
    }

    private void verificarOQueFazerActionPerformed(ActionEvent evt, User user) {
        int opcao = oQueFazerComboBox.getSelectedIndex();
        switch (opcao) {
            case 0: limparPanel(evt); break;
            case 1: new GenerosFavoritos(user).setVisible(true); this.dispose(); break;
            case 2: new AvaliarMusica(user).setVisible(true); this.dispose(); break; // Avaliar, Alterar e ou Excluir 
            // case 3: RecomendarActionPerformed(evt); break;
            case 4: alterSenhaActionPerformed(evt, user.getUserID()); break;
            case 5: excludeAccountActionPerformed(evt, user.getUserID()); break;
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
			java.util.logging.Logger.getLogger(UsuarioComum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UsuarioComum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UsuarioComum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UsuarioComum.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                User user = new User("testuser", "12345678");
                UserDAO userDao = new UserDAO();
                try {
                    if (userDao.login(user)) {
                        new UsuarioComum(user).setVisible(true);
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
