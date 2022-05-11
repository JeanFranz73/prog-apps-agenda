package view;

import lombok.Getter;
import utils.SVGUtils;

import javax.swing.*;

public class PrincipalView extends JFrame {
    private ListaPessoasView listaPessoas;
    private ListaUsersView listaUsers;
    private PessoaEditView cadastro;
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

        this.listaPessoas = new ListaPessoasView(this);
        this.listaUsers = new ListaUsersView(this);
        pane.addTab("Pessoas", new SVGUtils("icons/users.svg", 16, 16), listaPessoas.getRootPanel());
        pane.addTab("Usuários", new SVGUtils("icons/users.svg", 16, 16), listaUsers.getRootPanel());

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
