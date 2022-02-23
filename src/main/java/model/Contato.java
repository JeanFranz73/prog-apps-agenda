package model;

import dao.ContatoDAO;
import lombok.*;
import utils.Valida;

/**
 * @author jean.franz
 */
@Getter
@Setter
@NoArgsConstructor
public class Contato {

    private ContatoDAO dao;
    private String nome, id, cpf, telefone;

    public Contato(String nome, String cpf, String telefone) {
        this.dao = new ContatoDAO();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        dao.get(5);
    }

    public boolean cadastrarContato() {

        boolean result = false;

        if (Valida.validarCPF(getCpf())) {
            dao.save(this);
            result = true;
        }

        return result;
    }

    public String getContatoFormatado() {
        return getNome() + "," + getCpf() + "," + getTelefone() + "\n";
    }
}
