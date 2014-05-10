package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;

import bzh.medek.server.persistence.entities.Friend;

/**
 * DAO for FRIEND table
 * 
 * @author msansm1
 *
 */
@Stateless
public class FriendDAO extends Dao {

	public void updateFriend(Friend friend) {
		em.merge(friend);
	}

	public void saveFriend(Friend friend) {
		em.persist(friend);
		em.refresh(friend);
	}

	public void removeFriend(Friend friend) {
		em.remove(em.merge(friend));
		em.flush();
	}

	public List<Friend> getFriends() {
		return em.createQuery("from Friend", Friend.class)
				.getResultList();
	}

	public Friend getFriend(Integer id) {
		return em.find(Friend.class, id);
	}
}
