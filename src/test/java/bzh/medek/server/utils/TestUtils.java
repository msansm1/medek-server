package bzh.medek.server.utils;

import java.io.File;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import bzh.medek.server.conf.ClientMessage;
import bzh.medek.server.error.MedekError;
import bzh.medek.server.gracenote.GracenoteException;
import bzh.medek.server.interceptor.RestRequestFilter;
import bzh.medek.server.json.JsonClientMsg;
import bzh.medek.server.json.admin.JsonConfParam;
import bzh.medek.server.json.admin.user.JsonAdminUser;
import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.artist.JsonArtist;
import bzh.medek.server.json.auth.JsonLogin;
import bzh.medek.server.json.book.JsonBook;
import bzh.medek.server.json.home.JsonCollectionStats;
import bzh.medek.server.json.movie.JsonMovie;
import bzh.medek.server.json.tvshow.JsonShow;
import bzh.medek.server.json.user.JsonUser;
import bzh.medek.server.persistence.dao.UserDAO;
import bzh.medek.server.persistence.entities.Album;
import bzh.medek.server.rest.BookService;
import bzh.medek.server.rest.admin.AdminUserService;


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
                .addPackage(BookService.class.getPackage())
                .addPackage(JsonClientMsg.class.getPackage())
                .addPackage(ClientMessage.class.getPackage())
                .addPackage(JsonAlbum.class.getPackage())
                .addPackage(JsonBook.class.getPackage())
                .addPackage(JsonMovie.class.getPackage())
                .addPackage(JsonShow.class.getPackage())
                .addPackage(JsonUser.class.getPackage())
                .addPackage(MedekError.class.getPackage())
                .addPackage(Constants.class.getPackage())
                .addPackage(RestRequestFilter.class.getPackage())
                .addPackage(JsonLogin.class.getPackage())
                .addPackage(JsonArtist.class.getPackage())
                .addPackage(JsonAdminUser.class.getPackage())
                .addPackage(AdminUserService.class.getPackage())
                .addPackage(GracenoteException.class.getPackage())
                .addPackage(JsonCollectionStats.class.getPackage())
                .addPackage(JsonConfParam.class.getPackage())
                .addAsLibraries(libsConf)
                .addAsResource("create.sql", "create.sql")
                .addAsResource("load.sql", "load.sql")
                .addAsResource("drop.sql", "drop.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }
}
