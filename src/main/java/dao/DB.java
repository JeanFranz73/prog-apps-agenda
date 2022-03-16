package dao;

import java.sql.*;
import java.util.Properties;

public class DB {
    public Connection connect() {
        Connection connection = null;
        try {
            Properties prop = new Properties();
            try {
                prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

                connection = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("dbUser"), prop.getProperty("dbPass"));
                System.out.println("Sucesso na conexão com o banco de dados!");

                Statement stm = connection.createStatement();

            } catch (Exception e) {
                System.out.println("Erro na conexão do banco de dados");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Erro na leitura do arquivo config.properties");
            e.printStackTrace();
        }
        return connection;
    }
}
