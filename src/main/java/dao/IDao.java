package dao;

import java.util.List;

/**
 * Interface DAO generique definissant les operations CRUD.
 */
public interface IDao<T> {
    boolean create(T o);
    boolean delete(T o);
    boolean update(T o);
    T getById(int id);
    List<T> getAll();
}
