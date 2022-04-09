package dao;

import java.util.*;

/**
 * @author jean.franz
 */
public interface DAOI<T> {
    T get(int id);
    void save(T t);
    boolean update(String id, T t);
    void delete(T t);
    List<T> getAll();
}