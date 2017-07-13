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

import org.jboss.logging.Logger;

import bzh.medek.server.json.home.JsonCollectionStats;
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
    public JsonCollectionStats loginUser(@Context HttpServletRequest request) {
        User user = userDao.getUserByToken(request.getHeader(Constants.HTTP_HEADER_TOKEN));
        JsonCollectionStats stats = new JsonCollectionStats();
        stats.setAlbums(albumDAO.getUserStats(user.getId()));
        stats.setBooks(bookDAO.getUserStats(user.getId()));
        stats.setMovies(movieDAO.getUserStats(user.getId()));
        stats.setSeries(tvshowDAO.getUserStats(user.getId()));
        return stats;
    }

}
