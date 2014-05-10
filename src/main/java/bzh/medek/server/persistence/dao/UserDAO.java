package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.User;

/**
 * DAO for USER table
 * 
 * @author msansm1
 *
 */
@Stateless
public class UserDAO extends Dao {

	public void updateUser(User user) {
		em.merge(user);
	}

	public void saveUser(User user) {
		em.persist(user);
		em.refresh(user);
	}

	public void removeUser(User user) {
		em.remove(em.merge(user));
		em.flush();
	}

	public List<User> getUsers() {
		return em.createQuery("from User", User.class)
				.getResultList();
	}

	public User getUser(Integer id) {
		return em.find(User.class, id);
	}
}
