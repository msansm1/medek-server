package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Database;

/**
 * DAO for DATABASE table
 * 
 * @author msansm1
 *
 */
@Stateless
public class DatabaseDAO extends Dao {

	public void updateDatabase(Database dataBase) {
		em.merge(dataBase);
	}

	public void saveDatabase(Database dataBase) {
		em.persist(dataBase);
		em.refresh(dataBase);
	}

	public void removeDatabase(Database dataBase) {
		em.remove(em.merge(dataBase));
		em.flush();
	}

	public List<Database> getAllDatabases() {
		return em.createQuery("from Database", Database.class)
				.getResultList();
	}

	public Database getDatabase(Integer id) {
		return em.find(Database.class, id);
	}
}
