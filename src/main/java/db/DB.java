package db;

import java.sql.*;
import java.util.Properties;

/**
 * @author jean.franz
 */
public class DB {

    private static DB instance;

    private DB() {}

    public static DB getInstance() {
        if (instance == null) {
            new DB();
        }
        return instance;
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            Properties prop = new Properties();
            try {
                prop.load(DB.class.getClassLoader().getResourceAsStream("config.properties"));

                connection = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("dbUser"), null);

                Statement stm = connection.createStatement();

            } catch (Exception e) {
                System.err.println("Erro na conexão do banco de dados.");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Erro na leitura do arquivo config.properties");
            e.printStackTrace();
        }
        return connection;
    }
}
