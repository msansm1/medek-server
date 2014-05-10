package bzh.medek.server.utils;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import bzh.medek.server.conf.ClientMessage;
import bzh.medek.server.error.MedekError;
import bzh.medek.server.json.JsonClientMsg;
import bzh.medek.server.json.ping.JsonPingResp;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.entities.Album;
import bzh.medek.server.rest.PingService;


public class TestUtils {

    public static synchronized WebArchive getWarFile(String warName) {
        // The library included via the maven resolver of Shrinkwrap
        File[] libsConf = Maven.resolver()  
                    .loadPomFromFile("pom.xml").resolve("commons-configuration:commons-configuration")  
                    .withTransitivity().asFile();
        
        // creation of the war for testing
        return ShrinkWrap.create(WebArchive.class, warName+".war")
                .addPackage(Album.class.getPackage())
                .addPackage(UserDAO.class.getPackage())
                .addPackage(PingService.class.getPackage())
                .addPackage(JsonClientMsg.class.getPackage())
                .addPackage(ClientMessage.class.getPackage())
                .addPackage(JsonPingResp.class.getPackage())
                .addPackage(MedekError.class.getPackage())
                .addAsLibraries(libsConf)
                .addAsResource("META-INF/persistence.xml")
                .addAsResource("gui_errors_en.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsWebInfResource(new File("src/main/webapp/WEB-INF/web.xml"));
    }
}
