package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.friend.JsonFriend;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for friend REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
//Run the tests of the class as a client
@RunAsClient
public class FriendServiceTest {
    private static final Logger LOGGER = Logger.getLogger(FriendServiceTest.class);

    private static final String APP_NAME = "friendservice";

    private static final String svc_root = "/services/friends";

    // testable = false => it's for testing as a client (we don't test inside
    // the app)
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        // creation of the war for testing
        final WebArchive war = TestUtils.getWarFile(APP_NAME);
        LOGGER.info(war.toString(Formatters.VERBOSE));
        return war;
    }

    /**
     * Test for /services/friends/{userId} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
		List<JsonFriend> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root 
				+ "/1")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No friend found", response.isEmpty());
    }

    /**
     * Test for /services/friends/{userId}/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonFriend response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/2")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonFriend.class);
        assertEquals("", response.getFriendLogin());
    }

    /**
     * Test for /services/friends/{userId}/{id} POST Test OK
     * creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonFriend friend = new JsonFriend(1, 3, "", false, false);

        JsonFriend response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/2")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(friend, MediaType.APPLICATION_JSON), JsonFriend.class);
        assertEquals(false, response.getSharedCollection());
    }

    /**
     * Test for /services/friends/{userId}/{id} POST Test OK
     * update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonFriend friend = new JsonFriend(1, 3, "", false, true);

        JsonFriend response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/2")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(friend, MediaType.APPLICATION_JSON), JsonFriend.class);
        assertEquals(true, response.getSharedCollection());
    }

    /**
     * Test for /services/friends/{userId}/{id}/delete GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(5)
    public void callDelete() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonSimpleResponse response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root
        		+ "/1/3/delete")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonSimpleResponse.class);
        
        assertTrue("Delete failed", response!=null);
        
        Response friend = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/2")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get();        
        assertEquals("Must be 404", 404, friend.getStatus());
    }

}
