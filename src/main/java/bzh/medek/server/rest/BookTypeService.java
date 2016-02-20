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

import bzh.medek.server.json.book.JsonBooktype;
import bzh.medek.server.persistence.dao.BooktypeDAO;
import bzh.medek.server.persistence.entities.Booktype;
import bzh.medek.server.utils.Constants;

@Stateless
@ApplicationPath("/services")
@Path(value = "/booktypes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookTypeService extends Application {

    private static final Logger LOGGER = Logger.getLogger(BookTypeService.class);
    
    @Inject
    BooktypeDAO booktypeDao;
	
	public BookTypeService () {
	}

    /**
     *  GET /booktypes : retrieve all booktypes
     * 
     * @return
     */
    @GET
    public List<JsonBooktype> getAll() {
    	List<Booktype> booktypes = booktypeDao.getBooktypes();
    	LOGGER.info("find "+booktypes.size()+" booktypes in the database");
    	ArrayList<JsonBooktype> ll = new ArrayList<JsonBooktype>();
    	for (Booktype l:booktypes) {
    		ll.add(new JsonBooktype(l.getId(), l.getName()));
    	}
    	return ll;
    }

    /**
     *  GET /booktypes/{id} : retrieve one booktype
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonBooktype getOne(@PathParam(value = "id") Integer id) {
    	Booktype l = booktypeDao.getBooktype(id);
    	LOGGER.info("find "+l.getName()+" booktype in the database");
    	return new JsonBooktype(l.getId(), l.getName());
    }

    /**
     *  POST /booktypes : create / update one booktype
     * 
     * @param JsonBooktype booktype
     * @return
     */
    @POST
    public JsonBooktype createUpdateOne(JsonBooktype booktype) {
    	JsonBooktype jbooktype = booktype;
    	if (booktype.getId() == null) {
	    	Booktype l = new Booktype();
	    	l.setName(booktype.getName());
	    	booktypeDao.saveBooktype(l);
	    	jbooktype.setId(l.getId());
    	} else {
    		if (booktype.getName().equalsIgnoreCase(Constants.DELETED)) {
    			booktypeDao.removeBooktype(booktypeDao.getBooktype(booktype.getId()));
    		} else {
	        	Booktype l = booktypeDao.getBooktype(booktype.getId());
		    	l.setName(booktype.getName());
	        	booktypeDao.updateBooktype(l);
    		}
    	}
    	return jbooktype;
    }
	
}
