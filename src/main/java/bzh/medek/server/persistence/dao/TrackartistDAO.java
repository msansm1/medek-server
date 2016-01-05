package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Trackartist;
import bzh.medek.server.persistence.entities.TrackartistPK;

/**
 * DAO for TRACKARTIST table
 * 
 * @author msansm1
 *
 */
@Stateless
public class TrackartistDAO extends Dao {

	public void updateTrackartist(Trackartist trackartist) {
		em.merge(trackartist);
	}

	public void saveTrackartist(Trackartist trackartist) {
		em.persist(trackartist);
	}

	public void removeTrackartist(Trackartist trackartist) {
		em.remove(em.merge(trackartist));
		em.flush();
	}

	public List<Trackartist> getTrackartists() {
		return em.createQuery("from Trackartist", Trackartist.class)
				.getResultList();
	}

	public Trackartist getTrackartist(TrackartistPK taid) {
		return em.find(Trackartist.class, taid);
	}
}
