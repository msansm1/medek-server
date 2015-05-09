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

import bzh.medek.server.json.artist.JsonArtist;
import bzh.medek.server.persistence.dao.ArtistDAO;
import bzh.medek.server.persistence.dao.ArtisttypeDAO;
import bzh.medek.server.persistence.entities.Artist;

@Stateless
@ApplicationPath("/services")
@Path(value = "/artists")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArtistService extends Application {

    private static final Logger LOGGER = Logger.getLogger(ArtistService.class);
    
    @Inject
    ArtistDAO artistDao;
    @Inject
    ArtisttypeDAO artisttypeDAO;
	
	public ArtistService () {
	}

    /**
     *  GET /artists : retrieve all artists
     * 
     * @return
     */
    @GET
    public List<JsonArtist> getAll() {
    	List<Artist> artists = artistDao.getArtists();
    	LOGGER.info("find "+artists.size()+" artists in the database");
    	ArrayList<JsonArtist> la = new ArrayList<JsonArtist>();
    	for (Artist a:artists) {
    		la.add(new JsonArtist(a.getId(), a.getName(), a.getFirstname(), 
    				a.getArtisttype().getName(), a.getArtisttype().getId(), 
    				a.getNationality(), a.getBiolink()));
    	}
    	return la;
    }

    /**
     *  GET /artists/{id} : retrieve one artist
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonArtist getOne(@PathParam(value = "id") Integer id) {
    	Artist a = artistDao.getArtist(id);
    	LOGGER.info("find "+a.getName()+" artist in the database");
    	return new JsonArtist(a.getId(), a.getName(), a.getFirstname(), 
				a.getArtisttype().getName(), a.getArtisttype().getId(), 
				a.getNationality(), a.getBiolink());
    }

    /**
     *  POST /artists : create / update one artist
     * 
     * @param JsonArtist artist
     * @return
     */
    @POST
    public JsonArtist createUpdateOne(JsonArtist artist) {
    	JsonArtist jartist = artist;
    	if (artist.getId() == null) {
	    	Artist a = new Artist();
	    	a.setName(artist.getName());
	    	a.setFirstname(artist.getFirstname());
	    	a.setBiolink(artist.getBiolink());
	    	a.setNationality(artist.getNationality());
	    	if (artist.getTypeId() != null) {
	    		a.setArtisttype(artisttypeDAO.getArtisttype(artist.getTypeId()));
	    	}
	    	artistDao.saveArtist(a);
	    	jartist.setId(a.getId());
    	} else {
        	Artist a = artistDao.getArtist(artist.getId());
	    	a.setName(artist.getName());
	    	a.setFirstname(artist.getFirstname());
	    	a.setBiolink(artist.getBiolink());
	    	a.setNationality(artist.getNationality());
	    	if (artist.getTypeId() != null) {
	    		a.setArtisttype(artisttypeDAO.getArtisttype(artist.getTypeId()));
	    	}
        	artistDao.updateArtist(a);
    	}
    	return jartist;
    }

    /**
     *  GET /artists : retrieve all album artists
     * 
     * @return
     */
    @GET
	@Path("/albums")
    public List<JsonArtist> getAllForAlbums() {
    	List<Artist> artists = artistDao.getArtistsForAlbum();
    	LOGGER.info("find "+artists.size()+" album artists in the database");
    	ArrayList<JsonArtist> la = new ArrayList<JsonArtist>();
    	for (Artist a:artists) {
    		la.add(new JsonArtist(a.getId(), a.getName(), a.getFirstname(), 
    				a.getArtisttype().getName(), a.getArtisttype().getId(), 
    				a.getNationality(), a.getBiolink()));
    	}
    	return la;
    }

    /**
     *  GET /books : retrieve all book artists
     * 
     * @return
     */
    @GET
	@Path("/books")
    public List<JsonArtist> getAllForBooks() {
    	List<Artist> artists = artistDao.getArtistsForBook();
    	LOGGER.info("find "+artists.size()+" book artists in the database");
    	ArrayList<JsonArtist> la = new ArrayList<JsonArtist>();
    	for (Artist a:artists) {
    		la.add(new JsonArtist(a.getId(), a.getName(), a.getFirstname(), 
    				a.getArtisttype().getName(), a.getArtisttype().getId(), 
    				a.getNationality(), a.getBiolink()));
    	}
    	return la;
    }

    /**
     *  GET /movies : retrieve all movie artists
     * 
     * @return
     */
    @GET
	@Path("/movies")
    public List<JsonArtist> getAllForMovies() {
    	List<Artist> artists = artistDao.getArtistsForMovie();
    	LOGGER.info("find "+artists.size()+" movi artists in the database");
    	ArrayList<JsonArtist> la = new ArrayList<JsonArtist>();
    	for (Artist a:artists) {
    		la.add(new JsonArtist(a.getId(), a.getName(), a.getFirstname(), 
    				a.getArtisttype().getName(), a.getArtisttype().getId(), 
    				a.getNationality(), a.getBiolink()));
    	}
    	return la;
    }

    /**
     *  GET /series : retrieve all book artists
     * 
     * @return
     */
    @GET
	@Path("/series")
    public List<JsonArtist> getAllForSeries() {
    	List<Artist> artists = artistDao.getArtistsForSeries();
    	LOGGER.info("find "+artists.size()+" series artists in the database");
    	ArrayList<JsonArtist> la = new ArrayList<JsonArtist>();
    	for (Artist a:artists) {
    		la.add(new JsonArtist(a.getId(), a.getName(), a.getFirstname(), 
    				a.getArtisttype().getName(), a.getArtisttype().getId(), 
    				a.getNationality(), a.getBiolink()));
    	}
    	return la;
    }
	
}
