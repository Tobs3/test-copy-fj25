package de.hhbk.immoweg24.dao;

import de.hhbk.immoweg24.model.ModelTemplate;
import de.hhbk.immoweg24.utils.HibernateUtil;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 * DAO (Data Access Object): Das Dao ist für den direkten Datenbankzugriff 
 * verantwortlich. Der DAO abstrahiert die Datenbankinteraktionen und stellt 
 * Methoden zur Verfügung, um Informationen aus der Datenbank zu lesen, 
 * zu speichern, zu aktualisieren und zu löschen. 
 */
public class GenericDao<T extends ModelTemplate> implements Serializable {
    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------     

    public GenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    //-------------------------------------------------------------------------
    //  Class
    //-------------------------------------------------------------------------     
    protected Class<T> clazz = null;

    public void setClazz(Class<T> c) {
        this.clazz = c;
    }

    public Class<T> getClazz() {
        return this.clazz;
    }

    //-------------------------------------------------------------------------
    //  Execute
    //-------------------------------------------------------------------------  
    protected Object executeQuery(Function<Session, Object> code) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return code.apply(session);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    protected Object executeTransaction(Function<Session, Object> code) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Object result = code.apply(session);
                tx.commit();
                return result;
            } catch (Exception e) {
                System.out.println("ERROR --> " + e.getMessage());
                tx.rollback();
                throw new Exception(e);
            }
        }
    }

    //-------------------------------------------------------------------------
    //  Item
    //-------------------------------------------------------------------------     
    public T getNewEntityInstance() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public T getById(long id) throws Exception {
        return (T) executeTransaction((session) -> {
            return session.get(getClazz(), id);
        });
    }

    public T save(T entity) throws Exception {
        return (T) executeTransaction(session -> {
            if (entity.getId() <= 0) {
                session.persist(entity); // Neue Entität ohne gültige ID
            } else {
                session.merge(entity); 
            }
            return entity;
        });
    }

    public T merge(T o) throws Exception {
        return (T) executeTransaction((session) -> {
            return session.merge(o);
        });
    }

//    public boolean deleteItem(T o) throws Exception
//    { 
//        return (boolean) executeTransaction( (session) -> { try { session.delete(session.get(getClazz(), o.getId())); return true; } catch (Exception ex) { return false; } } );
//    } 
    public boolean deleteItem(T o) throws Exception {
        return (boolean) executeTransaction(session -> {
            return Optional.ofNullable(session.get(getClazz(), o.getId()))
                    .map(entity
                            -> {
                        session.remove(entity);
                        return true;
                    }).orElse(false);
        });
    }

    public int deleteAll() throws Exception {
        return (int) executeTransaction(session
                -> {
            Query<?> query = session.createQuery("DELETE FROM " + getClazz().getName(), getClazz());
            return query.executeUpdate();
        });
    }

    //-------------------------------------------------------------------------
    //  List
    //-------------------------------------------------------------------------     
    public List<T> list(int first, int limit) throws Exception {
        return (List<T>) executeQuery(session
                -> {
            session.clear();
            Query<T> query = session.createQuery("FROM " + getClazz().getName(), getClazz());
            if (first > 0) {
                query.setFirstResult(first);
            }
            if (limit > 0) {
                query.setMaxResults(limit);
            }
            return query.getResultList();
        });
    }

    public List<T> list() throws Exception {
        return list(-1, -1);
    }

}
