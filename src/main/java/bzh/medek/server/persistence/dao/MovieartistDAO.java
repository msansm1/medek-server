package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Movieartist;

/**
 * DAO for MOVIEARTIST table
 * 
 * @author msansm1
 *
 */
@Stateless
public class MovieartistDAO extends Dao {

	public void updateMovieartist(Movieartist movieartist) {
		em.merge(movieartist);
	}

	public void saveMovieartist(Movieartist movieartist) {
		em.persist(movieartist);
		em.refresh(movieartist);
	}

	public void removeMovieartist(Movieartist movieartist) {
		em.remove(em.merge(movieartist));
		em.flush();
	}

	public List<Movieartist> getMovieartists() {
		return em.createQuery("from Movieartist", Movieartist.class)
				.getResultList();
	}

	public Movieartist getMovieartist(Integer id) {
		return em.find(Movieartist.class, id);
	}
}
