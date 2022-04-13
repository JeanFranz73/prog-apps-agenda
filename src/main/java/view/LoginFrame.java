package view;

import utils.SVGUtils;

import javax.swing.*;

public class LoginFrame extends JFrame {

    private LoginView view;

    public LoginFrame() throws Exception {
        view = new LoginView(this);
        add(view.getRootPanel());
        initComponents();
        addMenu();
    }

    private void initComponents() throws Exception {
        UIManager.put("TitlePane.menuBarEmbedded", true);
        setIconImage(new SVGUtils("icons/aperture.svg").getImage());
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // setUndecorated(true);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addMenu() {
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
    }
}
