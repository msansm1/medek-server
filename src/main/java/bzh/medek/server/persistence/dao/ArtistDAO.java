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

	public List<Artist> getArtistsForAlbum() {
		return em.createQuery("from Artist a WHERE a.artisttype.id='1'", Artist.class)
				.getResultList();
	}

	public List<Artist> getArtistsForBook() {
		return em.createQuery("from Artist a WHERE a.artisttype.id='2'", Artist.class)
				.getResultList();
	}

	public List<Artist> getArtistsForMovie() {
		return em.createQuery("from Artist a WHERE a.artisttype.id IN ('3','4','5')", Artist.class)
				.getResultList();
	}

	public List<Artist> getArtistsForSeries() {
		return em.createQuery("from Artist a WHERE a.artisttype.id IN ('3','4','5')", Artist.class)
				.getResultList();
	}

	public Artist findArtistByName(String artist) {
		List<Artist> la = em.createQuery("from Artist where name=:name", Artist.class)
				.setParameter("name", artist)
				.getResultList();
		if (la.isEmpty()) {
			return null;
		} else {
			return la.get(0);
		}
	}
}
