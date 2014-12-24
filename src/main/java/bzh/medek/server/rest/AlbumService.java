package bzh.medek.server.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import bzh.medek.server.json.JsonResponse;
import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.persistence.dao.AlbumDAO;
import bzh.medek.server.persistence.entities.Album;

@Stateless
@ApplicationPath("/services")
@Path(value = "/albums")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlbumService extends Application {

    private static final Logger LOGGER = Logger.getLogger(AlbumService.class);
    
    @Inject
    AlbumDAO albumDao;
	
	public AlbumService () {
	}

    /**
     *  GET /albums : retrieve all albums
     * 
     * @return
     */
    @GET
    public List<Album> getAll() {
    	List<Album> b = albumDao.getAlbums();
    	LOGGER.info("find "+b.size()+" albums in the database");
    	return b;
    }

    /**
     *  GET /albums/{id} : retrieve one album
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public Album getOne(@PathParam(value = "id") Integer id) {
    	Album b = albumDao.getAlbum(id);
    	LOGGER.info("find "+b.getTitle()+" album in the database");
    	return b;
    }

    /**
     *  POST /albums : create one album
     * 
     * @param id
     * @return
     */
    @POST
    public JsonResponse createOne(JsonAlbum album) {
    	Album b = new Album();
    	b.setTitle(album.getTitle());
    	return new JsonSimpleResponse();
    }


    /**
     *  PUT /albums/{id} : update one album
     * 
     * @param id
     * @return
     */
    @PUT
    @Path(value = "/{id}")
    public Album updateOne(@PathParam(value = "id") Integer id, JsonAlbum album) {
    	Album b = albumDao.getAlbum(id);
    	b.setTitle(album.getTitle());
    	LOGGER.info("find "+b.getTitle()+" album in the database to update");
    	return b;
    }
    
	
	
}
