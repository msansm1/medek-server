package bzh.medek.server.rest.admin;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import bzh.medek.server.json.admin.JsonConfParam;
import bzh.medek.server.persistence.dao.ConfigurationDAO;

@Stateless
@ApplicationPath("/services")
@Path(value = "/admin/conf")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConfigurationService extends Application {

    private static final Logger LOGGER = Logger.getLogger(ConfigurationService.class);

    @Inject
    ConfigurationDAO configurationDAO;

    public ConfigurationService() {
        // empty constructor
    }

    /**
     * GET /admin/config : retrieve all configuration parameters
     * 
     * @return
     */
    @GET
    public List<JsonConfParam> getAll() {
        return configurationDAO.getJsonConf();
    }

}
