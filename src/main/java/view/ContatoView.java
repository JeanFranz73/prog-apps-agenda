package view;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import utils.FlatSVGIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContatoView extends JFrame {

    private ListaAgendaView lista;
    private CadastroView cadastro;
    private JTabbedPane pane;
    private JMenuBar menu;
    private JMenu opcoesMenu;
    private JCheckBoxMenuItem modoEscuro;

    public ContatoView() {
        changeTemaEscuro(true);
        this.lista = new ListaAgendaView();
        this.cadastro = new CadastroView();
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
    }

    private void initUI() {
        UIManager.put("TitlePane.menuBarEmbedded", true);

        setIconImage(new FlatSVGIcon("icons/aperture.svg").getImage());

        setTitle("Agenda");
        setJMenuBar(menu);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(this.pane);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void changeTemaEscuro(boolean b) {
        if (b) {
            FlatDarculaLaf.setup();
        } else {
            FlatLightLaf.setup();
        }
        FlatLaf.updateUI();
    }
}
