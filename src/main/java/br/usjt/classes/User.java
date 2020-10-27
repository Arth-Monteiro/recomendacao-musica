package br.usjt.classes;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.usjt.dao.ConnectionFactory;

public class User {
    
    public static void main(String[] args) {
        Connection conn = ConnectionFactory.obterConexao();
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM login";
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                String hash = res.getString(1);
                System.out.println(hash);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println(e);
        }        
    }
}
