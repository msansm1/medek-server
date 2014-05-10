package bzh.medek.server.rest;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import bzh.medek.server.utils.TestConstants;
import bzh.medek.server.utils.TestUtils;


/**
 * Test class for ping REST service.
 * 
 * @author rbarbot
 *
 */
@RunWith(Arquillian.class)
@RunAsClient
public class PingServiceTest {
		 
    private static final Logger LOGGER = Logger.getLogger(PingServiceTest.class);

    private static final String APP_NAME = "pingservice";
 
    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        // creation of the war for testing
        final WebArchive war = TestUtils.getWarFile(APP_NAME);
        LOGGER.info(war.toString(Formatters.VERBOSE));
        return war;
    }
 
    /**
     * Test for ping
     * 
     * @throws Exception
     */
    @Test
    public void callPingSvc() throws Exception {
    	Client client = ClientBuilder.newClient();

    	Response response = client.target(TestConstants.SERVER_ROOT+APP_NAME+"/rest/server/ping")
    	                   .queryParam("arquillian_testing", "true")
    	                   .request(MediaType.APPLICATION_JSON).get(); 
    	
        assertEquals(200, response.getStatus());
    }
 
}
