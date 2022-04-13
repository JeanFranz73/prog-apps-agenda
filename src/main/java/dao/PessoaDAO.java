package dao;

import db.DB;
import model.Pessoa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jean.franz
 */
public class PessoaDAO implements DAOI<Pessoa> {

    private final String tableName = "pessoas";

    @Override
    public Pessoa get(int id) {

        String query = String.format("select * from %s where id = '%s';", tableName, id);

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);
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
    public void save(Pessoa pessoa) {
        String query = String.format("insert into %s (username, pessoa, cargo, password) values %s, %s, %s, %s;",
                tableName,
                pessoa.getNome(),
                pessoa.getCpf(),
                pessoa.getTelefone(),
                pessoa.getEmail(),
                pessoa.getEndereco());

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean update(String id, Pessoa pessoa) {
        return false;
    }

    @Override
    public void delete(Pessoa pessoa) {
        String query = String.format("delete from %s where id = '%s';", tableName, pessoa.getId());

        try {

            PreparedStatement ps = DB.connect().prepareStatement(query);
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

            PreparedStatement ps = DB.connect().prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Integer idPessoa = rs.getInt("id");
                String nome = rs.getString("nome");
                Long cpf = rs.getLong("cpf");
                Long telefone = rs.getLong("telefone");
                String email = rs.getString("email");
                String endereco = rs.getString("endereco");

                Pessoa pessoa = new Pessoa(idPessoa, nome, cpf, telefone, email, endereco);
                list.add(pessoa);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
