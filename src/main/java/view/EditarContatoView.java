package view;

import dao.ContatoDAO;
import lombok.Getter;
import model.Contato;
import utils.ComboItem;
import utils.Valida;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditarContatoView {
    private JTextField userField;
    private JTextField nomeField;
    private JTextField telefoneField;
    private JTextField cpfField;
    private JComboBox contatosBox;
    private JButton atualizaButton;
    private ContatoDAO dao;
    @Getter
    private JPanel panelMain;

    public EditarContatoView() {
        this.dao = new ContatoDAO();
        initComponents();
        createListeners();
    }

    public void initComponents() {
        for (Contato c : dao.getAll()) {
            contatosBox.addItem(new ComboItem(c.getUser(), c.getUser()));
        }
    }

    public void updateValores() {
        Contato c = dao.getContatoByUser(contatosBox.getSelectedItem().toString());
        userField.setText(c.getUser());
        nomeField.setText(c.getNome());
        cpfField.setText(c.getCpf());
        telefoneField.setText(c.getTelefone());
    }

    public void updateCombo() {
        contatosBox.addItem(new ComboItem(null, null));
        for (Contato c : ContatoDAO.getAll()) {
            contatosBox.addItem(new ComboItem(c.getUser(), c.getUser()));
        }
    }

    public void createListeners() {
        contatosBox.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
            }
        });

        contatosBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateValores();
            }
        });

        atualizaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Valida.validarCPF(cpfField.getText()))
                    if (dao.update(Integer.toString(contatosBox.getSelectedIndex()), new Contato(userField.getText(), nomeField.getText(), cpfField.getText(), telefoneField.getText()))) {
                        contatosBox.removeItem(contatosBox.getSelectedIndex());
                        JOptionPane.showMessageDialog(getPanelMain(), "Contato editado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    } else
                        JOptionPane.showMessageDialog(getPanelMain(), "Houve um erro ao editar o contato.", "Alerta", JOptionPane.ERROR_MESSAGE);
                else {
                    JOptionPane.showMessageDialog(getPanelMain(), "CPF inválido.", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
