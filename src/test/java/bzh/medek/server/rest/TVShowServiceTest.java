package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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

import bzh.medek.server.json.JsonLang;
import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.tvshow.JsonMyShow;
import bzh.medek.server.json.tvshow.JsonShow;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for tvshow REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
// Run the tests of the class as a client
@RunAsClient
public class TVShowServiceTest {
    private static final Logger LOGGER = Logger.getLogger(TVShowServiceTest.class);

    private static final String APP_NAME = "tvshowservice";

    private static final String svc_root = "/services/tvshows";

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
     * Test for /services/tvshows GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonShow> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No tvshow found", response.isEmpty());
    }

    /**
     * Test for /services/tvshows/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonShow response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/loguser/1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonShow.class);
        assertEquals("Justified s01", response.getTitle());
    }

    /**
     * Test for /services/tvshows POST Test OK creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonShow tvshow = new JsonShow(null, "Stargate SG1", "Mickael !!!!", null, "", "", 1, "", 1, "89", 3, "ui",
                true, new ArrayList<JsonLang>(), new ArrayList<JsonLang>(), false, 0);

        JsonShow response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(tvshow, MediaType.APPLICATION_JSON), JsonShow.class);
        assertEquals(Integer.valueOf(8), response.getId());
    }

    /**
     * Test for /services/tvshows POST Test OK update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonShow tvshow = new JsonShow(2, "Stargate SG1 bis", "Mickael !!!!  !!!!", null, "", "", 1, "", 1, "89", 3,
                "ui", true, new ArrayList<JsonLang>(), new ArrayList<JsonLang>(), false, 0);

        JsonShow response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(tvshow, MediaType.APPLICATION_JSON), JsonShow.class);
        assertEquals("Stargate SG1 bis", response.getTitle());
    }

    /**
     * Test for /services/tvshows/user/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(5)
    public void callGetUserTvshows() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonAlbum> response = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root
                        + "/user?from=0&limit=5&orderBy=t.id&orderDir=asc&userId=1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No tvshow found", response.isEmpty());
        assertEquals("List page 1 does not contains 1 entrie", Integer.valueOf(1), Integer.valueOf(response.size()));
    }

    /**
     * Test for /services/tvshows GET with params
     * 
     * @throws Exception
     */
    @Test
    @InSequence(6)
    public void callGetListParamsPOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonShow> response = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "?from=0&limit=5&orderBy=id&orderDir=asc")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No tvshow found", response.isEmpty());
        assertEquals("List page 1 does not contains 5 entries", Integer.valueOf(5), Integer.valueOf(response.size()));
    }

    /**
     * Test for /services/tvshows GET with params
     * 
     * @throws Exception
     */
    @Test
    @InSequence(7)
    public void callGetListParamsPTwo() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonShow> response = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "?from=6&limit=5&orderBy=id&orderDir=asc")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No tvshow found", response.isEmpty());
        assertEquals("List page 2 does not contains 2 entries", Integer.valueOf(2), Integer.valueOf(response.size()));
    }

    /**
     * Test for /services/tvshows/addtocollec POST Test OK update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(8)
    public void callAddToCollec() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonMyShow show = new JsonMyShow(2, 1, 4, "");

        @SuppressWarnings("unchecked")
        List<JsonShow> listbefore = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root
                        + "/user?from=0&limit=5&orderBy=t.id&orderDir=asc&userId=1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        int sizebefore = listbefore.size();

        JsonSimpleResponse response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/addtocollec")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(show, MediaType.APPLICATION_JSON), JsonSimpleResponse.class);
        assertEquals("true", response.getOk());

        @SuppressWarnings("unchecked")
        List<JsonShow> listafter = client
                .target(TestConstants.SERVER_ROOT + APP_NAME + svc_root
                        + "/user?from=0&limit=5&orderBy=t.id&orderDir=asc&userId=1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        int sizeafter = listafter.size();
        assertTrue("not added correctly : " + sizebefore + " | " + sizeafter, (sizebefore + 1) == sizeafter);
    }

}
