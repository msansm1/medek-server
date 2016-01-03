package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.persistence.entities.Useralbum;
import bzh.medek.server.persistence.entities.UseralbumPK;

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
	}

	public void removeUseralbum(Useralbum useralbum) {
		em.remove(em.merge(useralbum));
		em.flush();
	}

	public List<Useralbum> getUseralbums() {
		return em.createQuery("from Useralbum", Useralbum.class)
				.getResultList();
	}

	public Useralbum getUseralbum(UseralbumPK id) {
		return em.find(Useralbum.class, id);
	}
	
	public Useralbum getUseralbum(Integer albumId, Integer userId) {
		TypedQuery<Useralbum> q = em.createQuery("from Useralbum "
				+ "WHERE id.album=:param1 AND id.user=:param2", Useralbum.class);
		q.setParameter("param1", albumId);
		q.setParameter("param2", userId);
		List<Useralbum> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
	
	public Useralbum getUseralbum(Integer albumId, String userToken) {
		TypedQuery<Useralbum> q = em.createQuery("from Useralbum "
				+ "WHERE id.album=:param1 AND userBean.token=:param2", Useralbum.class);
		q.setParameter("param1", albumId);
		q.setParameter("param2", userToken);
		List<Useralbum> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
}
