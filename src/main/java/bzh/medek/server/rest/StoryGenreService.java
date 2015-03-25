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

import bzh.medek.server.json.JsonStorygenre;
import bzh.medek.server.persistence.dao.StorygenreDAO;
import bzh.medek.server.persistence.entities.Storygenre;

@Stateless
@ApplicationPath("/services")
@Path(value = "/storygenres")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoryGenreService extends Application {

    private static final Logger LOGGER = Logger.getLogger(StoryGenreService.class);
    
    @Inject
    StorygenreDAO storygenreDao;
	
	public StoryGenreService () {
	}

    /**
     *  GET /storygenres : retrieve all storygenres
     * 
     * @return
     */
    @GET
    public List<JsonStorygenre> getAll() {
    	List<Storygenre> storygenres = storygenreDao.getStorygenres();
    	LOGGER.info("find "+storygenres.size()+" storygenres in the database");
    	ArrayList<JsonStorygenre> ls = new ArrayList<JsonStorygenre>();
    	for (Storygenre s:storygenres) {
    		ls.add(new JsonStorygenre(s.getId(), s.getName()));
    	}
    	return ls;
    }

    /**
     *  GET /storygenres/{id} : retrieve one storygenre
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonStorygenre getOne(@PathParam(value = "id") Integer id) {
    	Storygenre s = storygenreDao.getStorygenre(id);
    	LOGGER.info("find "+s.getName()+" storygenre in the database");
    	return new JsonStorygenre(s.getId(), s.getName());
    }

    /**
     *  POST /storygenres : create / update one storygenre
     * 
     * @param JsonStorygenre storygenre
     * @return
     */
    @POST
    public JsonStorygenre createUpdateOne(JsonStorygenre storygenre) {
    	JsonStorygenre jstorygenre = storygenre;
    	if (storygenre.getId() == null) {
	    	Storygenre s = new Storygenre();
	    	s.setName(storygenre.getName());
	    	storygenreDao.saveStorygenre(s);
	    	jstorygenre.setId(s.getId());
    	} else {
        	Storygenre s = storygenreDao.getStorygenre(storygenre.getId());
	    	s.setName(storygenre.getName());
        	storygenreDao.updateStorygenre(s);
    	}
    	return jstorygenre;
    }
	
}
