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

import bzh.medek.server.persistence.entities.Collection;

/**
 * Tests for collection DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class CollectionDAOTest {
    private static final Logger LOGGER = Logger.getLogger(CollectionDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "collectiondao.war")
        		.addClass(CollectionDAO.class)
                .addClass(Dao.class)
                .addPackage(Collection.class.getPackage())
                .addAsResource("load.sql", "load.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private CollectionDAO dao;

    final Collection collection = new Collection();

    public void saveCollectionTest() {
    	collection.setName("test collection");
        dao.saveCollection(collection);
        Assert.assertNotNull("Collection is not created", collection.getId());
    }

    public void getCollectionTest() {
        Integer id = collection.getId();
        Collection created = dao.getCollection(id);
        Assert.assertNotNull("Collection is not found", created);
    }

    public void updateCollectionTest() {
    	Collection updated = dao.getCollection(collection.getId());
    	updated.setName("changed :)");
        dao.updateCollection(updated);
        Assert.assertTrue("Collection is not updated",
                dao.getCollection(collection.getId()).getName().equalsIgnoreCase("changed :)"));

    }

    public void removeCollectionTest() {
        Integer id = collection.getId();
        Collection todel = dao.getCollection(id);
        dao.removeCollection(todel);
        Assert.assertNotNull("Collection is not removed", todel);
        Assert.assertNull("Collection is not removed(get request)", dao.getCollection(id));
    }

    @Test
    public void runTestsInOrder() {
        saveCollectionTest();
        getCollectionTest();
        updateCollectionTest();
        removeCollectionTest();
    }

    @Test
    public void getAllCollectionsTest() {
        List<Collection> l = dao.getCollections();
        Assert.assertNotNull("No Collection found", l);
    }
    
}
