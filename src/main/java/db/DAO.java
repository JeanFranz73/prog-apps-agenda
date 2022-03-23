package db;

import java.util.*;

/**
 * @author jean.franz
 */
public interface DAO<T> {
    T get(String id);
    void save(T t);
    boolean update(String id, T t);
    void delete(T t);
    List<T> getAll();
}