package dao;

import model.Agendamento;

import java.util.List;

public abstract class AgendamentoDAO implements DAOI<Agendamento> {

    public abstract Agendamento get(Long id);
    public abstract boolean save(Agendamento agendamento);
    public abstract boolean update(Long id, Agendamento agendamento);
    public abstract void delete(Agendamento agendamento);
    public abstract List<Agendamento> getAll();
}
