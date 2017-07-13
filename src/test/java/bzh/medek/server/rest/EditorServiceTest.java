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

import bzh.medek.server.json.book.JsonEditor;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for editor REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
// Run the tests of the class as a client
@RunAsClient
public class EditorServiceTest {
    private static final Logger LOGGER = Logger.getLogger(EditorServiceTest.class);

    private static final String APP_NAME = "editorservice";

    private static final String svc_root = "/services/editors";

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
     * Test for /services/editors GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callGetList() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonEditor> response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);
        assertFalse("No editor found", response.isEmpty());
    }

    /**
     * Test for /services/editors/{id} GET Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callGetOne() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        JsonEditor response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/1")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(JsonEditor.class);
        assertEquals("Dupuis", response.getName());
    }

    /**
     * Test for /services/editors POST Test OK creation
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callCreate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonEditor editor = new JsonEditor(null, "testrest");

        JsonEditor response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(editor, MediaType.APPLICATION_JSON), JsonEditor.class);
        assertEquals("testrest", response.getName());
    }

    /**
     * Test for /services/editors POST Test OK update
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callUpdate() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonEditor editor = new JsonEditor(1, "msansm1");

        JsonEditor response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(editor, MediaType.APPLICATION_JSON), JsonEditor.class);
        assertEquals("msansm1", response.getName());
    }

    /**
     * Test for /services/editors POST Test OK delete
     * 
     * @throws Exception
     */
    @Test
    @InSequence(5)
    public void callDelete() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);

        @SuppressWarnings("unchecked")
        List<JsonEditor> listbefore = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);

        JsonEditor editor = new JsonEditor(3, Constants.DELETED);
        JsonEditor response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(editor, MediaType.APPLICATION_JSON), JsonEditor.class);
        assertEquals(Constants.DELETED, response.getName());

        @SuppressWarnings("unchecked")
        List<JsonEditor> listafter = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root)
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .get(List.class);

        assertEquals(listbefore.size() - 1, listafter.size());
    }

}
