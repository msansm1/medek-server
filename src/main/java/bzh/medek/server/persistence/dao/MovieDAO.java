package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Movie;

/**
 * DAO for MOVIE table
 * 
 * @author msansm1
 *
 */
@Stateless
public class MovieDAO extends Dao {

	public void updateMovie(Movie movie) {
		em.merge(movie);
	}

	public void saveMovie(Movie movie) {
		em.persist(movie);
		em.refresh(movie);
	}

	public void removeMovie(Movie movie) {
		em.remove(em.merge(movie));
		em.flush();
	}

	public List<Movie> getMovies() {
		return em.createQuery("from Movie", Movie.class)
				.getResultList();
	}

	public Movie getMovie(Integer id) {
		return em.find(Movie.class, id);
	}
}
