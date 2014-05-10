package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Useralbum;

/**
 * DAO for USERALBUM table
 * 
 * @author msansm1
 *
 */
@Stateless
public class UseralbumDAO extends Dao {

	public void updateUseralbum(Useralbum useralbum) {
		em.merge(useralbum);
	}

	public void saveUseralbum(Useralbum useralbum) {
		em.persist(useralbum);
		em.refresh(useralbum);
	}

	public void removeUseralbum(Useralbum useralbum) {
		em.remove(em.merge(useralbum));
		em.flush();
	}

	public List<Useralbum> getUseralbums() {
		return em.createQuery("from Useralbum", Useralbum.class)
				.getResultList();
	}

	public Useralbum getUseralbum(Integer id) {
		return em.find(Useralbum.class, id);
	}
}
