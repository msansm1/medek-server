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

import org.jboss.logging.Logger;

import bzh.medek.server.json.artist.JsonArtisttype;
import bzh.medek.server.persistence.dao.ArtisttypeDAO;
import bzh.medek.server.persistence.entities.Artisttype;
import bzh.medek.server.utils.Constants;

@Stateless
@ApplicationPath("/services")
@Path(value = "/artisttypes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ArtistTypeService extends Application {

    private static final Logger LOGGER = Logger.getLogger(ArtistTypeService.class);

    @Inject
    ArtisttypeDAO artisttypeDao;

    public ArtistTypeService() {
    }

    /**
     * GET /artisttypes : retrieve all artisttypes
     * 
     * @return
     */
    @GET
    public List<JsonArtisttype> getAll() {
        List<Artisttype> artisttypes = artisttypeDao.getArtisttypes();
        LOGGER.info("find " + artisttypes.size() + " artisttypes in the database");
        ArrayList<JsonArtisttype> ll = new ArrayList<JsonArtisttype>();
        for (Artisttype l : artisttypes) {
            ll.add(new JsonArtisttype(l.getId(), l.getName()));
        }
        return ll;
    }

    /**
     * GET /artisttypes/{id} : retrieve one artisttype
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonArtisttype getOne(@PathParam(value = "id") Integer id) {
        Artisttype l = artisttypeDao.getArtisttype(id);
        LOGGER.info("find " + l.getName() + " artisttype in the database");
        return new JsonArtisttype(l.getId(), l.getName());
    }

    /**
     * POST /artisttypes : create / update one artisttype
     * 
     * @param JsonArtisttype
     *            artisttype
     * @return
     */
    @POST
    public JsonArtisttype createUpdateOne(JsonArtisttype artisttype) {
        JsonArtisttype jartisttype = artisttype;
        if (artisttype.getId() == null) {
            Artisttype l = new Artisttype();
            l.setName(artisttype.getName());
            artisttypeDao.saveArtisttype(l);
            jartisttype.setId(l.getId());
        } else {
            if (artisttype.getName().equalsIgnoreCase(Constants.DELETED)) {
                artisttypeDao.removeArtisttype(artisttypeDao.getArtisttype(artisttype.getId()));
            } else {
                Artisttype l = artisttypeDao.getArtisttype(artisttype.getId());
                l.setName(artisttype.getName());
                artisttypeDao.updateArtisttype(l);
            }
        }
        return jartisttype;
    }

}
