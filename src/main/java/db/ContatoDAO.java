package db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import model.Contato;

/**
 * @author jean.franz
 */
public class ContatoDAO {

    private final File arquivo;

    public ContatoDAO() {

        String arquivoPath = (System.getProperty("user.dir") + "\\contatos.csv");
        this.arquivo = new File(arquivoPath);

        try {
            if (arquivo.createNewFile())
                System.out.println("Criando arquivo de contatos");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Contato get(String id) {
        for (int i = 0; i < getAll().size(); i++) {

        }
        return new Contato();
    }

    public Contato getContatoByUser(String user) {
        Contato contato = new Contato();
        for (Contato c : getAll()) {
            if (c.getUser().equals(user)) {
                contato = c;
            }
        }
        return contato;
    }

    public List<Contato> getAll() {
        List<Contato> contatos = new ArrayList();
        try {
            BufferedReader leitor = new BufferedReader(new FileReader(arquivo));
            String contatoAtual;
            while ((contatoAtual = leitor.readLine()) != null) {
                contatos.add(new Contato().getContatoFormatado(contatoAtual));
            }
            leitor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contatos;
    }

    public void save(Contato contato) {
        try {
            FileWriter escritor = new FileWriter(arquivo, true);
            escritor.write(contato.formatarContato());
            escritor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean update(String linha, Contato contato) {
        boolean result = false;
        List<Contato> contatos = getAll();
        try {
            wipe();
            FileWriter escritor = new FileWriter(arquivo, true);

            contatos.set(Integer.parseInt(linha), contato);
            result = true;

            for (Contato c : contatos) {
                escritor.write(c.formatarContato());
            }
            escritor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(Contato contato) {
        try {
            FileWriter escritor = new FileWriter(arquivo);

            for (Contato c : getAll()) {
                if (!c.equals(contato)) {
                    escritor.write(c.formatarContato());
                }
                escritor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int linha) {

        List<Contato> contatos = getAll();

        try {
            wipe();

            FileWriter escritor = new FileWriter(arquivo, true);

            contatos.remove(linha);

            for (Contato c : contatos) {
                escritor.write(c.formatarContato());
            }

            escritor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void wipe() {
        try {
        FileWriter wipe = new FileWriter(arquivo, false);
        wipe.write("");
        wipe.close();
        } catch (Exception e) {
            System.out.println("Wipe falhou!");
            e.printStackTrace();
        }
    }
}
