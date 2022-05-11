package view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import utils.SVGUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class MainView extends JFrame {

    private ListaPessoasView lista;
    private PessoaEditView cadastro;
    private EditarContatoView edit;
    private JTabbedPane pane;
    private JCheckBoxMenuItem modoEscuro;

    public MainView() {
        this.lista = new ListaPessoasView(this);
        this.cadastro = new PessoaEditView();
        this.edit = new EditarContatoView();
        this.pane = new JTabbedPane();
        initComponents();
        initUI();
    }

    private void initComponents() {
        addMenu();
        addListeners();
        pane.addTab("Listar cadastros", new SVGUtils("icons/users.svg", 16, 16), lista.getRootPanel());
        pane.addTab("Novo cadastro", new SVGUtils("icons/user-plus.svg", 16, 16), cadastro.getRootPanel());
        pane.addTab("Editar cadastro", new SVGUtils("icons/edit.svg", 16, 16), edit.getRootPanel());
        pane.putClientProperty("JTabbedPane.tabWidthMode", "compact");
    }

    private void addListeners() {
        modoEscuro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeTemaEscuro(modoEscuro.isSelected());
            }
        });

        pane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lista.loadContatos();
            }
        });
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
        modoEscuro = new JCheckBoxMenuItem("Modo escuro", false);
        opcoesMenu.add(modoEscuro);
        menu.add(opcoesMenu);
        setJMenuBar(menu);
    }

    private void changeTemaEscuro(boolean valor) {
        if (valor) FlatDarkLaf.setup();
        else FlatLightLaf.setup();
        FlatLaf.updateUI();
    }
}
