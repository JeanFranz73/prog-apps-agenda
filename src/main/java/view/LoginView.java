package view;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    @Getter
    private JPanel rootPanel;
    private JButton button1;
    private JPanel mainPanel;

    private LoginFrame loginFrame;

    public LoginView(JFrame frame) {

        button1.putClientProperty( "Button.arc", 999 );
        loginFrame = (LoginFrame) frame;
        initComponents();
        addActions();
    }

    private void initComponents() {
    }

    private void addActions() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
            }
        });
    }
}
