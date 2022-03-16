package view;

import lombok.Getter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView {
    @Getter
    private JPanel rootPanel;
    private JPanel mainPanel;
    private JButton button1;

    private LoginFrame loginFrame;

    public LoginView(JFrame frame) {
        loginFrame = (LoginFrame) frame;
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
            }
        });
    }
}
