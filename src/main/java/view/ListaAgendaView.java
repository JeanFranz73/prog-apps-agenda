package view;

import dao.ContatoDAO;
import lombok.Getter;
import model.Contato;
import utils.FlatSVGIcon;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaAgendaView {
    private ContatoDAO dao;
    private JTable tabelaContatos;
    private DefaultTableModel model;
    private MaskFormatter cpfMask, numMask;
    private JButton deletarButton;

    @Getter
    private JPanel panelMain;

    public ListaAgendaView() {

        this.dao = new ContatoDAO();
        this.model = (DefaultTableModel) tabelaContatos.getModel();

        initComponents();

        criarTabela();
        tabelaContatos.setModel(model);

        createListeners();
    }

    public void initComponents() {
        deletarButton.setIcon(new FlatSVGIcon("icons/user-x.svg", 14, 14));
    }

    public void criarTabela() {
        model.addColumn("Usuário");
        model.addColumn("Nome");
        model.addColumn("CPF");
        model.addColumn("Telefone");
        loadContatos();
    }

    public void loadContatos() {

        model.setRowCount(0);

        Object[] contato = new Object[5];

        try {
            cpfMask = new MaskFormatter("###.###.###-##");
            numMask = new MaskFormatter("(##) ####-####");
            cpfMask.setValueContainsLiteralCharacters(false);
            numMask.setValueContainsLiteralCharacters(false);
            for (Contato c : dao.getAll()) {
                contato[0] = c.getUser();
                contato[1] = c.getNome();
                contato[2] = cpfMask.valueToString(c.getCpf());
                contato[3] = numMask.valueToString(c.getTelefone());
                model.addRow(contato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createListeners() {
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tabelaContatos.getSelectionModel().isSelectionEmpty()) {
                    if (JOptionPane.showConfirmDialog(getPanelMain(), "Você tem certeza que deseja excluir este contato?", "Confirmar exclusão", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                        JOptionPane.showMessageDialog(getPanelMain(), "Contato excluído com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        dao.delete(tabelaContatos.getSelectedRow());
                    }
                    loadContatos();
                } else {
                    JOptionPane.showMessageDialog(getPanelMain(), "Selecione um contato.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
