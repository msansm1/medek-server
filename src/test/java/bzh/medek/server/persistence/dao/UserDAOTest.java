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

import bzh.medek.server.persistence.entities.User;

/**
 * Tests for user DAO
 * 
 * @author msansm1
 * 
 */
@RunWith(Arquillian.class)
public class UserDAOTest {
    private static final Logger LOGGER = Logger.getLogger(UserDAOTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        final WebArchive war = ShrinkWrap.create(WebArchive.class, "userdao.war")
        		.addClass(UserDAO.class)
                .addClass(Dao.class)
                .addPackage(User.class.getPackage())
                .addAsResource("drop.sql", "drop.sql")
                .addAsResource("create.sql", "create.sql")
                .addAsResource("load.sql", "load.sql")
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

        LOGGER.info(war.toString(Formatters.VERBOSE));

        return war;
    }

    @Inject
    private UserDAO dao;

    final User user = new User();

    public void saveUserTest() {
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("log@test.tt");
        dao.saveUser(user);
        Assert.assertNotNull("User is not created", user.getId());
    }

    public void getUserTest() {
        Integer id = user.getId();
        User created = dao.getUser(id);
        Assert.assertNotNull("User is not found", created);
    }

    public void updateUserTest() {
    	User updated = dao.getUser(user.getId());
    	updated.setLogin("changed :)");
        dao.updateUser(updated);
        Assert.assertTrue("User is not updated",
                dao.getUser(user.getId()).getLogin().equalsIgnoreCase("changed :)"));

    }

    public void removeUserTest() {
        Integer id = user.getId();
        User todel = dao.getUser(id);
        dao.removeUser(todel);
        Assert.assertNotNull("User is not removed", todel);
        Assert.assertNull("User is not removed(get request)", dao.getUser(id));
    }

    @Test
    public void runTestsInOrder() {
        saveUserTest();
        getUserTest();
        updateUserTest();
        removeUserTest();
    }

    @Test
    public void getAllUsersTest() {
        List<User> l = dao.getUsers();
        Assert.assertNotNull("No User found", l);
    }
    
}
