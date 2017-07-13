package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

import bzh.medek.server.json.auth.JsonAuth;
import bzh.medek.server.json.auth.JsonLogin;
import bzh.medek.server.utils.Constants;
import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;

/**
 * Test class for auth REST service
 * 
 * @author msansm1
 *
 */
@RunWith(Arquillian.class)
// Run the tests of the class as a client
@RunAsClient
public class AuthServiceTest {
    private static final Logger LOGGER = Logger.getLogger(AuthServiceTest.class);

    private static final String APP_NAME = "authservice";

    private static final String svc_root = "/services/auth";

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
     * Test for /services/auth/login Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(1)
    public void callLoginOK() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonLogin login = new JsonLogin("msansm1", "m1pwd");

        JsonAuth response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/login")
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(login, MediaType.APPLICATION_JSON), JsonAuth.class);
        assertEquals(Integer.valueOf(1), response.getId());
    }

    /**
     * Test for /services/auth/login Test NOK => 401 wrong password
     * 
     * @throws Exception
     */
    @Test
    @InSequence(2)
    public void callLogin401() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonLogin login = new JsonLogin("msansm1", "tototest");

        Response response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/login")
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(login, MediaType.APPLICATION_JSON));
        assertEquals(401, response.getStatus());
    }

    /**
     * Test for /services/auth/login Test NOK => 401 wrong login
     * 
     * @throws Exception
     */
    @Test
    @InSequence(3)
    public void callLogin401LoginWrong() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonLogin login = new JsonLogin("myop", "tototest");

        Response response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/login")
                .request(MediaType.APPLICATION_JSON).post(Entity.entity(login, MediaType.APPLICATION_JSON));
        assertEquals(401, response.getStatus());
    }

    /**
     * Test for /services/auth/logout Test OK
     * 
     * @throws Exception
     */
    @Test
    @InSequence(4)
    public void callLogout() throws Exception {
        Client client = ClientBuilder.newClient().register(ResteasyJackson2Provider.class);
        JsonAuth login = new JsonAuth(2, "testarq", "", "", "", "", "");

        Response response = client.target(TestConstants.SERVER_ROOT + APP_NAME + svc_root + "/logout")
                .request(MediaType.APPLICATION_JSON).header(Constants.HTTP_HEADER_TOKEN, TestConstants.USER_TOKEN)
                .post(Entity.entity(login, MediaType.APPLICATION_JSON));
        assertEquals(200, response.getStatus());
    }

}
