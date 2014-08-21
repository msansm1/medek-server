package bzh.medek.server.persistence.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import bzh.medek.server.persistence.entities.Artist;

/**
 * Tests for artist DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class ArtistDAOTest {
    private static final Logger LOGGER = Logger.getLogger(ArtistDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "artistdao.war")
        		.addClass(ArtistDAO.class)
                .addClass(Dao.class)
                .addPackage(Artist.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private ArtistDAO dao;

    final Artist artist = new Artist();

    public void saveArtistTest() {
    	artist.setFirstname("toto");
        artist.setName("tata");
        dao.saveArtist(artist);
        Assert.assertNotNull("Artist is not created", artist.getId());
    }

    public void getArtistTest() {
        Integer id = artist.getId();
        Artist created = dao.getArtist(id);
        Assert.assertNotNull("Artist is not found", created);
    }

    public void updateArtistTest() {
    	Artist updated = dao.getArtist(artist.getId());
    	updated.setName("newtiti");
        dao.updateArtist(updated);
        Assert.assertTrue("Artist is not updated",
                dao.getArtist(artist.getId()).getName().equalsIgnoreCase("newtiti"));

    }

    public void removeArtistTest() {
        Integer id = artist.getId();
        Artist todel = dao.getArtist(id);
        dao.removeArtist(todel);
        Assert.assertNotNull("Artist is not removed", todel);
        Assert.assertNull("Artist is not removed(get request)", dao.getArtist(id));
    }

    @Test
    public void runTestsInOrder() {
        saveArtistTest();
        getArtistTest();
        updateArtistTest();
        removeArtistTest();
    }

    @Test
    public void getAllArtistsTest() {
        List<Artist> l = dao.getArtists();
        Assert.assertNotNull("No Artist found", l);
    }
    
}
