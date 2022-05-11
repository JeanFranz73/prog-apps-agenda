package db;

import java.sql.*;
import java.util.Properties;

/**
 * @author jean.franz
 */
public class MariaDBConnector {

    private static MariaDBConnector instance;

    private MariaDBConnector() {}

    public static MariaDBConnector getInstance() {
        if (instance == null) {
            new MariaDBConnector();
        }
        return instance;
    }

    public static Connection connect() {
        Connection connection = null;
        try {
            Properties prop = new Properties();
            try {
                prop.load(MariaDBConnector.class.getClassLoader().getResourceAsStream("config.properties"));

                connection = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("dbUser"), prop.getProperty("dbPass"));

                Statement stm = connection.createStatement();

            } catch (Exception e) {
                System.err.println("Erro na conexão do banco de dados.\n");
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.err.println("Erro na leitura do arquivo config.properties\n");
            e.printStackTrace();
        }
        return connection;
    }
}
