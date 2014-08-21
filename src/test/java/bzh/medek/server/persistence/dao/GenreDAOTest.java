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

import bzh.medek.server.persistence.entities.Genre;

/**
 * Tests for genre DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class GenreDAOTest {
    private static final Logger LOGGER = Logger.getLogger(GenreDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "genredao.war")
        		.addClass(GenreDAO.class)
                .addClass(Dao.class)
                .addPackage(Genre.class.getPackage())
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private GenreDAO dao;

    final Genre genre = new Genre();

    public void saveGenreTest() {
    	genre.setName("test genre");
        dao.saveGenre(genre);
        Assert.assertNotNull("Genre is not created", genre.getId());
    }

    public void getGenreTest() {
        Integer id = genre.getId();
        Genre created = dao.getGenre(id);
        Assert.assertNotNull("Genre is not found", created);
    }

    public void updateGenreTest() {
    	Genre updated = dao.getGenre(genre.getId());
    	updated.setName("changed :)");
        dao.updateGenre(updated);
        Assert.assertTrue("Genre is not updated",
                dao.getGenre(genre.getId()).getName().equalsIgnoreCase("changed :)"));

    }

    public void removeGenreTest() {
        Integer id = genre.getId();
        Genre todel = dao.getGenre(id);
        dao.removeGenre(todel);
        Assert.assertNotNull("Genre is not removed", todel);
        Assert.assertNull("Genre is not removed(get request)", dao.getGenre(id));
    }

    @Test
    public void runTestsInOrder() {
        saveGenreTest();
        getGenreTest();
        updateGenreTest();
        removeGenreTest();
    }

    @Test
    public void getAllGenresTest() {
        List<Genre> l = dao.getGenres();
        Assert.assertNotNull("No Genre found", l);
    }
    
}
