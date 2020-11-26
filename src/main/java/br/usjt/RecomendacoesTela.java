package br.usjt;

import java.awt.*;
import javax.swing.*;

public class RecomendacoesTela extends JFrame {

    private JLabel recomendacoesLabel;
    private JButton voltarButton;
    private JButton sairButton;
    private JTable tabela;

    private JPanel painel;

    public RecomendacoesTela() {
        super("Apollo");
        initComponentes();
        this.setLocationRelativeTo(null);
    }

    private void initComponentes() {
        recomendacoesLabel = new JLabel("<html> <i/> <b/> Estas são as nossas recomendações para você! <br/> </html>");
        voltarButton = new JButton("Voltar");
        sairButton = new JButton("Sair");
        Container container = this.getContentPane();
        painel = new JPanel();
        container.setLayout(new BorderLayout());
        container.setBackground(Color.WHITE);
        painel.setLayout(new BorderLayout());
        container.add(painel);

        // criar tabela
        // DefaultTableModel tabelaMusica = new DefaultTableModel();
        // tabelaMusica.addColumn("Música");
        // tabelaMusica.addColumn("Nota");
        // tabelaMusica.insertRow(0, new Object[] {"Duas Metades", "5.0"});
        // tabelaMusica.insertRow(1, new Object[] {"Amor pra Recomeçar", "5.0"});

        // Voce já sabe as colunas -> "Música" e "Nota"
        String[] colunas = {"Música", "Nota"};

        // Você busca as musicas no banco e retorna em forma de Musica[][]
        String[][] dados = {{"Duas Metades", "5.0"}, {"Amor para Recomeçar", "5.0"}};
        tabela = new JTable(dados, colunas);
        JScrollPane barraRolagem = new JScrollPane(tabela);
        painel.add(barraRolagem);

        pack();
    }
    
    public static void main(String[] args) {
        new RecomendacoesTela().setVisible(true);
    }
}