package view;

import dao.DAOFactory;
import dao.PessoaDAO;
import dao.UserDAO;
import lombok.Getter;
import model.Pessoa;
import model.User;
import utils.CargoEnum;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEditView extends JFrame {

    private Long userId;
    private JTextField userField;
    private JButton cadastraButton;
    private UserDAO dao;

    private PessoaDAO daoPessoas;

    @Getter
    private JPanel rootPanel;
    private JButton atualizaButton;
    private JComboBox pessoaField;
    private JComboBox cargoField;
    private JPasswordField senhaField;

    public UserEditView() {
        initComponents();
        atualizaButton.setVisible(false);
    }

    public UserEditView(User user) {
        initComponents();

        userId = user.getId();

        userField.setText(user.getUsername());
        pessoaField.setSelectedItem(user.getPessoa().getClass());
        cargoField.setSelectedItem(user.getCargo());

        cadastraButton.setVisible(false);
    }

    private void initComponents() {
        this.dao = DAOFactory.getUserDAO();

        setSize(500, 300);

        createListeners();
    }

    private void createListeners() {
        cadastraButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User(null, userField.getText(), (Pessoa) pessoaField.getSelectedItem(), (CargoEnum) cargoField.getSelectedItem());

                if (dao.save(user)) {
                    JOptionPane.showMessageDialog(getRootPanel(), "Usuário cadastrado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(getRootPanel(), "CPF inválido.", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        });

        atualizaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                User user = new User(null, userField.getText(), (Pessoa) pessoaField.getSelectedItem(), (CargoEnum) cargoField.getSelectedItem());

                if (dao.update(userId, user)) {
                    JOptionPane.showMessageDialog(getRootPanel(), "Cadastro de usuário atualizado com sucesso.", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                }
                else
                    JOptionPane.showMessageDialog(getRootPanel(), "Informações inválidas.", "Alerta", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void createUIComponents() {
        this.daoPessoas = DAOFactory.getPessoaDAO();
        pessoaField = new JComboBox(daoPessoas.getAll().toArray());
        cargoField = new JComboBox(CargoEnum.values());
    }
}
