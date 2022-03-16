package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LoginFrame extends JFrame {

    private LoginView view;

    public LoginFrame() throws Exception {


        view = new LoginView(this);
        add(view.getRootPanel());

        initComponents();
    }

    private void initComponents() throws Exception {
        paintComponents(ImageIO.read(new File(getClass().getClassLoader().getResource("img/login-bg.jpg").getPath())).getGraphics());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
