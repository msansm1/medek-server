package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Artist;

/**
 * DAO for ARTIST table
 * 
 * @author msansm1
 *
 */
@Stateless
public class ArtistDAO extends Dao {

	public void updateArtist(Artist artist) {
		em.merge(artist);
	}

	public void saveArtist(Artist artist) {
		em.persist(artist);
		em.refresh(artist);
	}

	public void removeArtist(Artist artist) {
		em.remove(em.merge(artist));
		em.flush();
	}

	public List<Artist> getArtists() {
		return em.createQuery("from Artist", Artist.class)
				.getResultList();
	}

	public Artist getArtist(Integer id) {
		return em.find(Artist.class, id);
	}
}
