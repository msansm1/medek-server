package bzh.medek.server.persistence.dao;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract class for DAO settings (All DAO classes must extend it)
 * 
 * @author PC_Projets02
 * 
 */
public abstract class Dao {

    @PersistenceContext(unitName = "medekserver")
    EntityManager em;

    @Resource
    SessionContext ctx;

    <T> T find(Class<T> entity, int primaryKey) {
        T ent = em.find(entity, primaryKey);
        em.refresh(ent);
        return ent;
    }

}
