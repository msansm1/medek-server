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

import bzh.medek.server.persistence.entities.Support;

/**
 * Tests for support DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class SupportDAOTest {
    private static final Logger LOGGER = Logger.getLogger(SupportDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "supportdao.war")
        		.addClass(SupportDAO.class)
                .addClass(Dao.class)
                .addPackage(Support.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private SupportDAO dao;

    final Support support = new Support();

    public void saveSupportTest() {
    	support.setName("test support");
        dao.saveSupport(support);
        Assert.assertNotNull("Support is not created", support.getId());
    }

    public void getSupportTest() {
        Integer id = support.getId();
        Support created = dao.getSupport(id);
        Assert.assertNotNull("Support is not found", created);
    }

    public void updateSupportTest() {
    	Support updated = dao.getSupport(support.getId());
    	updated.setName("changed :)");
        dao.updateSupport(updated);
        Assert.assertTrue("Support is not updated",
                dao.getSupport(support.getId()).getName().equalsIgnoreCase("changed :)"));

    }

    public void removeSupportTest() {
        Integer id = support.getId();
        Support todel = dao.getSupport(id);
        dao.removeSupport(todel);
        Assert.assertNotNull("Support is not removed", todel);
        Assert.assertNull("Support is not removed(get request)", dao.getSupport(id));
    }

    @Test
    public void runTestsInOrder() {
        saveSupportTest();
        getSupportTest();
        updateSupportTest();
        removeSupportTest();
    }

    @Test
    public void getAllSupportsTest() {
        List<Support> l = dao.getSupports();
        Assert.assertNotNull("No Support found", l);
    }
    
}
