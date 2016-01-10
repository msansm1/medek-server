package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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

import bzh.medek.server.json.JsonSimpleResponse;
import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.book.JsonBook;
import bzh.medek.server.json.book.JsonMyBook;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for book REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
//Run the tests of the class as a client
@RunAsClient
public class BookServiceTest {
    private static final Logger LOGGER = Logger.getLogger(BookServiceTest.class);

    private static final String APP_NAME = "bookservice";

    private static final String svc_root = "/services/books";

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
     * Test for /services/books GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
		List<JsonBook> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No book found", response.isEmpty());
    }

    /**
     * Test for /services/books/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonBook response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1/loguser/1")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonBook.class);
        assertEquals("The Hobbit", response.getTitle());
    }

    /**
     * Test for /services/books POST Test OK
     * creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonBook book = new JsonBook(null, "Rainbow Six", "Tom Clancy", "Poche");

        JsonBook response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON), JsonBook.class);
        assertEquals("Rainbow Six", response.getTitle());
    }

    /**
     * Test for /services/books POST Test OK
     * update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonBook book = new JsonBook(1, "Silmarillion", null, null);

        JsonBook response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON), JsonBook.class);
        assertEquals("Silmarillion", response.getTitle());
    }

    /**
     * Test for /services/books/user/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(5)
    public void callGetUserBooks() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
		List<JsonBook> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/user/1")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No user book found", response.isEmpty());
    }

    /**
     * Test for /services/books GET with params
     * Page 1
     * 
     * @throws Exception
     */
    @Test
    @InSequence(6)
    public void callGetListParamsPOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
		List<JsonBook> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root 
				+ "?from=0&limit=5&orderBy=id&orderDir=asc")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No book found", response.isEmpty());
        assertEquals("List page 1 does not contains 5 entries", Integer.valueOf(5), Integer.valueOf(response.size()));
    }

    /**
     * Test for /services/books GET with params
     * Page 2
     * 
     * @throws Exception
     */
    @Test
    @InSequence(7)
    public void callGetListParamsPTwo() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
		List<JsonBook> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root 
				+ "?from=6&limit=5&orderBy=id&orderDir=asc")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No book found", response.isEmpty());
        assertEquals("List page 2 does not contains 2 entries", Integer.valueOf(2), Integer.valueOf(response.size()));
    }

    /**
     * Test for /services/books/addtocollec POST Test OK
     * update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(8)
    public void callAddToCollec() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonMyBook book = new JsonMyBook(2, 1, 4, "", false);


		@SuppressWarnings("unchecked")
		List<JsonAlbum> listbefore = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/user/1")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        int sizebefore = listbefore.size();
        
        JsonSimpleResponse response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/addtocollec")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(book, MediaType.APPLICATION_JSON), JsonSimpleResponse.class);
        assertEquals("true", response.getOk());

		@SuppressWarnings("unchecked")
		List<JsonAlbum> listafter = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/user/1")
                .request(MediaType.APPLICATION_JSON)
                .header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        int sizeafter = listafter.size();
        assertTrue("not added correctly : "+sizebefore+" | "+sizeafter, (sizebefore+1)==sizeafter);
    }

	
}
