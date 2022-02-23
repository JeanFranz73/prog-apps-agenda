package view;

import dao.ContatoDAO;
import lombok.Getter;
import model.Contato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class ListaAgendaView {
    @Getter private JPanel panelMain;
    private JTable tabelaContatos;
    private DefaultTableModel model;

    public ListaAgendaView() {

        this.model = (DefaultTableModel) tabelaContatos.getModel();
        model.addColumn("Teste");
        model.addColumn("Teste2");
        model.addColumn("Teste3");

        tabelaContatos = new JTable(model);

    }
    public void criarTabela() {
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("Telefone");
        model.addColumn("Email");

        loadContatos();
    }

    public void loadContatos() {

        model.setRowCount(0);

        ContatoDAO dao = new ContatoDAO();
        List<Contato> contatos = dao.getAll();

        Object[] dadosTabela = new Object[4];

        for (int i = 0; i < contatos.size(); i++) {
            dadosTabela[0] = (i + 1);
            dadosTabela[1] = contatos.get(i).getNome();
            dadosTabela[2] = contatos.get(i).getTelefone();
            dadosTabela[3] = contatos.get(i).getCpf();
            model.addRow(dadosTabela);
        }
    }
}
