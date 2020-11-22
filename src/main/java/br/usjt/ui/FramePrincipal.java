package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FramePrincipal extends JFrame {
 
    private static final long serialVersionUID = 1855684694660214127L;

    protected Container container;
    protected JPanel panel;
    protected JButton darkModeButton, exitButton, inicioButton;

    public FramePrincipal() {
        super("Recomendação de Músicas");
        definirComponentesPrincipais();
        this.setLocationRelativeTo(null);
    }

    private void definirComponentesPrincipais() {
        this.container = getContentPane();
        container.setPreferredSize(new Dimension(500, 400));
        container.setBackground(converteRGB("b12322"));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        container.setLayout(new GridBagLayout());
        
        this.panel = new JPanel();
        this.darkModeButton = criarJButton("DARK");
        darkModeButton.setBackground(converteRGB("3b3b3b"));
        
        this.exitButton = criarJButton("SAIR");

        this.inicioButton = criarJButton("INICIO");

        panel.setOpaque(false);

        container.add(panel);

        
        darkModeButton.addActionListener(evt -> darkButtonActionPerformed(evt));
        exitButton.addActionListener(evt -> exitButtonActionPerformed(evt));
        inicioButton.addActionListener(evt -> inicioButtonActionPerformed(evt));

        panel.add(darkModeButton);
        panel.add(exitButton);
        panel.add(inicioButton);

    }

    private void darkButtonActionPerformed(ActionEvent evt) {
        if (darkModeButton.getBackground().equals(converteRGB("3b3b3b"))) {
            container.setBackground(converteRGB("3b3b3b"));
            darkModeButton.setBackground(converteRGB("b12322"));
            darkModeButton.setText("ACME");
        } else {
            container.setBackground(converteRGB("b12322"));
            darkModeButton.setBackground(converteRGB("3b3b3b"));
            darkModeButton.setText("DARK");
        }
    }

    private void inicioButtonActionPerformed(ActionEvent evt) {
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
    }

    private void exitButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    public JButton criarJButton(String text) {
        JButton button = new JButton();
        button.setText(text);
        button.setPreferredSize(new Dimension(80, 30));
        button.setFont(new Font("sansserif", Font.BOLD, 12));
        return button;
    }

    public JTextField criarJTextField() {
        return new JTextField();
    }

    public JLabel criarJLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        
        if (font != null) {
            label.setFont(font);
        }

        if (color != null) {
            label.setForeground(color);
        }

		return label;
    }

    public static Color converteRGB(String hex) {
        String sr = hex.substring(0,2);
        String sg = hex.substring(2,4);
        String sb = hex.substring(4,6);

        int r = Integer.parseInt(sr, 16);
        int g = Integer.parseInt(sg, 16);
        int b = Integer.parseInt(sb, 16);

        Color color = new Color(r,g,b);
        
        return color;
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
			java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FramePrincipal().setVisible(true);
			}
		});
	}
}
