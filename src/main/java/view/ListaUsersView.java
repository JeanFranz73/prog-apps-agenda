package view;

import dao.*;
import lombok.Getter;
import model.User;
import utils.SVGUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListaUsersView {
    private UserDAO dao;
    private JTable tabelaUsers;
    private DefaultTableModel model;
    private MaskFormatter cpfMask, numMask;
    private JButton deletarButton;
    private JFrame parentFrame;

    @Getter
    private JPanel rootPanel;
    private JButton editarButton;

    public ListaUsersView(JFrame frame) {

        this.parentFrame = frame;
        this.dao = DAOFactory.getUserDAO();
        this.model = (DefaultTableModel) tabelaUsers.getModel();

        initComponents();

        criarTabela();
        tabelaUsers.setModel(model);

        createListeners();
    }

    private void initComponents() {
        deletarButton.setIcon(new SVGUtils("icons/user-x.svg", 14, 14));
        editarButton.setIcon(new SVGUtils("icons/edit.svg", 14, 14));
    }

    private void criarTabela() {
        model.addColumn("ID");
        model.addColumn("username");
        model.addColumn("Nome");
        model.addColumn("Cargo");
        loadContatos();
        ajustarTabela();
    }

    public void loadContatos() {

        model.setRowCount(0);
        model.isCellEditable(0, 0);

        Object[] contato = new Object[6];
        for (User u : dao.getAll()) {

            contato[0] = u.getId();
            contato[1] = u.getUsername();
            contato[2] = u.getPessoa().getNome();
            contato[3] = u.getCargo().getName();

            model.addRow(contato);
        }
    }

    private void ajustarTabela() {
        tabelaUsers.setDefaultEditor(Object.class, null);
        tabelaUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaUsers.getColumnModel().getColumn(0).setResizable(false);
        tabelaUsers.getColumnModel().getColumn(0).setMaxWidth(30);
        tabelaUsers.getColumnModel().getColumn(0).setPreferredWidth(30);
    }

    private void createListeners() {
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tabelaUsers.getSelectionModel().isSelectionEmpty()) {
                    if (JOptionPane.showConfirmDialog(getRootPanel(), "Você tem certeza que deseja excluir este cadastro?", "Confirmar exclusão", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                        dao.delete(dao.get((Long) tabelaUsers.getValueAt(tabelaUsers.getSelectedRow(), 0)));
                        JOptionPane.showMessageDialog(getRootPanel(), "Cadastro excluído com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    loadContatos();
                } else {
                    JOptionPane.showMessageDialog(getRootPanel(), "Selecione um contato.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = dao.get(Long.parseLong(tabelaUsers.getValueAt(tabelaUsers.getSelectedRow(), 0).toString()));

                UserEditView pessoaView = new UserEditView(user);

                JDialog dialog = new JDialog(parentFrame, "Atualizar Cadastro de Usuário", true);

                dialog.add(pessoaView.getRootPanel());
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
    }
}
