package model;

import db.ContatoDAO;
import lombok.*;
import utils.Valida;

import java.util.List;

/**
 * @author jean.franz
 */
@Getter
@Setter
@NoArgsConstructor
public class Contato {

    private ContatoDAO dao;
    private String user, nome, cpf, telefone;

    public Contato(String user, String nome, String cpf, String telefone) {
        this.dao = new ContatoDAO();
        this.user = user;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public boolean cadastrarContato() {

        boolean result = false;

        if (Valida.validarCPF(getCpf())) {
            dao.save(this);
            result = true;
        }

        return result;
    }

    public List<Contato> getContatos() {
        return dao.getAll();
    }

    public String formatarContato() {
        return getUser() + "," + getNome() + "," + getCpf() + "," + getTelefone() + "\n";
    }

    public Contato getContatoFormatado(String contato) {
        String[] c = contato.split(",");
        return new Contato(c[0], c[1], c[2], c[3]);
    }
}
