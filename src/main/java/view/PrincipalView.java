package view;

import lombok.Getter;
import utils.SVGUtils;

import javax.swing.*;
import java.awt.*;

public class PrincipalView extends JFrame {
    private ListaPessoasView listaCadastros;
    private PessoaView cadastro;
    private EditarContatoView edit;
    private JTabbedPane pane;
    private JCheckBoxMenuItem modoEscuro;
    private JToolBar toolBar;

    @Getter
    private JPanel rootPanel;

    public PrincipalView() {
        initComponents();
        initUI();
    }

    private void initComponents() {
        addMenu();

//        this.cadastro = new PessoaView();
//        pane.addTab("Agendamentos", new SVGUtils("icons/list.svg", 16, 16), cadastro.getRootPanel());

        this.listaCadastros = new ListaPessoasView(this);
        pane.addTab("Cadastros", new SVGUtils("icons/users.svg", 16, 16), listaCadastros.getRootPanel());

        pane.putClientProperty("JTabbedPane.tabWidthMode", "compact");
    }

    private void initUI() {
        setIconImage(new SVGUtils("icons/aperture.svg").getImage());

        setTitle("Consultório Clínico");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(this.pane);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu opcoesMenu = new JMenu("Opções");
        modoEscuro = new JCheckBoxMenuItem("Checkbox", false);
        opcoesMenu.add(modoEscuro);
        menu.add(opcoesMenu);
        setJMenuBar(menu);
    }
}
