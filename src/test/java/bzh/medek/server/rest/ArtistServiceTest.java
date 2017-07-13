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

import bzh.medek.server.json.artist.JsonArtist;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for artist REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
// Run the tests of the class as a client
@RunAsClient
public class ArtistServiceTest {
    private static final Logger LOGGER = Logger.getLogger(ArtistServiceTest.class);

    private static final String APP_NAME = "artistservice";

    private static final String svc_root = "/services/artists";

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
     * Test for /services/artists GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonArtist> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No artist found", response.isEmpty());
    }

    /**
     * Test for /services/artists/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonArtist response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonArtist.class);
        assertEquals("Metallica", response.getName());
    }

    /**
     * Test for /services/artists POST Test OK creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonArtist artist = new JsonArtist(null, "testrest", "", "", 1, "", "");

        JsonArtist response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(artist, MediaType.APPLICATION_JSON), JsonArtist.class);
        assertEquals("testrest", response.getName());
    }

    /**
     * Test for /services/artists POST Test OK update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonArtist artist = new JsonArtist(4, "msansm1", "", "", 1, "", "");

        JsonArtist response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(artist, MediaType.APPLICATION_JSON), JsonArtist.class);
        assertEquals("msansm1", response.getName());
    }

    /**
     * Test for /services/artists/albums GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(5)
    public void callGetListForAlbums() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonArtist> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/albums")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No artist found", response.isEmpty());
    }

    /**
     * Test for /services/artists/books GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(6)
    public void callGetListForBooks() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonArtist> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/books")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No artist found", response.isEmpty());
    }

    /**
     * Test for /services/artists/movies GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(7)
    public void callGetListForMovies() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonArtist> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/movies")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No artist found", response.isEmpty());
    }

    /**
     * Test for /services/artists/movies GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(8)
    public void callGetListForSeries() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonArtist> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/series")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No artist found", response.isEmpty());
    }

}
