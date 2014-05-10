package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the movieartist database table.
 * 
 */
@Embeddable
public class MovieartistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int movie;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int artist;

	public MovieartistPK() {
	}
	public int getMovie() {
		return this.movie;
	}
	public void setMovie(int movie) {
		this.movie = movie;
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
		if (!(other instanceof MovieartistPK)) {
			return false;
		}
		MovieartistPK castOther = (MovieartistPK)other;
		return 
			(this.movie == castOther.movie)
			&& (this.artist == castOther.artist);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.movie;
		hash = hash * prime + this.artist;
		
		return hash;
	}
}