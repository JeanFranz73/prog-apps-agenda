package view;

import dao.AgendamentoDAO;
import dao.DAOFactory;
import dao.PessoaDAO;
import lombok.Getter;
import model.Agendamento;
import model.Pessoa;
import utils.SVGUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaAgendamentosView {
    private AgendamentoDAO dao;
    private JTable tabelaAgendamentos;
    private DefaultTableModel model;
    private JButton deletarButton;
    private JFrame parentFrame;

    @Getter
    private JPanel rootPanel;
    private JButton editarButton;

    public ListaAgendamentosView(JFrame frame) {

        this.parentFrame = frame;
        this.dao = DAOFactory.getAgendamentoDAO();
        this.model = (DefaultTableModel) tabelaAgendamentos.getModel();

        initComponents();

        criarTabela();
        tabelaAgendamentos.setModel(model);

        createListeners();
    }

    private void initComponents() {
        deletarButton.setIcon(new SVGUtils("icons/user-x.svg", 14, 14));
        editarButton.setIcon(new SVGUtils("icons/edit.svg", 14, 14));
    }

    private void criarTabela() {
        model.addColumn("ID");
        model.addColumn("Agendado por");
        model.addColumn("Paciente");
        model.addColumn("Médico");
        model.addColumn("Descrição");
        model.addColumn("Horário");
        loadAgendamentos();
        ajustarTabela();
    }

    public void loadAgendamentos() {

        model.setRowCount(0);
        model.isCellEditable(0, 0);

        Object[] contato = new Object[6];
        for (Agendamento a : dao.getAll()) {

            contato[0] = a.getId();
            contato[1] = a.getCadastrante();
            contato[2] = a.getPaciente();
            contato[3] = a.getMedico();
            contato[4] = a.getDescricao();
            contato[5] = a.getHorarioAgendado();

            model.addRow(contato);
        }
    }

    private void ajustarTabela() {
        tabelaAgendamentos.setDefaultEditor(Object.class, null);
        tabelaAgendamentos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaAgendamentos.getColumnModel().getColumn(0).setResizable(false);
        tabelaAgendamentos.getColumnModel().getColumn(0).setMaxWidth(30);
        tabelaAgendamentos.getColumnModel().getColumn(0).setPreferredWidth(30);
    }

    private void createListeners() {
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tabelaAgendamentos.getSelectionModel().isSelectionEmpty()) {
                    if (JOptionPane.showConfirmDialog(getRootPanel(), "Você tem certeza que deseja excluir este agendamento?", "Confirmar exclusão", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                        dao.delete(dao.get((Long) tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 0)));
                        JOptionPane.showMessageDialog(getRootPanel(), "Agendamento cancelado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(getRootPanel(), "Selecione um agendamento.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
                loadAgendamentos();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Agendamento agendamento = dao.get(Long.parseLong(tabelaAgendamentos.getValueAt(tabelaAgendamentos.getSelectedRow(), 0).toString()));

                PessoaEditView pessoaEditView = new PessoaEditView(null);

                JDialog dialog = new JDialog(parentFrame, "Editar agendamento", true);

                dialog.add(pessoaEditView.getRootPanel());
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
}
