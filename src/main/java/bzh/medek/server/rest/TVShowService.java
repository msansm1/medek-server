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

import bzh.medek.server.json.tvshow.JsonShow;
import bzh.medek.server.persistence.dao.TvshowDAO;
import bzh.medek.server.persistence.entities.Tvshow;

@Stateless
@ApplicationPath("/services")
@Path(value = "/tvshows")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TVShowService extends Application {

    private static final Logger LOGGER = Logger.getLogger(TVShowService.class);
    
    @Inject
    TvshowDAO showDao;
	
	public TVShowService () {
	}

    /**
     *  GET /shows : retrieve all shows
     * 
     * @return
     */
    @GET
    public List<JsonShow> getAll() {
    	List<Tvshow> shows = showDao.getTvshows();
    	LOGGER.info("find "+shows.size()+" shows in the database");
    	ArrayList<JsonShow> ls = new ArrayList<JsonShow>();
    	for (Tvshow s:shows) {
    		ls.add(new JsonShow(s.getId(), s.getTitle(), s.getCover(), s.getDescription()));
    	}
    	return ls;
    }

    /**
     *  GET /shows/{id} : retrieve one show
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonShow getOne(@PathParam(value = "id") Integer id) {
    	Tvshow b = showDao.getTvshow(id);
    	LOGGER.info("find "+b.getTitle()+" show in the database");
    	return new JsonShow(b.getId(), b.getTitle(), b.getCover(), b.getDescription());
    }

    /**
     *  POST /shows : create / update one show
     * 
     * @param id
     * @return
     */
    @POST
    public JsonShow createOne(JsonShow show) {
    	JsonShow jshow = show;
    	if (show.getId() == null) {
    		Tvshow s = new Tvshow();
    		s.setCover(show.getCover());
    		s.setDescription(show.getDescription());
    		s.setTitle(show.getTitle());
	    	showDao.saveTvshow(s);
	    	jshow.setId(s.getId());
    	} else {
    		Tvshow s = showDao.getTvshow(show.getId());
    		s.setCover(show.getCover());
    		s.setDescription(show.getDescription());
    		s.setTitle(show.getTitle());
	    	showDao.updateTvshow(s);
    	}
    	return jshow;
    }

    /**
     *  GET /shows/user/{id} : retrieve shows for one user
     * 
     * @param id - user ID
     * @return
     */
    @GET
    @Path(value = "/user/{id}")
    public List<JsonShow> getUserShows(@PathParam(value = "id") Integer id) {
    	return showDao.getUsersTvshows(id);
    }
	
}
