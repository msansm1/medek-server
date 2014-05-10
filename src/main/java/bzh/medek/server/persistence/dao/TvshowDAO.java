package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Tvshow;

/**
 * DAO for TVSHOW table
 * 
 * @author msansm1
 *
 */
@Stateless
public class TvshowDAO extends Dao {

	public void updateTvshow(Tvshow tvshow) {
		em.merge(tvshow);
	}

	public void saveTvshow(Tvshow tvshow) {
		em.persist(tvshow);
		em.refresh(tvshow);
	}

	public void removeTvshow(Tvshow tvshow) {
		em.remove(em.merge(tvshow));
		em.flush();
	}

	public List<Tvshow> getTvshows() {
		return em.createQuery("from Tvshow", Tvshow.class)
				.getResultList();
	}

	public Tvshow getTvshow(Integer id) {
		return em.find(Tvshow.class, id);
	}
}
