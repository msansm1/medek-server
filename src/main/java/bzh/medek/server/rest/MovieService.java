package bzh.medek.server.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import bzh.medek.server.json.movie.JsonMovie;
import bzh.medek.server.persistence.dao.MovieDAO;
import bzh.medek.server.persistence.entities.Movie;

@Stateless
@ApplicationPath("/services")
@Path(value = "/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieService extends Application {

    private static final Logger LOGGER = Logger.getLogger(MovieService.class);
    
    @Inject
    MovieDAO movieDao;
	
	public MovieService () {
	}

    /**
     *  GET /movies : retrieve all movies
     * 
     * @return
     */
    @GET
    public List<JsonMovie> getAll() {
    	List<Movie> movies = movieDao.getMovies();
    	LOGGER.info("find "+movies.size()+" movies in the database");
    	ArrayList<JsonMovie> lm = new ArrayList<JsonMovie>();
    	for (Movie m : movies) {
    		lm.add(new JsonMovie(m.getId(), m.getTitle()));
    	}
    	return lm;
    }

    /**
     *  GET /movies/{id} : retrieve one movie
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonMovie getOne(@PathParam(value = "id") Integer id) {
    	Movie b = movieDao.getMovie(id);
    	LOGGER.info("find "+b.getTitle()+" movie in the database");
    	return new JsonMovie(b.getId(), b.getTitle());
    }

    /**
     *  POST /movies : create / update one movie
     * 
     * @param id
     * @return
     */
    @POST
    public JsonMovie createUpdateOne(JsonMovie movie) {
    	JsonMovie jmovie = movie;
    	if (movie.getId() == null) {
    		Movie m = new Movie();
    		m.setTitle(movie.getTitle());
    		movieDao.saveMovie(m);
	    	jmovie.setId(m.getId());
    	} else {
        	Movie m = movieDao.getMovie(movie.getId());
    		m.setTitle(movie.getTitle());
    		movieDao.updateMovie(m);
    	}
    	return jmovie;
    }

    /**
     *  GET /movies/user/{id} : retrieve movie for one user
     * 
     * @param id - user ID
     * @return
     */
    @GET
    @Path(value = "/user/{id}")
    public List<JsonMovie> getUserMovies(@PathParam(value = "id") Integer id) {
    	return movieDao.getUsersMovies(id);
    }
    
}
