package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DAO<T> {

    String tableName;

    public ResultSet getById(String tableName, String id) {

        String query = String.format("select * from %s where id = '%s';", tableName, id);

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);
            ps.execute();

            return ps.getResultSet();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
