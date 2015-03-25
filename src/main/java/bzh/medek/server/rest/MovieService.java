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

import bzh.medek.server.json.JsonLang;
import bzh.medek.server.json.movie.JsonMovie;
import bzh.medek.server.persistence.dao.MovieDAO;
import bzh.medek.server.persistence.dao.StorygenreDAO;
import bzh.medek.server.persistence.dao.SupportDAO;
import bzh.medek.server.persistence.entities.Lang;
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
    @Inject
    SupportDAO supportDAO;
    @Inject
    StorygenreDAO storygenreDAO;
	
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
    		lm.add(new JsonMovie(m.getId(), m.getTitle(), m.getDescription(),
    				m.getReleasedate(), m.getCover(), m.getSupportBean().getName(), m.getSupportBean().getId(),
    				m.getStorygenre().getName(), m.getStorygenre().getId(), m.getLength(), m.getIscollector()));
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
    	JsonMovie jm = movieDao.getJsonMovie(id);
    	LOGGER.info("find "+jm.getTitle()+" movie in the database");
    	Movie m = movieDao.getMovie(id);
    	List<JsonLang> ll = new ArrayList<JsonLang>();
    	for (Lang l:m.getLangs2()) {
    		ll.add(new JsonLang(l.getId(), l.getName()));
    	}
    	jm.setLangs(ll);
    	List<JsonLang> ls = new ArrayList<JsonLang>();
    	for (Lang l:m.getLangs1()) {
    		ls.add(new JsonLang(l.getId(), l.getName()));
    	}
    	jm.setSubtitles(ls);
    	return jm;
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
    		m.setDescription(movie.getDescription());
    		m.setReleasedate(movie.getReleaseDate());
    		m.setLength(movie.getLength());
    		m.setIscollector(movie.getIsCollector());
    		m.setSupportBean(supportDAO.getSupport(movie.getSupportId()));
    		m.setStorygenre(storygenreDAO.getStorygenre(movie.getGenreId()));
    		movieDao.saveMovie(m);
	    	jmovie.setId(m.getId());
    	} else {
        	Movie m = movieDao.getMovie(movie.getId());
    		m.setTitle(movie.getTitle());
    		m.setDescription(movie.getDescription());
    		m.setReleasedate(movie.getReleaseDate());
    		m.setLength(movie.getLength());
    		m.setIscollector(movie.getIsCollector());
    		m.setSupportBean(supportDAO.getSupport(movie.getSupportId()));
    		m.setStorygenre(storygenreDAO.getStorygenre(movie.getGenreId()));
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
