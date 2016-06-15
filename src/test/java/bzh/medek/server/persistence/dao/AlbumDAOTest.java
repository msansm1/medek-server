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

import bzh.medek.server.json.album.JsonAlbum;
import bzh.medek.server.json.home.AlbumStats;
import bzh.medek.server.persistence.entities.Album;

/**
 * Tests for album DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class AlbumDAOTest {
    private static final Logger LOGGER = Logger.getLogger(AlbumDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "albumdao.war")
        		.addClass(AlbumDAO.class)
        		.addPackage(JsonAlbum.class.getPackage())
                .addClass(Dao.class)
                .addPackage(Album.class.getPackage())
                .addClass(AlbumStats.class)
                .addAsResource("drop.sql", "drop.sql")
                .addAsResource("create.sql", "create.sql")
                .addAsResource("load.sql", "load.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private AlbumDAO dao;

    final Album album = new Album();

    public void saveAlbumTest() {
    	album.setTitle("test album");
        album.setCover("YOUHOU");
        dao.saveAlbum(album);
        Assert.assertNotNull("Album is not created", album.getId());
    }

    public void getAlbumTest() {
        Integer id = album.getId();
        Album created = dao.getAlbum(id);
        Assert.assertNotNull("Album is not found", created);
    }

    public void updateAlbumTest() {
    	Album updated = dao.getAlbum(album.getId());
    	updated.setCover("changed :)");
        dao.updateAlbum(updated);
        Assert.assertTrue("Album is not updated",
                dao.getAlbum(album.getId()).getCover().equalsIgnoreCase("changed :)"));

    }

    public void removeAlbumTest() {
        Integer id = album.getId();
        Album todel = dao.getAlbum(id);
        dao.removeAlbum(todel);
        Assert.assertNotNull("Album is not removed", todel);
        Assert.assertNull("Album is not removed(get request)", dao.getAlbum(id));
    }

    @Test
    public void runTestsInOrder() {
        saveAlbumTest();
        getAlbumTest();
        updateAlbumTest();
        removeAlbumTest();
    }

    @Test
    public void getAllAlbumsTest() {
        List<Album> l = dao.getAlbums();
        Assert.assertNotNull("No Album found", l);
    }

    @Test
    public void getUserAlbumsTest() {
        List<JsonAlbum> l = dao.getUsersAlbums(1);
        Assert.assertFalse("No user Album found", l.isEmpty());
    }
    
}
