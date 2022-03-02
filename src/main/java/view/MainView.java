package view;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import utils.FlatSVGIcon;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.TabbedPaneUI;
import java.awt.event.*;

public class MainView extends JFrame {

    private ListaAgendaView lista;
    private ContatoView cadastro;
    private EditarContatoView edit;
    private JTabbedPane pane;
    private JMenuBar menu;
    private JMenu opcoesMenu;
    private JCheckBoxMenuItem modoEscuro;
    private TabbedPaneUI paneui;

    public MainView() {
        changeTemaEscuro(true);
        this.lista = new ListaAgendaView();
        this.cadastro = new ContatoView();
        this.edit = new EditarContatoView();
        this.pane = new JTabbedPane();
        menu = new JMenuBar();
        initComponents();
        initUI();
    }

    private void initComponents() {

        opcoesMenu = new JMenu("Opções");
        modoEscuro = new JCheckBoxMenuItem("Modo escuro", true);

        modoEscuro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeTemaEscuro(modoEscuro.isSelected());
            }
        });

        opcoesMenu.add(modoEscuro);

        menu.add(opcoesMenu);

        pane.addTab("Listar contatos", new FlatSVGIcon("icons/users.svg", 16, 16), lista.getPanelMain());
        pane.addTab("Novo contato", new FlatSVGIcon("icons/user-plus.svg", 16, 16), cadastro.getPanelMain());
        pane.addTab("Editar contato", new FlatSVGIcon("icons/edit.svg", 16, 16), edit.getPanelMain());
        pane.putClientProperty( "JTabbedPane.tabWidthMode", "compact" );

        pane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lista.loadContatos();
            }
        });
    }

    private void initUI() {
        UIManager.put("TitlePane.menuBarEmbedded", true);

        setIconImage(new FlatSVGIcon("icons/aperture.svg").getImage());

        setTitle("Agenda");
        setJMenuBar(menu);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(this.pane);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void changeTemaEscuro(boolean valor) {
        if (valor) FlatDarkLaf.setup();
        else FlatLightLaf.setup();
        FlatLaf.updateUI();
    }
}
