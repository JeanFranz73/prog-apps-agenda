package dao;

import java.util.*;

/**
 * @author jean.franz
 */
public interface Dao<T> {
    T get(String id);
    List<T> getAll();
    void save(T t);
    boolean update(String id, T t);
    void delete(T t);
}