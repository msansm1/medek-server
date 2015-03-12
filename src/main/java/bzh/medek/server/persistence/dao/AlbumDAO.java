package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.album.JsonAlbum;
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
		return em.createQuery("from Album", Album.class).getResultList();
	}

	public Album getAlbum(Integer id) {
		return em.find(Album.class, id);
	}

	public List<JsonAlbum> getUsersAlbums(Integer id) {
		TypedQuery<JsonAlbum> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.album.JsonAlbum(a.id, a.title, "
						+ "a.cover)"
						+ "from Album a, Useralbum ua, User u "
						+ "where a.id=ua.id.album and ua.id.user=u.id and u.id=:param1",
				JsonAlbum.class);
		q.setParameter("param1", id);
		return q.getResultList();
	}
}
