package bzh.medek.server.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import bzh.medek.server.json.JsonResponse;
import bzh.medek.server.json.ping.JsonPingResp;

@Stateless
@ApplicationPath("/services")
@Path(value = "/ping")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PingService extends Application {

    private static final Logger LOGGER = Logger.getLogger(PingService.class);

    public PingService() {
    }

    /**
     *  /ping
     * 
     * @param servletContext
     * @return
     */
    @GET
    public JsonResponse ping(@Context ServletContext servletContext) {
        String serverRev = "";
        String appVersion = "";
        InputStreamReader read = null;
        InputStream input = null;
        BufferedReader reader = null;
        try {
            input = servletContext.getResourceAsStream("/META-INF/MANIFEST.MF");
            // server
            read = new InputStreamReader(input);
            reader = new BufferedReader(read);
            String line = reader.readLine();
            while (line != null) {
                if (line.contains("Implementation-Build: ")) {
                    serverRev = line.substring(line.indexOf(' ') + 1);
                }
                if (line.contains("Implementation-Version: ")) {
                    appVersion = line.substring(line.indexOf(' ') + 1);
                }
                line = reader.readLine();
            }
            reader.close();
            read.close();
            input.close();
        } catch (IOException e) {
            LOGGER.error("while getting info from manifest:", e);
        } catch (NullPointerException e) {
            LOGGER.error("while trying to read manifest:", e);
            return new JsonPingResp("dev", "test");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOGGER.error("while getting manifest file (closing bufferedreader) :", e);
                }
            }
            if (read != null) {
                try {
                    read.close();
                } catch (IOException e) {
                    LOGGER.error("while getting manifest file (closing inputstreamreader) :", e);
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOGGER.error("while getting manifest file (closing inputstream) :", e);
                }
            }
        }

        return new JsonPingResp(serverRev, appVersion);
    }

}
