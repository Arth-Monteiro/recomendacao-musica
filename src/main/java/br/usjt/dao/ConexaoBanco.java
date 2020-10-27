package br.usjt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
// import java.sql.ResultSet;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexaoBanco {
    private static Connection conexao;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PORT = "3306";
	private static final String DATABASE = "projeto_recomendacao";
	private static final String USR = "useradmin";
    private static final String PWD = "useradmin";  
	private static final String PARAMS = "?useTimezone=true&serverTimezone=UTC";
    private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DATABASE + PARAMS;

    private static final String SQL_HASH ="SELECT ? FROM ? WHERE ?";
    
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
			System.out.println("Erro carregando o driver: " + e.getMessage());
        }
    }
  
    public static void main(String[] args) {
        try {
            conectar();
        } catch (SQLException e) {
			System.out.println("Erro de conexao: " + e.getMessage());
		}
    }

    public static Connection conectar() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection(URL, USR, PWD);
        }
        return conexao;
    }

    // public static ResultSet executarQuery(String colunas, String tabela, String condicao) {

    // }


}

/*
public class ConectaBanco {
    private static final String SQL_ACESSO_DOC ="SELECT * FROM acesso WHERE nrdocumento ='999999999999'";

    private static final String SQL_HASH ="SELECT nome, idade FROM tabela WHERE campo = ?";


    public static void main(String[] args) throws SQLException {
        try (
            Connection con = conectar();
            PreparedStatement stmt = con.prepareStatement(SQL_ACESSO_DOC);
            PreparedStatement stmtHash = con.createStatement(SQL_HASH);
        ) {
            String hash = "";
            try (ResultSet res = stmt.executeQuery()) {
                if (res.next()) {
                    hash = res.getString(3);
                    System.out.println(hash);
                }
            }
            stmtHash.setString(1, hash);
            try (ResultSet res = stmtHash.executeQuery()) {
                while (res.next()) {
                    String nome = res.getString(1);
                    int idade = res.getInt(2);
                    System.out.println(nome + " tem " + idade + " anos.");
                }
            }
        }
    }
*/