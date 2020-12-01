package br.usjt.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// Classe onde concentrei todos os botoes, containers e panel padrao
// Assim, em um lugar so defini o tamanho e a cor padrao
public class FramePrincipal extends JFrame {
 
    private static final long serialVersionUID = 1855684694660214127L;

    protected Container container;
    protected JPanel panel;
    protected JButton darkModeButton, exitButton, inicioButton;

    public FramePrincipal() {
        super("Recomendação de Músicas"); // Construtor do pai JFrame.
        definirComponentesPrincipais();
        this.setLocationRelativeTo(null);
    }

    private void definirComponentesPrincipais() {
        this.container = getContentPane();
        container.setPreferredSize(new Dimension(500, 400));
        container.setBackground(converteRGB("b12322"));
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        container.setLayout(new GridBagLayout()); // Define o layout do JFrame
        
        this.panel = new JPanel();
        this.darkModeButton = criarJButton("DARK");
        darkModeButton.setBackground(converteRGB("3b3b3b"));
        
        this.exitButton = criarJButton("SAIR");

        this.inicioButton = criarJButton("INICIO");

        panel.setOpaque(false); // Define o panel como Opaco para que assim so seja preciso definir a cor 
                                // do JFrame (container principal)

        container.add(panel); // Adicional o panel no container principal

        // Adiciona "ouvintes de ações" em cada botao
        darkModeButton.addActionListener(evt -> darkButtonActionPerformed(evt));
        exitButton.addActionListener(evt -> exitButtonActionPerformed(evt));
        inicioButton.addActionListener(evt -> inicioButtonActionPerformed(evt));

        // Adiciona botoes no panel
        panel.add(darkModeButton);
        panel.add(exitButton);
        panel.add(inicioButton);

    }

    // Funcao para mudar a cor vermelha para cinza e vice versa. Apenas um "enfeite do codigo"
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

    // Quando o botao "Inicio", pode estar renomeado como "LOG OUT", for pressionado,
    // retorna para a tela de inicio
    protected void inicioButtonActionPerformed(ActionEvent evt) {
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
        this.dispose();
    }

    // Quando o botao sair for pressionado, fecha a aplicacao
    private void exitButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    // Funcao para criar CheckBoxes com o texto do parametro e cor do texto da cor do param
    public JCheckBox criarJCheckBox(String text, Color cor) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setForeground(cor);
        return checkBox;
    }

    // Funcao para criar um Button com o texto designado pelo parametro
    // Tambem define o tamanho padrao do botao de 80x30
    // Alem de definir a fonte padrao como sansserif, negrito, tamanho 12
    public JButton criarJButton(String text) {
        JButton button = new JButton();
        button.setText(text);
        button.setPreferredSize(new Dimension(80, 30));
        button.setFont(new Font("sansserif", Font.BOLD, 12));
        return button;
    }

    // Funcao para criar uma caixa de texto
    public JTextField criarJTextField() {
        return new JTextField();
    }

    // Funcao para criar um Label com o texto designado pelo param
    // Caso a fonte ou a cor sejam diferentes de nulos, altera
    // Para o que estiver designado pelo param
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

    // Funcao para converter codigo hexadecimal em uma cor RGB
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

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FramePrincipal().setVisible(true);
			}
		});
	}
}