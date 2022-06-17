package dao;

import db.DBConnector;
import model.Pessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jean.franz
 */
public class PessoaDAOImpl extends PessoaDAO {

    private final String tableName = "pessoas";
    private static PessoaDAOImpl instance;

    private PessoaDAOImpl() {
    }

    public static PessoaDAOImpl getInstance() {
        if (instance == null) {
            instance = new PessoaDAOImpl();
        }
        return instance;
    }

    @Override
    public Pessoa get(Long id) {

        String query = String.format("select * from %s where id = '%s';", tableName, id);

        try {

            PreparedStatement ps = DBConnector.connect().prepareStatement(query);
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
        String query = String.format("insert into %s (nome, cpf, telefone, email, endereco) values ('%s', '%s', '%s', '%s', '%s');",
                tableName,
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getTelefone(),
                pessoa.getEmail(),
                pessoa.getEndereco());

        System.out.println(query);
        try {
            PreparedStatement ps = DBConnector.connect().prepareStatement(query);

            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Long id, Pessoa pessoa) {
        String query = String.format("update %s set nome = '%s', cpf = '%s', telefone = '%s', email = '%s', endereco = '%s' where id = %d;",
                tableName,
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getTelefone(),
                pessoa.getEmail(),
                pessoa.getEndereco(),
                id);

        try {
            PreparedStatement ps = DBConnector.connect().prepareStatement(query);

            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(Pessoa pessoa) {
        String query = String.format("delete from %s where id = '%s';", tableName, pessoa.getId());

        try {

            PreparedStatement ps = DBConnector.connect().prepareStatement(query);

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

            PreparedStatement ps = DBConnector.connect().prepareStatement(query);
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
