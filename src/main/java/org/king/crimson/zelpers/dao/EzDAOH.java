package org.king.crimson.zelpers.dao;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pe.albatross.octavia.Octavia;
import pe.albatross.octavia.dynatable.DynatableSql;

public abstract class EzDAOH<T extends Serializable> implements EzDAO<T> {

    private Class<T> clazz;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PersistenceContext
    private EntityManager em;

    protected void setClazz(final Class<T> clazzToSet) {
        clazz = Preconditions.checkNotNull(clazzToSet);
    }

    @Override
    public T find(final long id) {
        return (T) getCurrentSession().find(clazz, id);
    }

    protected T find(Octavia octavia) {
        return (T) octavia.find(this.getCurrentSession());
    }

    @Override
    public List<T> all() {
        String query = "from " + clazz.getName();
        return getCurrentSession().createQuery(query).list();
    }

    protected List<T> all(Octavia octavia) {
        return octavia.all(this.getCurrentSession());
    }

    protected List<T> all(DynatableSql dynatableSql) {
        return dynatableSql.all(this.getCurrentSession());
    }

    @Override
    public List<T> all(List<Long> ids) {
        Query query = getCurrentSession().createQuery("FROM " + clazz.getName() + " c WHERE c.id IN :ids");
        query.setParameterList("ids", ids);
        return query.list();
    }

    @Override
    public void save(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void update(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().merge(entity);
    }

    protected void update(Octavia octavia) {
        octavia.execute(this.getCurrentSession());
    }

    @Override
    public void delete(final T entity) {
        Preconditions.checkNotNull(entity);
        getCurrentSession().delete(entity);
    }

    @Override
    public void delete(final long entityId) {
        final T entity = find(entityId);
        Preconditions.checkState(entity != null);
        delete(entity);
    }

    protected Session getCurrentSession() {
        return (Session) em.unwrap(Session.class);
    }
}
