package dao;

import model.User;

import java.util.List;

public abstract class UserDAO implements DAOI<User> {

    public abstract User get(Long id);
    public abstract boolean save(User user);
    public abstract boolean update(Long id, User user);
    public abstract void delete(User user);
    public abstract List<User> getAll();
}
