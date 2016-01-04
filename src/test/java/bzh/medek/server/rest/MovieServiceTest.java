package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
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

import bzh.medek.server.json.movie.JsonMovie;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for movie REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
//Run the tests of the class as a client
@RunAsClient
public class MovieServiceTest {
    private static final Logger LOGGER = Logger.getLogger(MovieServiceTest.class);

    private static final String APP_NAME = "movieservice";

    private static final String svc_root = "/services/movies";

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
     * Test for /services/movies GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
		List<JsonMovie> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No movie found", response.isEmpty());
    }

    /**
     * Test for /services/movies/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonMovie response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/loguser/1")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonMovie.class);
        assertEquals("Princesse Mononoké", response.getTitle());
    }

    /**
     * Test for /services/movies POST Test OK
     * creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonMovie movie = new JsonMovie(null, "Apocalypse now", "", new Date(), "", "", 1, "", 1, "", false);
        		
        JsonMovie response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(movie, MediaType.APPLICATION_JSON), JsonMovie.class);
        assertEquals("Apocalypse now", response.getTitle());
    }

    /**
     * Test for /services/movies POST Test OK
     * update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonMovie movie = new JsonMovie(1, "Totorro", "", new Date(), "", "", 1, "", 1, "", false);

        JsonMovie response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(movie, MediaType.APPLICATION_JSON), JsonMovie.class);
        assertEquals("Totorro", response.getTitle());
    }

    /**
     * Test for /services/movies/user/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(5)
    public void callGetUserMovies() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
		List<JsonMovie> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/user/1")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No user movie found", response.isEmpty());
    }

    /**
     * Test for /services/movies GET with params
     * 
     * @throws Exception
     */
    @Test
    @InSequence(6)
    public void callGetListParams() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
		List<JsonMovie> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root 
				+ "?from=0&limit=5&orderBy=id&orderDir=asc")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No movie found", response.isEmpty());
    }

	
}
