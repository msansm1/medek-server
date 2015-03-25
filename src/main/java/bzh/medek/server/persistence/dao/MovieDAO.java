package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.movie.JsonMovie;
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
		return em.createQuery("from Movie", Movie.class).getResultList();
	}

	public Movie getMovie(Integer id) {
		return em.find(Movie.class, id);
	}

	public List<JsonMovie> getUsersMovies(Integer id) {
		TypedQuery<JsonMovie> q = em
				.createQuery(
						"SELECT NEW bzh.medek.server.json.movie.JsonMovie(m.id, m.title, "
								+ "m.description, m.releasedate, m.cover, m.supportBean.name, "
								+ "m.supportBean.id, m.storygenre.name, m.storygenre.id, "
								+ "m.length, m.iscollector) "
								+ "from Movie m, Usermovie um, User u "
								+ "where m.id=um.id.movie and um.id.user=u.id and u.id=:param1",
						JsonMovie.class);
		q.setParameter("param1", id);
		return q.getResultList();
	}

	public JsonMovie getJsonMovie(Integer id) {
		TypedQuery<JsonMovie> q = em
				.createQuery(
						"SELECT NEW bzh.medek.server.json.movie.JsonMovie(m.id, m.title, "
								+ "m.description, m.releasedate, m.cover, m.supportBean.name, "
								+ "m.supportBean.id, m.storygenre.name, m.storygenre.id, "
								+ "m.length, m.iscollector) from Movie m "
								+ "where m.id=:param1", JsonMovie.class);
		q.setParameter("param1", id);
		List<JsonMovie> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
}
