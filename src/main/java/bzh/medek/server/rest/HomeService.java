package bzh.medek.server.rest;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import bzh.medek.server.json.home.AlbumStats;
import bzh.medek.server.json.home.BookStats;
import bzh.medek.server.json.home.JsonCollectionStats;
import bzh.medek.server.json.home.MovieStats;
import bzh.medek.server.json.home.SerieStats;
import bzh.medek.server.persistence.dao.AlbumDAO;
import bzh.medek.server.persistence.dao.BookDAO;
import bzh.medek.server.persistence.dao.MovieDAO;
import bzh.medek.server.persistence.dao.TvshowDAO;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.entities.User;
import bzh.medek.server.utils.Constants;

/**
 * Auth service (/services/auth)
 * 
 * @author ebarona
 * 
 */
@Stateless
@ApplicationPath("/services")
@Path(value = "/home")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HomeService extends Application {

	private static final Logger LOGGER = Logger.getLogger(HomeService.class);

	@Inject
	UserDAO userDao;
	@Inject
	AlbumDAO albumDAO;
	@Inject
	BookDAO bookDAO;
	@Inject
	MovieDAO movieDAO;
	@Inject
	TvshowDAO tvshowDAO;

	public HomeService() {
	}

	/**
	 * GET /mycollec : collection stats for an user
	 * 
	 * @return
	 */
	@GET
	@Path("/mycollec")
	public JsonCollectionStats userStats(@Context HttpServletRequest request) {
		JsonCollectionStats stats = new JsonCollectionStats();
		User user = userDao.getUserByToken(request.getHeader(Constants.HTTP_HEADER_TOKEN));
		if (user != null) {
			stats.setAlbums(albumDAO.getUserStats(user.getId()));
			stats.setBooks(bookDAO.getUserStats(user.getId()));
			stats.setMovies(movieDAO.getUserStats(user.getId()));
			stats.setSeries(tvshowDAO.getUserStats(user.getId()));
		} else {
			stats.setAlbums(new AlbumStats(Long.valueOf(0)));
			stats.setBooks(new BookStats(Long.valueOf(0)));
			stats.setMovies(new MovieStats(Long.valueOf(0)));
			stats.setSeries(new SerieStats(Long.valueOf(0)));
		}
		return stats;
	}
	
	/**
	 * GET /allcollec : collection stats (for all database)
	 * 
	 * @return
	 */
	@GET
	@Path("/allcollec")
	public JsonCollectionStats allStats() {
		JsonCollectionStats stats = new JsonCollectionStats();
		stats.setAlbums(new AlbumStats(Long.valueOf(albumDAO.getAlbums().size())));
		stats.setBooks(new BookStats(Long.valueOf(bookDAO.getBooks().size())));
		stats.setMovies(new MovieStats(Long.valueOf(movieDAO.getMovies().size())));
		stats.setSeries(new SerieStats(Long.valueOf(tvshowDAO.getTvshows().size())));
		return stats;
	}

}
