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

import bzh.medek.server.json.movie.JsonMovie;
import bzh.medek.server.persistence.entities.Movie;

/**
 * Tests for movie DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class MovieDAOTest {
    private static final Logger LOGGER = Logger.getLogger(MovieDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "moviedao.war")
        		.addClass(MovieDAO.class)
        		.addClass(JsonMovie.class)
                .addClass(Dao.class)
                .addPackage(Movie.class.getPackage())
                .addAsResource("drop.sql", "drop.sql")
                .addAsResource("create.sql", "create.sql")
                .addAsResource("load.sql", "load.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private MovieDAO dao;

    final Movie movie = new Movie();

    public void saveMovieTest() {
    	movie.setTitle("test movie");
        movie.setCover("YOUHOU");
        movie.setIscollector(false);
        dao.saveMovie(movie);
        Assert.assertNotNull("Movie is not created", movie.getId());
    }

    public void getMovieTest() {
        Integer id = movie.getId();
        Movie created = dao.getMovie(id);
        Assert.assertNotNull("Movie is not found", created);
    }

    public void updateMovieTest() {
    	Movie updated = dao.getMovie(movie.getId());
    	updated.setCover("changed :)");
        dao.updateMovie(updated);
        Assert.assertTrue("Movie is not updated",
                dao.getMovie(movie.getId()).getCover().equalsIgnoreCase("changed :)"));

    }

    public void removeMovieTest() {
        Integer id = movie.getId();
        Movie todel = dao.getMovie(id);
        dao.removeMovie(todel);
        Assert.assertNotNull("Movie is not removed", todel);
        Assert.assertNull("Movie is not removed(get request)", dao.getMovie(id));
    }

    @Test
    public void runTestsInOrder() {
        saveMovieTest();
        getMovieTest();
        updateMovieTest();
        removeMovieTest();
    }

    @Test
    public void getAllMoviesTest() {
        List<Movie> l = dao.getMovies();
        Assert.assertNotNull("No Movie found", l);
    }

    @Test
    public void getUserMoviesTest() {
        List<JsonMovie> l = dao.getUsersMovies(1);
        Assert.assertNotNull("No user Movie found", l);
    }
    
}
