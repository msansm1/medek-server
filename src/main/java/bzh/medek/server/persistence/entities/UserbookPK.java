package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the userbook database table.
 * 
 */
@Embeddable
public class UserbookPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int user;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int book;

	public UserbookPK() {
	}
	public int getUser() {
		return this.user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public int getBook() {
		return this.book;
	}
	public void setBook(int book) {
		this.book = book;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserbookPK)) {
			return false;
		}
		UserbookPK castOther = (UserbookPK)other;
		return 
			(this.user == castOther.user)
			&& (this.book == castOther.book);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.user;
		hash = hash * prime + this.book;
		
		return hash;
	}
}