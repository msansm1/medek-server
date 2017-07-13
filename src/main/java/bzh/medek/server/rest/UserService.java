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

import bzh.medek.server.json.auth.JsonAuth;
import bzh.medek.server.json.user.JsonUser;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.entities.User;
import bzh.medek.server.utils.Crypt;

@Stateless
@ApplicationPath("/services")
@Path(value = "/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserService extends Application {

    private static final Logger LOGGER = Logger.getLogger(UserService.class);

    @Inject
    UserDAO userDao;

    public UserService() {
    }

    /**
     * GET /users : retrieve all users
     * 
     * @return
     */
    @GET
    public List<JsonUser> getAll() {
        List<User> users = userDao.getUsers();
        LOGGER.info("find " + users.size() + " users in the database");
        ArrayList<JsonUser> lu = new ArrayList<JsonUser>();
        for (User u : users) {
            lu.add(new JsonUser(u.getId(), u.getLogin(), u.getEmail()));
        }
        return lu;
    }

    /**
     * GET /users/{id} : retrieve one user
     * 
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{id}")
    public JsonUser getOne(@PathParam(value = "id") Integer id) {
        User u = userDao.getUser(id);
        LOGGER.info("find " + u.getLogin() + " user in the database");
        return new JsonUser(u.getId(), u.getLogin(), u.getEmail());
    }

    /**
     * POST /users : create / update one user
     * 
     * @param JsonUser
     *            user
     * @return
     */
    @POST
    public JsonUser createUpdateOne(JsonUser user) {
        JsonUser juser = user;
        if (user.getId() == null) {
            User u = new User();
            u.setEmail(user.getMail());
            u.setLogin(user.getLogin());
            u.setPassword(Crypt.crypt(user.getLogin(), "password"));
            userDao.saveUser(u);
            juser.setId(u.getId());
        } else {
            User u = userDao.getUser(user.getId());
            u.setEmail(user.getMail());
            u.setLogin(user.getLogin());
            userDao.updateUser(u);
        }
        return juser;
    }

    /**
     * POST /users/profile : update logged user
     * 
     * @param JsonAuth
     *            user
     * @return
     */
    @POST
    @Path(value = "/profile")
    public JsonAuth updateLogged(JsonAuth user) {
        JsonAuth juser = user;
        User u = userDao.getUser(user.getId());
        u.setEmail(user.getEmail());
        if (Crypt.crypt(juser.getLogin(), juser.getOldpassword()).equals(u.getPassword())) {
            u.setPassword(Crypt.crypt(user.getLogin(), user.getNewpassword()));
        }
        userDao.updateUser(u);
        return juser;
    }

}
