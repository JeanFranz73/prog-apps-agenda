package view;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private LoginView view;

    public LoginFrame() throws Exception {
        view = new LoginView(this);
        add(view.getRootPanel());

        initComponents();
    }

    private void initComponents() throws Exception {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
