package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the tvartist database table.
 * 
 */
@Embeddable
public class TvartistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int tvshow;

	@Column(insertable=false, updatable=false, unique=true, nullable=false)
	private int artist;

	public TvartistPK() {
	}
	public int getTvshow() {
		return this.tvshow;
	}
	public void setTvshow(int tvshow) {
		this.tvshow = tvshow;
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
		if (!(other instanceof TvartistPK)) {
			return false;
		}
		TvartistPK castOther = (TvartistPK)other;
		return 
			(this.tvshow == castOther.tvshow)
			&& (this.artist == castOther.artist);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tvshow;
		hash = hash * prime + this.artist;
		
		return hash;
	}
}