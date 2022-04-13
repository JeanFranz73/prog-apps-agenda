package dao;

import java.util.*;

/**
 * @author jean.franz
 */
public interface DAOI<T> {
    T get(Long id);
    boolean save(T t);
    boolean update(Long id, T t);
    void delete(T t);
    List<T> getAll();
}