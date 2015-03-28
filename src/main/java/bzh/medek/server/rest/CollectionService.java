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

import bzh.medek.server.json.book.JsonCollection;
import bzh.medek.server.persistence.dao.CollectionDAO;
import bzh.medek.server.persistence.entities.Collection;

@Stateless
@ApplicationPath("/services")
@Path(value = "/collections")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CollectionService extends Application {

    private static final Logger LOGGER = Logger.getLogger(CollectionService.class);
    
    @Inject
    CollectionDAO collectionDao;
	
	public CollectionService () {
	}

    /**
     *  GET /collections : retrieve all collections
     * 
     * @return
     */
    @GET
    public List<JsonCollection> getAll() {
    	List<Collection> collections = collectionDao.getCollections();
    	LOGGER.info("find "+collections.size()+" collections in the database");
    	ArrayList<JsonCollection> ll = new ArrayList<JsonCollection>();
    	for (Collection l:collections) {
    		ll.add(new JsonCollection(l.getId(), l.getName()));
    	}
    	return ll;
    }

    /**
     *  GET /collections/{id} : retrieve one collection
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonCollection getOne(@PathParam(value = "id") Integer id) {
    	Collection l = collectionDao.getCollection(id);
    	LOGGER.info("find "+l.getName()+" collection in the database");
    	return new JsonCollection(l.getId(), l.getName());
    }

    /**
     *  POST /collections : create / update one collection
     * 
     * @param JsonCollection collection
     * @return
     */
    @POST
    public JsonCollection createUpdateOne(JsonCollection collection) {
    	JsonCollection jcollection = collection;
    	if (collection.getId() == null) {
	    	Collection l = new Collection();
	    	l.setName(collection.getName());
	    	collectionDao.saveCollection(l);
	    	jcollection.setId(l.getId());
    	} else {
        	Collection l = collectionDao.getCollection(collection.getId());
	    	l.setName(collection.getName());
        	collectionDao.updateCollection(l);
    	}
    	return jcollection;
    }
	
}
