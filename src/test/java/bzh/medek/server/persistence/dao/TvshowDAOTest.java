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

import bzh.medek.server.json.tvshow.JsonShow;
import bzh.medek.server.persistence.entities.Tvshow;

/**
 * Tests for tvshow DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class TvshowDAOTest {
    private static final Logger LOGGER = Logger.getLogger(TvshowDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "tvshowdao.war")
        		.addClass(TvshowDAO.class)
        		.addClass(JsonShow.class)
                .addClass(Dao.class)
                .addPackage(Tvshow.class.getPackage())
                .addAsResource("drop.sql", "drop.sql")
                .addAsResource("create.sql", "create.sql")
                .addAsResource("load.sql", "load.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private TvshowDAO dao;

    final Tvshow tvshow = new Tvshow();

    public void saveTvshowTest() {
    	tvshow.setTitle("test tvshow");
        tvshow.setCover("YOUHOU");
        dao.saveTvshow(tvshow);
        Assert.assertNotNull("Tvshow is not created", tvshow.getId());
    }

    public void getTvshowTest() {
        Integer id = tvshow.getId();
        Tvshow created = dao.getTvshow(id);
        Assert.assertNotNull("Tvshow is not found", created);
    }

    public void updateTvshowTest() {
    	Tvshow updated = dao.getTvshow(tvshow.getId());
    	updated.setCover("changed :)");
        dao.updateTvshow(updated);
        Assert.assertTrue("Tvshow is not updated",
                dao.getTvshow(tvshow.getId()).getCover().equalsIgnoreCase("changed :)"));

    }

    public void removeTvshowTest() {
        Integer id = tvshow.getId();
        Tvshow todel = dao.getTvshow(id);
        dao.removeTvshow(todel);
        Assert.assertNotNull("Tvshow is not removed", todel);
        Assert.assertNull("Tvshow is not removed(get request)", dao.getTvshow(id));
    }

    @Test
    public void runTestsInOrder() {
        saveTvshowTest();
        getTvshowTest();
        updateTvshowTest();
        removeTvshowTest();
    }

    @Test
    public void getAllTvshowsTest() {
        List<Tvshow> l = dao.getTvshows();
        Assert.assertNotNull("No Tvshow found", l);
    }

    @Test
    public void getUserTvshowsTest() {
        List<JsonShow> l = dao.getUsersTvshows(1);
        Assert.assertNotNull("No Tvshow found", l);
    }
    
}
