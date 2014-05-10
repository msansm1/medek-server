package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the bookartist database table.
 * 
 */
@Embeddable
public class BookartistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int book;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int artist;

	public BookartistPK() {
	}
	public int getBook() {
		return this.book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public int getArtist() {
		return this.artist;
	}
	public void setArtist(int artist) {
		this.artist = artist;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof BookartistPK)) {
			return false;
		}
		BookartistPK castOther = (BookartistPK)other;
		return 
			(this.book == castOther.book)
			&& (this.artist == castOther.artist);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.book;
		hash = hash * prime + this.artist;
		
		return hash;
	}
}