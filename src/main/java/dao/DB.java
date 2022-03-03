package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {

    public static void main(String[] args) {
        new DB().connect();
    }

    public Connection connect() {
        Connection connection = null;
        try {
            Properties prop = new Properties();
            try {
                prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

                Class.forName(prop.getProperty("dbDriver"));
                connection = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("dbUser"), prop.getProperty("dbPass"));

                System.out.print("Conectado!");

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
