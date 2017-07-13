package bzh.medek.server.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.friend.JsonFriend;
import bzh.medek.server.persistence.dao.FriendDAO;
import bzh.medek.server.persistence.entities.Friend;
import bzh.medek.server.persistence.entities.FriendPK;

@Stateless
@ApplicationPath("/services")
@Path(value = "/friends")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FriendService extends Application {

    private static final Logger LOGGER = Logger.getLogger(FriendService.class);

    @Inject
    FriendDAO friendDAO;

    public FriendService() {
    }

    /**
     * GET /friends/{userId} : retrieve all friends for an user
     * 
     * @param userId
     * @return
     */
    @GET
    @Path(value = "/{userId}")
    public List<JsonFriend> getAllWithParams(@Context HttpServletRequest request,
            @PathParam(value = "userId") Integer userId, @QueryParam("from") int from, @QueryParam("limit") int limit,
            @QueryParam("orderBy") String orderBy, @QueryParam("orderDir") String orderDir) {
        return friendDAO.getFriendsListForUser(userId, from, limit, orderBy, orderDir);
    }

    /**
     * GET /friends/{userId}/{id} : retrieve one friend for one user
     * 
     * @param userId
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{userId}/{id}")
    public Response getOne(@PathParam(value = "userId") Integer userId, @PathParam(value = "id") Integer id) {
        JsonFriend jf = friendDAO.getFriendForUser(userId, id);
        if (jf == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No friend found").build();
        }
        return Response.status(Response.Status.OK).entity(jf).build();
    }

    /**
     * POST /friends/{userId}/{id} : ask / accept friend
     * 
     * @param userId
     * @param id
     * @return
     */
    @POST
    @Path(value = "/{userId}/{id}")
    public JsonFriend createUpdate(@PathParam(value = "userId") Integer userId, @PathParam(value = "id") Integer id,
            JsonFriend friend) {
        JsonFriend jf = friend;
        FriendPK fpk = new FriendPK();
        fpk.setFriend(jf.getFriendId());
        fpk.setUser(jf.getUserId());
        Friend f = friendDAO.getFriend(fpk);
        if (f == null) {
            f = new Friend();
            f.setId(fpk);
            f.setIsaccepted(jf.getAccepted());
            f.setIssharedcollection(jf.getSharedCollection());
            friendDAO.saveFriend(f);
        } else {
            f.setIsaccepted(jf.getAccepted());
            f.setIssharedcollection(jf.getSharedCollection());
            friendDAO.updateFriend(f);
        }
        return jf;
    }

    /**
     * GET /friends/{userId}/{id}/delete : delete a friend
     * 
     * @param userId
     * @param id
     * @return
     */
    @GET
    @Path(value = "/{userId}/{id}/delete")
    public JsonSimpleResponse delete(@PathParam(value = "userId") Integer userId, @PathParam(value = "id") Integer id) {
        FriendPK fpk = new FriendPK();
        fpk.setFriend(id);
        fpk.setUser(userId);
        friendDAO.removeFriend(friendDAO.getFriend(fpk));
        return new JsonSimpleResponse(true);
    }

}
