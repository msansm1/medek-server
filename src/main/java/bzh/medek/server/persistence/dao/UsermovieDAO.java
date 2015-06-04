package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.persistence.entities.Usermovie;

/**
 * DAO for USERMOVIE table
 * 
 * @author msansm1
 *
 */
@Stateless
public class UsermovieDAO extends Dao {

	public void updateUsermovie(Usermovie usermovie) {
		em.merge(usermovie);
	}

	public void saveUsermovie(Usermovie usermovie) {
		em.persist(usermovie);
	}

	public void removeUsermovie(Usermovie usermovie) {
		em.remove(em.merge(usermovie));
		em.flush();
	}

	public List<Usermovie> getUsermovies() {
		return em.createQuery("from Usermovie", Usermovie.class)
				.getResultList();
	}

	public Usermovie getUsermovie(Integer id) {
		return em.find(Usermovie.class, id);
	}
	
	public Usermovie getUsermovie(Integer movieId, Integer userId) {
		TypedQuery<Usermovie> q = em.createQuery("from Usermovie "
				+ "WHERE id.movie=:param1 AND id.user=:param2", Usermovie.class);
		q.setParameter("param1", movieId);
		q.setParameter("param2", userId);
		List<Usermovie> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
}
