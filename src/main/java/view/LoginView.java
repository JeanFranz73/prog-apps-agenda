package view;

import lombok.Getter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

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

    private void createUIComponents() {
        UIManager.put( "Component.arc", 999 );
        rootPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    g.drawImage(ImageIO.read(new File(getClass().getClassLoader().getResource("img/login-bg.jpg").getFile()))
                            .getScaledInstance(1280, 720, Image.SCALE_AREA_AVERAGING), 0, 0, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
