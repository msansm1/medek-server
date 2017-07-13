package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.logging.Logger;
import org.jboss.resteasy.plugins.providers.jackson.ResteasyJackson2Provider;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import bzh.medek.server.json.user.JsonUser;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for user REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
// Run the tests of the class as a client
@RunAsClient
public class UserServiceTest {
    private static final Logger LOGGER = Logger.getLogger(UserServiceTest.class);

    private static final String APP_NAME = "userservice";

    private static final String svc_root = "/services/users";

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
     * Test for /services/users GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonUser> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No user found", response.isEmpty());
    }

    /**
     * Test for /services/users/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonUser response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonUser.class);
        assertEquals("msansm1", response.getLogin());
    }

    /**
     * Test for /services/users POST Test OK creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonUser user = new JsonUser(null, "testrest", "test@rest.bzh");

        JsonUser response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), JsonUser.class);
        assertEquals("testrest", response.getLogin());
    }

    /**
     * Test for /services/users POST Test OK update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonUser user = new JsonUser(1, "msansm1", "test@test.bzh");

        JsonUser response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(user, MediaType.APPLICATION_JSON), JsonUser.class);
        assertEquals("msansm1", response.getLogin());
    }

}
