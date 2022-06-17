package view;

import dao.DAOFactory;
import dao.AgendamentoDAO;
import lombok.Getter;
import model.Agendamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AgendamentoEditView extends JFrame {

    private Long agendamentoId;
    private JTextField nomeField;
    private JTextField cpfField;
    private JTextField telefoneField;
    private JTextField emailField;
    private JTextField enderecoField;
    private JButton cadastraButton;
    private AgendamentoDAO dao;

    @Getter
    private JPanel rootPanel;
    private JButton atualizaButton;
    private JComboBox pacienteComboBox;

    public AgendamentoEditView() {
        initComponents();
        atualizaButton.setVisible(false);
    }

    public AgendamentoEditView(Agendamento agendamento) {
        initComponents();

        agendamentoId = agendamento.getId();

//        nomeField.setText(agendamento.getNome());
//        cpfField.setText(agendamento.getCpf().toString());
//        telefoneField.setText(agendamento.getTelefone().toString());
//        emailField.setText(agendamento.getEmail());
//        enderecoField.setText(agendamento.getEndereco());
//        cadastraButton.setVisible(false);
    }

    private void initComponents() {
        this.dao = DAOFactory.getAgendamentoDAO();

        createListeners();
    }

    private void createListeners() {
//        cadastraButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Agendamento agendamento = new Agendamento(null, nomeField.getText(), Long.getLong(cpfField.getText()), Long.getLong(telefoneField.getText()), emailField.getText(), enderecoField.getText());
//
//                if (dao.save(agendamento)) {
//                    JOptionPane.showMessageDialog(getRootPanel(), "Contato cadastrado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
//                    dispose();
//                }
//                else
//                    JOptionPane.showMessageDialog(getRootPanel(), "CPF inválido.", "Alerta", JOptionPane.ERROR_MESSAGE);
//            }
//        });
//
//        atualizaButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                Agendamento agendamento = new Agendamento(agendamentoId, nomeField.getText(), Long.parseLong(cpfField.getText()), Long.parseLong(telefoneField.getText()), emailField.getText(), enderecoField.getText());
//
//                if (dao.update(agendamentoId, agendamento)) {
//                    JOptionPane.showMessageDialog(getRootPanel(), "Cadastro de agendamento atualizado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
//                    dispose();
//                }
//                else
//                    JOptionPane.showMessageDialog(getRootPanel(), "Informações inválidas.", "Alerta", JOptionPane.ERROR_MESSAGE);
//            }
//        });
    }

    private void createUIComponents() {
        pacienteComboBox = new JComboBox(dao.getAll().toArray());
    }
}
