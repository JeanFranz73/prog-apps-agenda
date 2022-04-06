package db;

import model.User;

import java.sql.*;

public class UserDAO extends DAO<User> {

    String tableName = "users";

    public User getById(String id) {
        ResultSet rs = getById(tableName, id);
        return new User();
    }
}
