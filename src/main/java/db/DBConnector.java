package db;

import java.sql.*;

/**
 * @author jean.franz
 */
public class DBConnector {

    private static DBConnector instance;

    private static Connection connection = null;
    private static Statement stm;

    private DBConnector() {}

    public static DBConnector getInstance() {
        if (instance == null) {
            new DBConnector();
        }
        return instance;
    }

    public static Connection connect() {

        if (connection == null) {
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:banco.db", "root", "password");

                    stm = connection.createStatement();

                } catch (SQLException e) {
                    System.err.println("Erro ao conectar com o banco de dados.\n");
                    e.printStackTrace();
                }
            }
        return connection;
    }
}
