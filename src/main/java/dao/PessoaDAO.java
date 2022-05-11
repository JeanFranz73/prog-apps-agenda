package dao;

import model.Pessoa;

import java.util.List;

public abstract class PessoaDAO implements DAOI<Pessoa> {

    public abstract Pessoa get(Long id);
    public abstract boolean save(Pessoa pessoa);
    public abstract boolean update(Long id, Pessoa pessoa);
    public abstract void delete(Pessoa pessoa);
    public abstract List<Pessoa> getAll();
}
