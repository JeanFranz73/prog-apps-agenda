package view;

import lombok.Getter;
import model.Contato;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroView {
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JButton cadastraButton;
    @Getter private JPanel panelMain;

    public CadastroView() {
        initComponents();
    }

    private void initComponents() {
        cadastraButton.setBackground(UIManager.getDefaults().getColor("Button.default.background"));
        createListeners();
    }

    private void createListeners() {

        cadastraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Contato contato = new Contato(nomeField.getText(), cpfField.getText(), telefoneField.getText());

                if (contato.cadastrarContato())
                    JOptionPane.showMessageDialog(null, "Contato cadastrado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "CPF inválido.", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
