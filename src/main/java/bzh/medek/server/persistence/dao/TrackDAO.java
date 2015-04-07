package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Track;

/**
 * DAO for TRACK table
 * 
 * @author msansm1
 *
 */
@Stateless
public class TrackDAO extends Dao {

	public void updateTrack(Track track) {
		em.merge(track);
	}

	public void saveTrack(Track track) {
		em.persist(track);
		em.refresh(track);
	}

	public void removeTrack(Track track) {
		em.remove(em.merge(track));
		em.flush();
	}

	public List<Track> getTracks() {
		return em.createQuery("from Track", Track.class)
				.getResultList();
	}

	public Track getTrack(Integer id) {
		return em.find(Track.class, id);
	}

	public List<Track> getTracksForAlbum(Integer albumId) {
		return em.createQuery("from Track t where t.albumBean.id='"+albumId+"'", Track.class)
				.getResultList();
	}
}
