package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the useralbum database table.
 * 
 */
@Embeddable
public class UseralbumPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int user;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int album;

	public UseralbumPK() {
	}
	public int getUser() {
		return this.user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getAlbum() {
		return this.album;
	}
	public void setAlbum(int album) {
		this.album = album;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UseralbumPK)) {
			return false;
		}
		UseralbumPK castOther = (UseralbumPK)other;
		return 
			(this.user == castOther.user)
			&& (this.album == castOther.album);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.user;
		hash = hash * prime + this.album;
		
		return hash;
	}
}