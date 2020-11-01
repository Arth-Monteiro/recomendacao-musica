package br.usjt.classes;

import br.usjt.dao.ConnectionFactory;
import lombok.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter @AllArgsConstructor @ToString
public class User {
    private String username;
    @Setter private String password;

    // public User(String username, String password) {
    //     this.username = username;
    //     this.password = password;
    // }

    public static boolean login(Connection conn, String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND senha = ?"; // Query para verificar se existe usuario
        PreparedStatement stmt = conn.prepareStatement(query); // Prepara a Query

        // Define variaveis na query
        stmt.setString(1, username);
        stmt.setString(2, password);

        // Executa a Query
        try (ResultSet res = stmt.executeQuery()) {
            if (res.next()) {
                return true; // Caso encontre algum usuario
            } else {
                return false; // Caso nao encontre usuario
            }
        }
    }

    public static String signin(Connection conn, String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?"; // Query para verificar se existe nome de usuario
        PreparedStatement stmt = conn.prepareStatement(query); // Prepara a Query
        stmt.setString(1, username);
        try (ResultSet res = stmt.executeQuery()) {
            if (res.next()) {
                return "Nome de usuario já existente!";
            } else {
                String queryInsert = "INSERT INTO users (username, senha) VALUES (?,?)";
                PreparedStatement stmtInsert = conn.prepareStatement(queryInsert);
                stmtInsert.setString(1, username);
                stmtInsert.setString(2, password);
                stmtInsert.execute();
                return "Usuario cadastrado!";
            }
        }
    }

    public static void main(String[] args) {
        Connection conn = ConnectionFactory.obterConexao();
        try {
            System.out.println(signin(conn, "testedois", "ApenasUmTeste"));
            System.out.println(login(conn, "testedois", "ApenasUmTeste"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
    }
}

// Apenas como documentacao para continuar o desenvolvimento
// try {
//     Statement stmt = conn.createStatement();
//     String query = "SELECT * FROM login  WHERE username = 'TESTE'";
//     ResultSet resultSet = stmt.executeQuery(query);
//     ResultSetMetaData rsmd = resultSet.getMetaData();
//     int columnsNumber = rsmd.getColumnCount();
//     System.out.println(columnsNumber);
//     while (resultSet.next()) {
//         for (int i = 1; i <= columnsNumber; i++) {
//             if (i > 1) System.out.print(",  ");
//             String columnValue = resultSet.getString(i);
//             System.out.print(columnValue + " " + rsmd.getColumnName(i));
//         }   
//         System.out.println("");
//     } 
//     conn.close();
// } catch (SQLException e) {
//     System.out.println(e.getMessage());
// }    
