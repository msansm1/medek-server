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

import bzh.medek.server.persistence.entities.Database;

/**
 * Tests for database DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class DatabaseDAOTest {
    private static final Logger LOGGER = Logger.getLogger(DatabaseDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "databasedao.war")
        		.addClass(DatabaseDAO.class)
                .addClass(Dao.class)
                .addPackage(Database.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private DatabaseDAO dao;

    final Database database = new Database();

    public void saveDatabaseTest() {
    	database.setVersion("test database");
        dao.saveDatabase(database);
        Assert.assertNotNull("Database is not created", database.getId());
    }

    public void getDatabaseTest() {
        Integer id = database.getId();
        Database created = dao.getDatabase(id);
        Assert.assertNotNull("Database is not found", created);
    }

    public void updateDatabaseTest() {
    	Database updated = dao.getDatabase(database.getId());
    	updated.setVersion("changed :)");
        dao.updateDatabase(updated);
        Assert.assertTrue("Database is not updated",
                dao.getDatabase(database.getId()).getVersion().equalsIgnoreCase("changed :)"));

    }

    public void removeDatabaseTest() {
        Integer id = database.getId();
        Database todel = dao.getDatabase(id);
        dao.removeDatabase(todel);
        Assert.assertNotNull("Database is not removed", todel);
        Assert.assertNull("Database is not removed(get request)", dao.getDatabase(id));
    }

    @Test
    public void runTestsInOrder() {
        saveDatabaseTest();
        getDatabaseTest();
        updateDatabaseTest();
        removeDatabaseTest();
    }

    @Test
    public void getAllDatabasesTest() {
        List<Database> l = dao.getAllDatabases();
        Assert.assertNotNull("No Database found", l);
    }
    
}
