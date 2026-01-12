package org.informatics.dao;

import java.util.List;

public interface BaseDao<T, ID> {
    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    void delete(T entity);
}
