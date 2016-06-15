package bzh.medek.server.persistence.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import bzh.medek.server.json.friend.JsonFriend;
import bzh.medek.server.persistence.entities.Friend;
import bzh.medek.server.persistence.entities.FriendPK;

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

	public Friend getFriend(FriendPK id) {
		return em.find(Friend.class, id);
	}

	public List<JsonFriend> getFriendsForUser(Integer userId) {
		TypedQuery<JsonFriend> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.friend.JsonFriend(f.id.user, f.id.friend, "
						+ "f.user1.login, f.isaccepted, f.issharedcollection)"
						+ "from Friend f "
						+ "where f.id.user=:userId",
						JsonFriend.class);
		q.setParameter("userId", userId);
		return q.getResultList();
	}

	public JsonFriend getFriendForUser(Integer userId, Integer friendId) {
		TypedQuery<JsonFriend> q = em.createQuery(
				"SELECT NEW bzh.medek.server.json.friend.JsonFriend(f.id.user, f.id.friend, "
						+ "f.user1.login, f.isaccepted, f.issharedcollection)"
						+ "from Friend f "
						+ "where f.id.user=:userId and f.id.friend=:friendId",
						JsonFriend.class);
		q.setParameter("userId", userId);
		q.setParameter("friendId", friendId);
		List<JsonFriend> res = q.getResultList();
		if (!res.isEmpty()) {
			return res.get(0);
		}
		return null;
	}
}
