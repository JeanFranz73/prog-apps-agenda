package view;

import lombok.Getter;
import model.Contato;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContatoView {
    private JTextField userField;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JButton cadastraButton;
    @Getter private JPanel panelMain;

    public ContatoView() {

        initComponents();
    }

    private void initComponents() {
        createListeners();
    }

    private void createListeners() {
        cadastraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Contato contato = new Contato(userField.getText(), nomeField.getText(), cpfField.getText(), telefoneField.getText());

                if (contato.cadastrarContato())
                    JOptionPane.showMessageDialog(getPanelMain(), "Contato cadastrado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(getPanelMain(), "CPF inválido.", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
