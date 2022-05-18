package view;

import lombok.Getter;
import utils.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    @Getter
    private JPanel rootPanel;
    private JButton loginButton;
    private JPanel mainPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private LoginFrame loginFrame;

    public LoginView(JFrame frame) {
        loginFrame = (LoginFrame) frame;
        initComponents();
        addActions();
    }

    private void initComponents() {
        JRootPane rootPane = SwingUtilities.getRootPane(loginFrame);
        rootPane.setDefaultButton(loginButton);
    }

    private void addActions() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (Validator.login(usernameField.getText(), passwordField.getPassword())) {
                    PrincipalView view = new PrincipalView();
                    loginFrame.dispose();
                }
            }
        });
    }
}
