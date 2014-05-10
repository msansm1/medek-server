package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Album;

/**
 * DAO for ALBUM table
 * 
 * @author msansm1
 *
 */
@Stateless
public class AlbumDAO extends Dao {

	public void updateAlbum(Album album) {
		em.merge(album);
	}

	public void saveAlbum(Album album) {
		em.persist(album);
		em.refresh(album);
	}

	public void removeAlbum(Album album) {
		em.remove(em.merge(album));
		em.flush();
	}

	public List<Album> getAlbums() {
		return em.createQuery("from Album", Album.class)
				.getResultList();
	}

	public Album getAlbum(Integer id) {
		return em.find(Album.class, id);
	}
}
