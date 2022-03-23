package view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import utils.FlatSVGIcon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;

public class MainView extends JFrame {

    private ListaAgendaView lista;
    private ContatoView cadastro;
    private EditarContatoView edit;
    private JTabbedPane pane;
    private JCheckBoxMenuItem modoEscuro;

    public MainView() {
        changeTemaEscuro(true);
        this.lista = new ListaAgendaView();
        this.cadastro = new ContatoView();
        this.edit = new EditarContatoView();
        this.pane = new JTabbedPane();
        initComponents();
        initUI();
    }

    private void initComponents() {
        addMenu();
        addListeners();
        pane.addTab("Listar contatos", new FlatSVGIcon("icons/users.svg", 16, 16), lista.getRootPanel());
        pane.addTab("Novo contato", new FlatSVGIcon("icons/user-plus.svg", 16, 16), cadastro.getRootPanel());
        pane.addTab("Editar contato", new FlatSVGIcon("icons/edit.svg", 16, 16), edit.getRootPanel());
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
        setIconImage(new FlatSVGIcon("icons/aperture.svg").getImage());

        setTitle("Agenda");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(this.pane);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addMenu() {
        JMenuBar menu = new JMenuBar();
        JMenu opcoesMenu = new JMenu("Opções");
        modoEscuro = new JCheckBoxMenuItem("Modo escuro", true);
        opcoesMenu.add(modoEscuro);
        menu.add(opcoesMenu);
        setJMenuBar(menu);
    }

    private void changeTemaEscuro(boolean valor) {
        if (valor) FlatNordIJTheme.setup();
        else FlatLightLaf.setup();
        FlatLaf.updateUI();
    }
}
