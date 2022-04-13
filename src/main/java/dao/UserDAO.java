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
    public User get(Long id) {

        String query = String.format("select * from %s where id = '%s';", tableName, id);

        PessoaDAO pessoaDAO = new PessoaDAO();

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            if (rs.next()) {
                String username = rs.getString("username");
                Pessoa pessoa = pessoaDAO.get(rs.getLong("pessoa"));
                CargoEnum cargo = CargoEnum.getById(rs.getInt("cargo"));

                return new User(id, username, pessoa, cargo);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(User user) {
        String query = String.format("insert into %s (username, pessoa, cargo) values %s, %s, %s;",
                tableName,
                user.getUsername(),
                user.getPessoa().getId(),
                user.getCargo().getId());

        try {
            PreparedStatement ps = DB.connect().prepareStatement(query);

            ps.executeUpdate();

            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Long id, User user) {

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
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                Pessoa pessoa = pessoaDAO.get(rs.getLong("pessoa"));
                CargoEnum cargo = CargoEnum.getById(rs.getInt("cargo"));

                User user = new User(id, username, pessoa, cargo);
                list.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }
}
