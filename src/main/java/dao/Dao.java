package dao;

import java.util.*;

/**
 * @author jean.franz
 */
public interface Dao<T> {
    
    T get(long id);
    
    List<T> getAll();
    
    void save(T t);
    
    void update(T t, String[] params);
    
    void delete(T t);
}