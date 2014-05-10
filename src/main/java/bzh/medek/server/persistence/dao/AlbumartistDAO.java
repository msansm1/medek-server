package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Albumartist;

/**
 * DAO for ALBUMARTIST table
 * 
 * @author msansm1
 *
 */
@Stateless
public class AlbumartistDAO extends Dao {

	public void updateAlbumartist(Albumartist albumartist) {
		em.merge(albumartist);
	}

	public void saveAlbumartist(Albumartist albumartist) {
		em.persist(albumartist);
		em.refresh(albumartist);
	}

	public void removeAlbumartist(Albumartist albumartist) {
		em.remove(em.merge(albumartist));
		em.flush();
	}

	public List<Albumartist> getAlbumartists() {
		return em.createQuery("from Albumartist", Albumartist.class)
				.getResultList();
	}

	public Albumartist getAlbumartist(Integer id) {
		return em.find(Albumartist.class, id);
	}
}
