package dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import model.Contato;

/**
 * @author jean.franz
 */
public class ContatoDAO implements Dao<Contato> {

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

    @Override
    public Contato get(long id) {
        try {
            FileReader leitor = new FileReader(arquivo.getName());
            leitor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Contato();
    }

    @Override
    public List<Contato> getAll() {
        List<Contato> contatos = new ArrayList<Contato>();

        return contatos;
    }

    @Override
    public void save(Contato contato) {
        try {
            FileWriter escritor = new FileWriter(arquivo.getName(), true);
            escritor.write(contato.getContatoFormatado());
            escritor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Próximas aulas, se precisar
    @Override
    public void update(Contato contato, String[] args) {
    }

    @Override
    public void delete(Contato contato) {
    }
}
