package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.persistence.entities.Usertv;

/**
 * DAO for USERTV table
 * 
 * @author msansm1
 *
 */
@Stateless
public class UsertvDAO extends Dao {

	public void updateUsertv(Usertv usertv) {
		em.merge(usertv);
	}

	public void saveUsertv(Usertv usertv) {
		em.persist(usertv);
	}

	public void removeUsertv(Usertv usertv) {
		em.remove(em.merge(usertv));
		em.flush();
	}

	public List<Usertv> getUsertvs() {
		return em.createQuery("from Usertv", Usertv.class)
				.getResultList();
	}

	public Usertv getUsertv(Integer id) {
		return em.find(Usertv.class, id);
	}
	
	public Usertv getUsertv(Integer tvId, Integer userId) {
		TypedQuery<Usertv> q = em.createQuery("from Usertv "
				+ "WHERE id.tvshow=:param1 AND id.user=:param2", Usertv.class);
		q.setParameter("param1", tvId);
		q.setParameter("param2", userId);
		List<Usertv> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}

	public Usertv getUsertv(Integer tvId, String token) {
		TypedQuery<Usertv> q = em.createQuery("from Usertv "
				+ "WHERE id.tvshow=:param1 AND userBean.token=:param2", Usertv.class);
		q.setParameter("param1", tvId);
		q.setParameter("param2", token);
		List<Usertv> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
}
