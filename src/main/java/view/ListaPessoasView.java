package view;

import dao.DAOFactory;
import dao.PessoaDAO;
import lombok.Getter;
import model.Pessoa;
import utils.SVGUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class ListaPessoasView {
    private PessoaDAO dao;
    private JTable tabelaPessoas;
    private DefaultTableModel model;
    private MaskFormatter cpfMask, numMask;

    private TableRowSorter<TableModel> rowSorter;
    private JButton deletarButton;
    private JFrame parentFrame;

    @Getter
    private JPanel rootPanel;
    private JButton editarButton;
    private JButton addButton;
    private JButton updateListaButton;
    private JTextField pesquisaField;

    public ListaPessoasView(JFrame frame) {

        this.parentFrame = frame;
        this.dao = DAOFactory.getPessoaDAO();
        this.model = (DefaultTableModel) tabelaPessoas.getModel();

        initComponents();

        criarTabela();
        tabelaPessoas.setModel(model);

        createListeners();

    }

    private void initComponents() {
        updateListaButton.setIcon(new SVGUtils("icons/refresh.svg", 14, 14));
        addButton.setIcon(new SVGUtils("icons/user-plus.svg", 14, 14));
        deletarButton.setIcon(new SVGUtils("icons/user-x.svg", 14, 14));
        editarButton.setIcon(new SVGUtils("icons/edit.svg", 14, 14));
    }

    private void criarTabela() {
        model.addColumn("ID");
        model.addColumn("Nome");
        model.addColumn("CPF");
        model.addColumn("Telefone");
        model.addColumn("E-mail");
        model.addColumn("Endereço");
        loadPessoas();
        ajustarTabela();
    }

    public void loadPessoas() {

        model.setRowCount(0);
        model.isCellEditable(0, 0);

        Object[] contato = new Object[6];
        try {
            cpfMask = new MaskFormatter("###.###.###-##");
            numMask = new MaskFormatter("(##) #####-####");
            cpfMask.setValueContainsLiteralCharacters(false);
            numMask.setValueContainsLiteralCharacters(false);
            for (Pessoa p : dao.getAll()) {

                contato[0] = p.getId();
                contato[1] = p.getNome();
                contato[2] = cpfMask.valueToString(p.getCpf());
                contato[3] = numMask.valueToString(p.getTelefone());
                contato[4] = p.getEmail();
                contato[5] = p.getEndereco();

                model.addRow(contato);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.rowSorter = new TableRowSorter<>(tabelaPessoas.getModel());
        this.tabelaPessoas.setRowSorter(rowSorter);
    }

    private void ajustarTabela() {
        tabelaPessoas.setDefaultEditor(Object.class, null);
        tabelaPessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaPessoas.getColumnModel().getColumn(0).setResizable(false);
        tabelaPessoas.getColumnModel().getColumn(0).setMaxWidth(30);
        tabelaPessoas.getColumnModel().getColumn(0).setPreferredWidth(30);
        tabelaPessoas.getColumnModel().getColumn(2).setPreferredWidth(90);
        tabelaPessoas.getColumnModel().getColumn(3).setPreferredWidth(90);
    }

    private void createListeners() {

        updateListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPessoas();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PessoaEditView pessoaEditView = new PessoaEditView();

                JDialog dialog = new JDialog(parentFrame, "Cadastrar Pessoa", true);

                dialog.add(pessoaEditView.getRootPanel());
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });
        deletarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tabelaPessoas.getSelectionModel().isSelectionEmpty()) {
                    if (JOptionPane.showConfirmDialog(getRootPanel(), "Você tem certeza que deseja excluir este cadastro?", "Confirmar exclusão", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                        dao.delete(dao.get((Long) tabelaPessoas.getValueAt(tabelaPessoas.getSelectedRow(), 0)));
                        JOptionPane.showMessageDialog(getRootPanel(), "Cadastro excluído com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    loadPessoas();
                } else {
                    JOptionPane.showMessageDialog(getRootPanel(), "Selecione um contato.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pessoa pessoa = dao.get(Long.parseLong(tabelaPessoas.getValueAt(tabelaPessoas.getSelectedRow(), 0).toString()));

                PessoaEditView pessoaEditView = new PessoaEditView(pessoa);

                JDialog dialog = new JDialog(parentFrame, "Atualizar Cadastro de Pessoa", true);

                dialog.add(pessoaEditView.getRootPanel());
                dialog.pack();
                dialog.setLocationRelativeTo(null);
                dialog.setVisible(true);
            }
        });

        pesquisaField.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = pesquisaField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = pesquisaField.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
    }
}
