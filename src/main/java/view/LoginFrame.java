package view;

import db.DBConnector;
import utils.SVGUtils;
import utils.Validator;

import javax.swing.*;
import java.io.File;
import java.sql.PreparedStatement;

public class LoginFrame extends JFrame {

    private LoginView view;

    public LoginFrame() {
        verificaDb();
        view = new LoginView(this);
        add(view.getRootPanel());
        initComponents();
        addMenu();
    }

    private void initComponents() {
        UIManager.put("TitlePane.menuBarEmbedded", true);
        setIconImage(new SVGUtils("icons/aperture.svg").getImage());
        setTitle("Login");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addMenu() {
        JMenuBar menu = new JMenuBar();
        setJMenuBar(menu);
    }

    private boolean verificaDb() {
        boolean result = false;
        File f = new File("banco.db");
        if (!f.isFile()) {
            System.out.println("Criando arquivo do banco de dados...");
            String queryPessoas = "create table pessoas(id INTEGER not null primary key autoincrement, nome text not null, cpf text not null, telefone text, email text, endereco text);";
            String queryUsers = "create table users(id INTEGER not null primary key autoincrement, username text, pessoa INTEGER default NULL constraint fk_pessoa references pessoas, cargo INTEGER default NULL,password text);";
            String queryAgendamentos = "create table agendamentos(id INTEGER not null primary key autoincrement, cadastrante INTEGER not null references users, paciente INTEGER not null references pessoas, medico INTEGER not null references users, desc INTEGER, data_marcada TEXT, data_registro TEXT not null);";
            String queryAdminPessoa = "insert into pessoas (nome, cpf) values ('Administrador', '12345678910');";
            String queryAdminUser = "insert into users (username, pessoa, cargo, password) values ('admin', 1, 0, 'x61Ey612Kl2gpFL56FT9weDnpSo4AV8j8+qx2AuTHdRyY036xxzTTrw10Wq3+4qQyB+XURPWx1ONxp3Y3pB37A==');";

            try {
                if (f.createNewFile()) {
                    System.out.println("Arquivo banco.db criado, criando tabelas...");

                    PreparedStatement ps;

                    ps = DBConnector.connect().prepareStatement(queryPessoas);
                    ps.executeUpdate();
                    System.out.println("Tabela 'pessoas' criada com sucesso");

                    ps = DBConnector.connect().prepareStatement(queryUsers);
                    ps.executeUpdate();
                    System.out.println("Tabela 'users' criada com sucesso");

                    ps = DBConnector.connect().prepareStatement(queryAgendamentos);
                    ps.executeUpdate();
                    System.out.println("Tabela 'agendamentos' criada com sucesso");

                    ps = DBConnector.connect().prepareStatement(queryAdminPessoa);
                    ps.executeUpdate();
                    ps = DBConnector.connect().prepareStatement(queryAdminUser);
                    ps.executeUpdate();
                    System.out.println("Usuário 'admin' criado com sucesso");

                    System.out.println("-----");
                    System.out.println("Tabelas criadas com sucesso!");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
