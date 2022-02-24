package view;

import dao.ContatoDAO;
import lombok.Getter;
import model.Contato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ListaAgendaView {
    private ContatoDAO dao;
    private JTable tabelaContatos;
    private DefaultTableModel model;

    @Getter
    private JPanel panelMain;

    public ListaAgendaView() {
        this.dao = new ContatoDAO();
        this.model = (DefaultTableModel) tabelaContatos.getModel();

        criarTabela();

        tabelaContatos.setModel(model);

    }

    public void criarTabela() {
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("CPF");
        model.addColumn("Telefone");
        loadContatos();
    }

    public void loadContatos() {

        model.setRowCount(0);

        Object[] contato = new Object[4];

        int i = 0;
        for (Contato c : dao.getAll()) {
            contato[0] = (i + 1);
            contato[1] = c.getNome();
            contato[2] = c.getCpf();
            contato[3] = c.getTelefone();
            model.addRow(contato);
            i++;
        }
    }
}
