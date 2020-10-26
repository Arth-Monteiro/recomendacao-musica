package br.usjt.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection conexao;
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String PORT = "3306";
	private static final String DATABASE = "projeto_recomendacao";
	private static final String PARAMS = "?useTimezone=true&serverTimezone=UTC";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DATABASE + PARAMS;
	private static final String USR = "useradmin";
    private static final String PWD = "useradmin";

    public static void main(String[] args) {
        obterConexao();
    }
    
    public static Connection obterConexao() {
		try {
			Class.forName(DRIVER);
			if (conexao == null) {
				conexao = DriverManager.getConnection(URL, USR, PWD);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Erro carregando o driver: " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Erro de conexao: " + e.getMessage());
		}
		return conexao;
	}
}
