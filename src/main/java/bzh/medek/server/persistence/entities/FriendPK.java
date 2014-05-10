package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the friend database table.
 * 
 */
@Embeddable
public class FriendPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int user;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int friend;

	public FriendPK() {
	}
	public int getUser() {
		return this.user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getFriend() {
		return this.friend;
	}
	public void setFriend(int friend) {
		this.friend = friend;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof FriendPK)) {
			return false;
		}
		FriendPK castOther = (FriendPK)other;
		return 
			(this.user == castOther.user)
			&& (this.friend == castOther.friend);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.user;
		hash = hash * prime + this.friend;
		
		return hash;
	}
}