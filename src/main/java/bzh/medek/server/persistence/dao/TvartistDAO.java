package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Tvartist;

/**
 * DAO for TVARTIST table
 * 
 * @author msansm1
 *
 */
@Stateless
public class TvartistDAO extends Dao {

	public void updateTvartist(Tvartist tvartist) {
		em.merge(tvartist);
	}

	public void saveTvartist(Tvartist tvartist) {
		em.persist(tvartist);
		em.refresh(tvartist);
	}

	public void removeTvartist(Tvartist tvartist) {
		em.remove(em.merge(tvartist));
		em.flush();
	}

	public List<Tvartist> getTvartists() {
		return em.createQuery("from Tvartist", Tvartist.class)
				.getResultList();
	}

	public Tvartist getTvartist(Integer id) {
		return em.find(Tvartist.class, id);
	}
}
