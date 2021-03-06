package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.home.MovieStats;
import bzh.medek.server.json.movie.JsonMovie;
import bzh.medek.server.persistence.entities.Movie;
import bzh.medek.server.persistence.entities.Movieartist;

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

	public List<Movie> getMoviesForList(int index, int limit, String orderBy,
			String orderDir) {
		String dir = "DESC";
		if (orderDir != null) {
			dir = orderDir;
		}
		return em.createQuery("FROM Movie ORDER BY "+orderBy+" "+dir, Movie.class)
				.setFirstResult(index)
				.setMaxResults(limit)
				.getResultList();
	}

	public MovieStats getUserStats(Integer userId) {
		TypedQuery<MovieStats> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.home.MovieStats(COUNT(um.movieBean.id))"
						+ "from Usermovie um "
						+ "where um.id.user=:userId",
						MovieStats.class);
		q.setParameter("userId", userId);
		return q.getSingleResult();
	}

	public List<JsonMovie> getUserMovies(int index, int limit, String orderBy,
			String orderDir, Integer userId) {
		String dir = "DESC";
		if (orderDir != null) {
			dir = orderDir;
		}
		return em.createQuery("SELECT NEW bzh.medek.server.json.movie.JsonMovie(m.id, m.title, "
				+ "m.description, m.releasedate, m.cover, m.supportBean.name, m.supportBean.id, "
				+ "m.storygenre.name, m.storygenre.id, m.length, m.iscollector, true, um.rating) "
				+ "FROM Movie m INNER JOIN m.usermovies um "
				+ "where um.id.user=:param1 ORDER BY "+orderBy+" "+dir, JsonMovie.class)
				.setParameter("param1", userId)
				.setFirstResult(index)
				.setMaxResults(limit)
				.getResultList();
	}

	public List<Movieartist> getMovieArtists(Integer movieId) {
		return em.createQuery("from Movieartist ma where ma.id.movie=:param1", Movieartist.class)
				.setParameter("param1", movieId)
				.getResultList();
	}
}
