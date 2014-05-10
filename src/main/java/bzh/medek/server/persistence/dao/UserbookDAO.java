package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Userbook;

/**
 * DAO for USERBOOK table
 * 
 * @author msansm1
 *
 */
@Stateless
public class UserbookDAO extends Dao {

	public void updateUserbook(Userbook userbook) {
		em.merge(userbook);
	}

	public void saveUserbook(Userbook userbook) {
		em.persist(userbook);
		em.refresh(userbook);
	}

	public void removeUserbook(Userbook userbook) {
		em.remove(em.merge(userbook));
		em.flush();
	}

	public List<Userbook> getUserbooks() {
		return em.createQuery("from Userbook", Userbook.class)
				.getResultList();
	}

	public Userbook getUserbook(Integer id) {
		return em.find(Userbook.class, id);
	}
}
