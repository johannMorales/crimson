package org.king.crimson.zelpers.dao;

import java.io.Serializable;
import java.util.List;

public interface EzDAO<T extends Serializable> {

    T find(final long id);

    List<T> all();

    List<T> all(List<Long> ids);

    void save(final T entity);

    void update(final T entity);

    void delete(final T entity);

    void delete(final long id);

}
