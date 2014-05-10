package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Genre;

/**
 * DAO for GENRE table
 * 
 * @author msansm1
 *
 */
@Stateless
public class GenreDAO extends Dao {

	public void updateGenre(Genre genre) {
		em.merge(genre);
	}

	public void saveGenre(Genre genre) {
		em.persist(genre);
		em.refresh(genre);
	}

	public void removeGenre(Genre genre) {
		em.remove(em.merge(genre));
		em.flush();
	}

	public List<Genre> getGenres() {
		return em.createQuery("from Genre", Genre.class)
				.getResultList();
	}

	public Genre getGenre(Integer id) {
		return em.find(Genre.class, id);
	}
}
