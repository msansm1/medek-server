package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.album.JsonMyAlbum;
import bzh.medek.server.json.album.JsonTrack;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for album REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
// Run the tests of the class as a client
@RunAsClient
public class AlbumServiceTest {
    private static final Logger LOGGER = Logger.getLogger(AlbumServiceTest.class);

    private static final String APP_NAME = "albumservice";

    private static final String svc_root = "/services/albums";

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
     * Test for /services/albums GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonAlbum> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/loguser/1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No album found", response.isEmpty());
    }

    /**
     * Test for /services/albums/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonAlbum response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/loguser/1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonAlbum.class);
        assertEquals("And justice for all", response.getTitle());
    }

    /**
     * Test for /services/albums POST Test OK creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonAlbum album = new JsonAlbum(null, "la ouache", null, null, "BZH", 1, 12, "CD", 1, "Matmatah", 1, true, 4,
                false, null);

        JsonAlbum response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(album, MediaType.APPLICATION_JSON), JsonAlbum.class);
        assertEquals("la ouache", response.getTitle());
    }

    /**
     * Test for /services/albums POST Test OK update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonAlbum album = new JsonAlbum(1, "Master of puppets", null, null, "Metal", 1, 9, "CD", 1, "Metallica", 1,
                true, 5, false, null);

        JsonAlbum response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(album, MediaType.APPLICATION_JSON), JsonAlbum.class);
        assertEquals("Master of puppets", response.getTitle());
    }

    /**
     * Test for /services/albums/user GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(5)
    public void callGetUserAlbums() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonAlbum> response = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root
                        + "/user?from=0&limit=5&orderBy=a.id&orderDir=asc&userId=1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No album found", response.isEmpty());
        assertEquals("List page 1 does not contains 1 entrie", Integer.valueOf(1), Integer.valueOf(response.size()));
    }

    /**
     * Test for /services/albums/{id}/tracks GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(6)
    public void callGetAlbumTracks() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonTrack> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/tracks")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No user album found", response.isEmpty());
    }

    /**
     * Test for /services/albums GET with params Page 1
     * 
     * @throws Exception
     */
    @Test
    @InSequence(7)
    public void callGetListParamsPOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonAlbum> response = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "?from=0&limit=5&orderBy=id&orderDir=asc")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No album found", response.isEmpty());
        assertEquals("List page 1 does not contains 5 entries", Integer.valueOf(5), Integer.valueOf(response.size()));
    }

    /**
     * Test for /services/albums GET with params Page 2
     * 
     * @throws Exception
     */
    @Test
    @InSequence(8)
    public void callGetListParamsPTwo() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonAlbum> response = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "?from=6&limit=5&orderBy=id&orderDir=asc")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No album found", response.isEmpty());
        assertEquals("List page 2 does not contains 2 entries", Integer.valueOf(2), Integer.valueOf(response.size()));
    }

    /**
     * Test for /services/albums/addtocollec POST Test OK update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(9)
    public void callAddToCollec() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonMyAlbum album = new JsonMyAlbum(2, 1, 4, "", false);

        @SuppressWarnings("unchecked")
        List<JsonAlbum> listbefore = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root
                        + "/user?from=0&limit=5&orderBy=a.id&orderDir=asc&userId=1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        int sizebefore = listbefore.size();

        JsonSimpleResponse response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/addtocollec")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(album, MediaType.APPLICATION_JSON), JsonSimpleResponse.class);
        assertEquals("true", response.getOk());

        @SuppressWarnings("unchecked")
        List<JsonAlbum> listafter = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root
                        + "/user?from=0&limit=5&orderBy=a.id&orderDir=asc&userId=1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        int sizeafter = listafter.size();
        assertTrue("not added correctly : " + sizebefore + " | " + sizeafter, (sizebefore + 1) == sizeafter);
    }

}
