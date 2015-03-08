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

import bzh.medek.server.persistence.entities.Lang;

/**
 * Tests for lang DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class LangDAOTest {
    private static final Logger LOGGER = Logger.getLogger(LangDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "langdao.war")
        		.addClass(LangDAO.class)
                .addClass(Dao.class)
                .addPackage(Lang.class.getPackage())
                .addAsResource("load.sql", "load.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private LangDAO dao;

    final Lang lang = new Lang();

    public void saveLangTest() {
    	lang.setName("test lang");
        dao.saveLang(lang);
        Assert.assertNotNull("Lang is not created", lang.getId());
    }

    public void getLangTest() {
        Integer id = lang.getId();
        Lang created = dao.getLang(id);
        Assert.assertNotNull("Lang is not found", created);
    }

    public void updateLangTest() {
    	Lang updated = dao.getLang(lang.getId());
    	updated.setName("changed :)");
        dao.updateLang(updated);
        Assert.assertTrue("Lang is not updated",
                dao.getLang(lang.getId()).getName().equalsIgnoreCase("changed :)"));

    }

    public void removeLangTest() {
        Integer id = lang.getId();
        Lang todel = dao.getLang(id);
        dao.removeLang(todel);
        Assert.assertNotNull("Lang is not removed", todel);
        Assert.assertNull("Lang is not removed(get request)", dao.getLang(id));
    }

    @Test
    public void runTestsInOrder() {
        saveLangTest();
        getLangTest();
        updateLangTest();
        removeLangTest();
    }

    @Test
    public void getAllLangsTest() {
        List<Lang> l = dao.getLangs();
        Assert.assertNotNull("No Lang found", l);
    }
    
}
