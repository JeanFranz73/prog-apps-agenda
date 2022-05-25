package dao;

import db.DBConnector;
import model.Agendamento;
import model.Pessoa;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jean.franz
 */
public class AgendamentoDAOImpl extends AgendamentoDAO {

    private final String tableName = "agendamentos";
    private static AgendamentoDAOImpl instance;

    private PessoaDAO pessoaDAO;
    private UserDAO userDAO;

    private AgendamentoDAOImpl() {
        this.userDAO = DAOFactory.getUserDAO();
        this.pessoaDAO = DAOFactory.getPessoaDAO();
    }

    public static AgendamentoDAOImpl getInstance() {
        if (instance == null) {
            instance = new AgendamentoDAOImpl();
        }
        return instance;
    }

    @Override
    public Agendamento get(Long id) {

        String query = String.format("select * from %s where id = '%s';", tableName, id);

        pessoaDAO = DAOFactory.getPessoaDAO();
        userDAO = DAOFactory.getUserDAO();

        try {

            PreparedStatement ps = DBConnector.connect().prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                User cadastrante = userDAO.get(rs.getLong("cadastrante"));
                Pessoa paciente = pessoaDAO.get(rs.getLong("paciente"));
                User medico = userDAO.get(rs.getLong("medico"));
                String descricao = rs.getString("desc");
                String horarioAgendado = rs.getString("data_marcada");
                String dataRegistro = rs.getString("data_registro");

                return new Agendamento(id, cadastrante, paciente, medico, descricao, horarioAgendado, dataRegistro);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean save(Agendamento agendamento) {
        String query = String.format("insert into %s (cadastrante, paciente, medico, desc, data_marcada) values %s, %s, %s, %s, %s;",
                tableName,
                agendamento.getCadastrante().getId(),
                agendamento.getPaciente().getId(),
                agendamento.getMedico().getId(),
                agendamento.getDescricao(),
                agendamento.getHorarioAgendado());

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
    public boolean update(Long id, Agendamento agendamento) {
        String query = String.format("update %s set cadastrante = '%s', paciente = '%s', medico = '%s', desc = '%s', data_marcada = '%s' where id = %d;",
                tableName,
                agendamento.getCadastrante().getId(),
                agendamento.getPaciente().getId(),
                agendamento.getMedico().getId(),
                agendamento.getDescricao(),
                agendamento.getHorarioAgendado(),
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
    public void delete(Agendamento agendamento) {
        String query = String.format("delete from %s where id = '%s';", tableName, agendamento.getId());

        try {

            PreparedStatement ps = DBConnector.connect().prepareStatement(query);

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Agendamento> getAll() {
        List<Agendamento> list = new ArrayList<>();
        String query = String.format("select * from %s;", tableName);

        try {

            PreparedStatement ps = DBConnector.connect().prepareStatement(query);
            ps.execute();

            ResultSet rs = ps.getResultSet();

            while (rs.next()) {
                Long id = rs.getLong("id");
                User cadastrante = userDAO.get(rs.getLong("cadastrante"));
                Pessoa paciente = pessoaDAO.get(rs.getLong("paciente"));
                User medico = userDAO.get(rs.getLong("medico"));
                String descricao = rs.getString("desc");
                String horarioAgendado = rs.getString("data_marcada");
                String dataRegistro = rs.getString("data_registro");

                Agendamento agendamento = new Agendamento(id, cadastrante, paciente, medico, descricao, horarioAgendado, dataRegistro);
                list.add(agendamento);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
