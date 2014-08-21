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

import bzh.medek.server.persistence.entities.Track;

/**
 * Tests for track DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class TrackDAOTest {
    private static final Logger LOGGER = Logger.getLogger(TrackDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "trackdao.war")
        		.addClass(TrackDAO.class)
                .addClass(Dao.class)
                .addPackage(Track.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private TrackDAO dao;

    final Track track = new Track();

    public void saveTrackTest() {
    	track.setTitle("test track");
        track.setLength("2:30");
        dao.saveTrack(track);
        Assert.assertNotNull("Track is not created", track.getId());
    }

    public void getTrackTest() {
        Integer id = track.getId();
        Track created = dao.getTrack(id);
        Assert.assertNotNull("Track is not found", created);
    }

    public void updateTrackTest() {
    	Track updated = dao.getTrack(track.getId());
    	updated.setLength("10:10");
        dao.updateTrack(updated);
        Assert.assertTrue("Track is not updated",
                dao.getTrack(track.getId()).getLength().equalsIgnoreCase("10:10"));

    }

    public void removeTrackTest() {
        Integer id = track.getId();
        Track todel = dao.getTrack(id);
        dao.removeTrack(todel);
        Assert.assertNotNull("Track is not removed", todel);
        Assert.assertNull("Track is not removed(get request)", dao.getTrack(id));
    }

    @Test
    public void runTestsInOrder() {
        saveTrackTest();
        getTrackTest();
        updateTrackTest();
        removeTrackTest();
    }

    @Test
    public void getAllTracksTest() {
        List<Track> l = dao.getTracks();
        Assert.assertNotNull("No Track found", l);
    }
    
}
