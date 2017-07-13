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

import bzh.medek.server.json.JsonSupport;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for support REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
// Run the tests of the class as a client
@RunAsClient
public class SupportServiceTest {
    private static final Logger LOGGER = Logger.getLogger(SupportServiceTest.class);

    private static final String APP_NAME = "supportservice";

    private static final String svc_root = "/services/supports";

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
     * Test for /services/supports GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonSupport> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No support found", response.isEmpty());
    }

    /**
     * Test for /services/supports/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonSupport response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonSupport.class);
        assertEquals("DVD", response.getName());
    }

    /**
     * Test for /services/supports POST Test OK creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonSupport support = new JsonSupport(null, "testrest");

        JsonSupport response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(support, MediaType.APPLICATION_JSON), JsonSupport.class);
        assertEquals("testrest", response.getName());
    }

    /**
     * Test for /services/supports POST Test OK update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonSupport support = new JsonSupport(1, "msansm1");

        JsonSupport response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(support, MediaType.APPLICATION_JSON), JsonSupport.class);
        assertEquals("msansm1", response.getName());
    }

    /**
     * Test for /services/supports POST Test OK delete
     * 
     * @throws Exception
     */
    @Test
    @InSequence(5)
    public void callDelete() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonSupport> listbefore = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);

        JsonSupport support = new JsonSupport(2, Constants.DELETED);
        JsonSupport response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(support, MediaType.APPLICATION_JSON), JsonSupport.class);
        assertEquals(Constants.DELETED, response.getName());

        @SuppressWarnings("unchecked")
        List<JsonSupport> listafter = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);

        assertEquals(listbefore.size() - 1, listafter.size());
    }

}
