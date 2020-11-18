package br.usjt.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	private static final String PORT = "3306";
	private static final String DATABASE = "projeto_recomendacao";
	private static final String PARAMS = "?useTimezone=true&serverTimezone=UTC";
	private static final String URL = "jdbc:mysql://localhost:" + PORT + "/" + DATABASE + PARAMS;
	private static final String USR = "useradmin";
    private static final String PWD = "useradmin";

    public static Connection obterConexao() throws Exception {
		return DriverManager.getConnection(URL, USR, PWD);
	}
}
