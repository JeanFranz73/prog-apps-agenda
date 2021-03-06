package dao;

import db.MariaDBConnector;
import model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jean.franz
 */
public class PessoaMariaDbDAO extends PessoaDAO {

    private final String tableName = "pessoas";

    private static PessoaMariaDbDAO instance;

    private PessoaMariaDbDAO() {
    }

    public static PessoaMariaDbDAO getInstance() {
        if (instance == null) {
            instance = new PessoaMariaDbDAO();
        }
        return instance;
    }

    @Override
    public Pessoa get(Long id) {

        String query = String.format("select * from %s where id = '%s';", tableName, id);

        try {

            PreparedStatement ps = MariaDBConnector.connect().prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                String nome = rs.getString("nome");
                Long cpf = rs.getLong("cpf");
                Long telefone = rs.getLong("telefone");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");

                return new Pessoa(id, nome, cpf, telefone, email, endereco);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Pessoa pessoa) {
        String query = String.format("insert into %s (username, pessoa, cargo, password) values %s, %s, %s, %s;",
                tableName,
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getTelefone(),
                pessoa.getEmail(),
                pessoa.getEndereco());

        try {
            PreparedStatement ps = MariaDBConnector.connect().prepareStatement(query);
            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Long id, Pessoa pessoa) {
        return false;
    }

    @Override
    public void delete(Pessoa pessoa) {
        String query = String.format("delete from %s where id = '%s';", tableName, pessoa.getId());

        try {

            PreparedStatement ps = MariaDBConnector.connect().prepareStatement(query);
            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Pessoa> getAll() {
        List<Pessoa> list = new ArrayList<>();
        String query = String.format("select * from %s;", tableName);

        try {

            PreparedStatement ps = MariaDBConnector.connect().prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                Long cpf = rs.getLong("cpf");
                Long telefone = rs.getLong("telefone");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");

                Pessoa pessoa = new Pessoa(id, nome, cpf, telefone, email, endereco);
                list.add(pessoa);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
