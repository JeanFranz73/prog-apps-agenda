package dao;

import db.DB;
import model.Pessoa;
import model.User;
import utils.CargoEnum;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jean.franz
 */
public class UserDAO implements DAOI<User> {

    private final String tableName = "users";

    @Override
    public User get(int id) {

        String query = String.format("select * from %s where id = '%s';", tableName, id);

        PessoaDAO pessoaDAO = new PessoaDAO();

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                String username = rs.getString("username");
                Pessoa pessoa = pessoaDAO.get(rs.getInt("pessoa"));
                CargoEnum cargo = CargoEnum.getById(rs.getInt("cargo"));
                String password = rs.getString("password");

                return new User(id, username, pessoa, cargo, password);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void save(User user) {
        String query = String.format("insert into %s (username, pessoa, cargo, password) values %s, %s, %s, %s;",
                tableName,
                user.getUsername(),
                user.getPessoa().getId(),
                user.getCargo().getId(),
                user.getPassword());

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean update(String id, User user) {

        String query = String.format("update %s set (username = '%s', pessoa = '%s', cargo = '%s') where id = %s;",
                tableName,
                user.getUsername(),
                user.getPessoa().getId(),
                user.getCargo().getId(),
                id);

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public void delete(User user) {
        String query = String.format("delete from %s where id = '%s';", tableName, user.getId());

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();

        String query = String.format("select * from %s;", tableName);

        PessoaDAO pessoaDAO = new PessoaDAO();

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Integer idUser = rs.getInt("id");
                String username = rs.getString("username");
                Pessoa pessoa = pessoaDAO.get(rs.getInt("pessoa"));
                CargoEnum cargo = CargoEnum.getById(rs.getInt("cargo"));
                String password = rs.getString("password");

                User user = new User(idUser, username, pessoa, cargo, password);
                list.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
