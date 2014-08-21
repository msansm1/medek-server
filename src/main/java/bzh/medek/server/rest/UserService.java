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
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.entities.User;

@Stateless
@ApplicationPath("/services")
@Path(value = "/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService extends Application {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    
    @Inject
    UserDAO userDao;
	
	public UserService () {
	}

    /**
     *  GET /users : retrieve all users
     * 
     * @return
     */
    @GET
    public List<User> getAll() {
    	List<User> u = userDao.getUsers();
    	LOGGER.info("find "+u.size()+" users in the database");
    	return u;
    }

    /**
     *  GET /users/{id} : retrieve one user
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public User getOne(@PathParam(value = "id") Integer id) {
    	User u = userDao.getUser(id);
    	LOGGER.info("find "+u.getLogin()+" user in the database");
    	return u;
    }

    /**
     *  POST /users : create one user
     * 
     * @param id
     * @return
     */
    @POST
    public JsonResponse createOne() {
    	return new JsonSimpleResponse();
    }


    /**
     *  PUT /users/{id} : update one user
     * 
     * @param id
     * @return
     */
    @PUT
    @Path(value = "/{id}")
    public User updateOne(@PathParam(value = "id") Integer id) {
    	User u = userDao.getUser(id);
    	LOGGER.info("find "+u.getLogin()+" user in the database to update");
    	return u;
    }
    
	
	
}
