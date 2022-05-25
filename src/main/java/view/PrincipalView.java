package view;

import lombok.Getter;
import utils.Config;
import utils.SVGUtils;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PrincipalView extends JFrame {
    private ListaPessoasView listaPessoas;
    private ListaUsersView listaUsers;
    private ListaAgendamentosView agendamentosView;
    private PessoaEditView cadastro;
    private JTabbedPane pane;
    private JCheckBoxMenuItem modoEscuro;
    private JToolBar toolBar;

    @Getter
    private JPanel rootPanel;
    private JPanel headerPanel;
    private JLabel nameText;
    private JButton addAgendamentoButton;

    public PrincipalView() {
        initComponents();
        initUI();
    }

    private void initComponents() {
        addMenu();

        this.agendamentosView = new ListaAgendamentosView(this);
        this.listaPessoas = new ListaPessoasView(this);
        this.listaUsers = new ListaUsersView(this);

        pane.addTab("Agendamentos", new SVGUtils("icons/list.svg", 16, 16), agendamentosView.getRootPanel());
        pane.addTab("Pessoas", new SVGUtils("icons/users.svg", 16, 16), listaPessoas.getRootPanel());
        pane.addTab("Usuários", new SVGUtils("icons/users.svg", 16, 16), listaUsers.getRootPanel());

        // pane.putClientProperty("JTabbedPane.tabWidthMode", "compact");

        pane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                System.out.println("mudou");
                agendamentosView.loadAgendamentos();
                listaPessoas.loadPessoas();
                listaUsers.loadUsers();
            }
        });
    }

        private void initUI () {
            setIconImage(new SVGUtils("icons/aperture.svg").getImage());

            nameText.setText(String.format("Olá %s!", Config.getLoggedUser().getPessoa().getNome()));

            if (Config.isAdmin()) {
                setTitle("Consultório Clínico - ADMIN MODE");
            } else {
                setTitle("Consultório Clínico");
            }
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setContentPane(this.rootPanel);
            setSize(1280, 720);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        private void addMenu () {
            JMenuBar menu = new JMenuBar();
            JMenu opcoesMenu = new JMenu("Opções");
            modoEscuro = new JCheckBoxMenuItem("Checkbox", false);
            opcoesMenu.add(modoEscuro);
            menu.add(opcoesMenu);
            setJMenuBar(menu);
        }
    }
