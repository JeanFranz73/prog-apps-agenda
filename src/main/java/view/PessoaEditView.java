package view;

import dao.DAOFactory;
import dao.PessoaDAO;
import lombok.Getter;
import model.Pessoa;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PessoaEditView {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JTextField emailField;
    private JTextField enderecoField;
    private JButton cadastraButton;
    private PessoaDAO dao;

    @Getter
    private JPanel rootPanel;
    private JButton atualizaButton;

    public PessoaEditView() {
        initComponents();
        atualizaButton.setVisible(false);
    }

    public PessoaEditView(Pessoa pessoa) {
        initComponents();
        nomeField.setText(pessoa.getNome());
        cpfField.setText(pessoa.getCpf().toString());
        telefoneField.setText(pessoa.getTelefone().toString());
        emailField.setText(pessoa.getEmail());
        enderecoField.setText(pessoa.getEndereco());
        cadastraButton.setVisible(false);
    }

    private void initComponents() {
        this.dao = DAOFactory.getPessoaDAO();
        createListeners();
    }

    private void createListeners() {
        cadastraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pessoa pessoa = new Pessoa(null, nomeField.getText(), Long.getLong(cpfField.getText()), Long.getLong(telefoneField.getText()), emailField.getText(), enderecoField.getText());

                if (dao.save(pessoa))
                    JOptionPane.showMessageDialog(getRootPanel(), "Contato cadastrado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(getRootPanel(), "CPF inválido.", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
