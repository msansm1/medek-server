package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

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
	
	public Userbook getUserbook(Integer bookId, Integer userId) {
		TypedQuery<Userbook> q = em.createQuery("from Userbook "
				+ "WHERE id.book=:param1 AND id.user=:param2", Userbook.class);
		q.setParameter("param1", bookId);
		q.setParameter("param2", userId);
		List<Userbook> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
}
