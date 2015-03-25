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

import bzh.medek.server.persistence.entities.Storygenre;

/**
 * Tests for storygenre DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class StorygenreDAOTest {
    private static final Logger LOGGER = Logger.getLogger(StorygenreDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "storygenredao.war")
        		.addClass(StorygenreDAO.class)
                .addClass(Dao.class)
                .addPackage(Storygenre.class.getPackage())
                .addAsResource("load.sql", "load.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private StorygenreDAO dao;

    final Storygenre storygenre = new Storygenre();

    public void saveStorygenreTest() {
    	storygenre.setName("test storygenre");
        dao.saveStorygenre(storygenre);
        Assert.assertNotNull("Storygenre is not created", storygenre.getId());
    }

    public void getStorygenreTest() {
        Integer id = storygenre.getId();
        Storygenre created = dao.getStorygenre(id);
        Assert.assertNotNull("Storygenre is not found", created);
    }

    public void updateStorygenreTest() {
    	Storygenre updated = dao.getStorygenre(storygenre.getId());
    	updated.setName("changed :)");
        dao.updateStorygenre(updated);
        Assert.assertTrue("Storygenre is not updated",
                dao.getStorygenre(storygenre.getId()).getName().equalsIgnoreCase("changed :)"));

    }

    public void removeStorygenreTest() {
        Integer id = storygenre.getId();
        Storygenre todel = dao.getStorygenre(id);
        dao.removeStorygenre(todel);
        Assert.assertNotNull("Storygenre is not removed", todel);
        Assert.assertNull("Storygenre is not removed(get request)", dao.getStorygenre(id));
    }

    @Test
    public void runTestsInOrder() {
        saveStorygenreTest();
        getStorygenreTest();
        updateStorygenreTest();
        removeStorygenreTest();
    }

    @Test
    public void getAllStorygenresTest() {
        List<Storygenre> l = dao.getStorygenres();
        Assert.assertNotNull("No Storygenre found", l);
    }
    
}
