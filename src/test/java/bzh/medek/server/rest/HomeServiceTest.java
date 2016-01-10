package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

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

import bzh.medek.server.json.home.JsonCollectionStats;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for home REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
//Run the tests of the class as a client
@RunAsClient
public class HomeServiceTest {
    private static final Logger LOGGER = Logger.getLogger(HomeServiceTest.class);

    private static final String APP_NAME = "homeservice";

    private static final String svc_root = "/services/home";

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
     * Test for /services/home/mycollec Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callMyCollec() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonCollectionStats response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/mycollec")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonCollectionStats.class);
        assertNotNull(response.getAlbums());
//        assertEquals(Integer.valueOf(1), response.getAlbums().getNb());
//        assertEquals(Integer.valueOf(1), response.getBooks().getNb());
//        assertEquals(Integer.valueOf(1), response.getMovies().getNb());
//        assertEquals(Integer.valueOf(1), response.getSeries().getNb());
    }
	
}
