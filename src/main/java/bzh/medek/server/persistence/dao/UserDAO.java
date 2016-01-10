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

	public Integer tokenExists(String authToken) {
        @SuppressWarnings("unchecked")
        List<Integer> l = em.createQuery("select id from User u where u.token = :param1")
                .setParameter("param1", authToken)
                .getResultList();
        if (l.isEmpty()) {
            return null;
        } else {
            return l.get(0);
        }
	}

	public User getUserByLogin(String login) {
		List<User> allUser = em
				.createQuery("from User u where u.login=:userLogin",
						User.class).setParameter("userLogin", login)
				.getResultList();
		for (User usr : allUser) {
			return usr;
		}
		return null;
	}

	public User getUserByToken(String token) {
		List<User> allUser = em
				.createQuery("from User u where u.token=:token",
						User.class).setParameter("token", token)
				.getResultList();
		if (!allUser.isEmpty()) {
			return allUser.get(0);
		}
		return null;
	}
}
