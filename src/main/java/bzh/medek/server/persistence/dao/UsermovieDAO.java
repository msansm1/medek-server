package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

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
		em.refresh(usermovie);
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
}
