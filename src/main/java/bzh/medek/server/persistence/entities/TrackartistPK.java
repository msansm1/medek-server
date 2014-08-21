package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TRACKARTIST database table.
 * 
 */
@Embeddable
public class TrackartistPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="TRACK", insertable=false, updatable=false, unique=true, nullable=false)
	private int track;

	@Column(name="ARTIST", insertable=false, updatable=false, unique=true, nullable=false)
	private int artist;

	public TrackartistPK() {
	}
	public int getTrack() {
		return this.track;
	}
	public void setTrack(int track) {
		this.track = track;
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
		if (!(other instanceof TrackartistPK)) {
			return false;
		}
		TrackartistPK castOther = (TrackartistPK)other;
		return 
			(this.track == castOther.track)
			&& (this.artist == castOther.artist);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.track;
		hash = hash * prime + this.artist;
		
		return hash;
	}
}