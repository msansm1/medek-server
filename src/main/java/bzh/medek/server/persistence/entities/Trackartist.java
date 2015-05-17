package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TRACKARTIST database table.
 * 
 */
@Entity
@Table(name="TRACKARTIST")
@NamedQuery(name="Trackartist.findAll", query="SELECT t FROM Trackartist t")
public class Trackartist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TrackartistPK id;

	//bi-directional many-to-one association to Artist
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ARTIST", nullable=false, insertable=false, updatable=false)
	private Artist artistBean;

	//bi-directional many-to-one association to Artisttype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE")
	private Artisttype artisttype;

	//bi-directional many-to-one association to Track
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TRACK", nullable=false, insertable=false, updatable=false)
	private Track trackBean;

	public Trackartist() {
	}

	public TrackartistPK getId() {
		return this.id;
	}

	public void setId(TrackartistPK id) {
		this.id = id;
	}

	public Artist getArtistBean() {
		return this.artistBean;
	}

	public void setArtistBean(Artist artistBean) {
		this.artistBean = artistBean;
	}

	public Artisttype getArtisttype() {
		return this.artisttype;
	}

	public void setArtisttype(Artisttype artisttype) {
		this.artisttype = artisttype;
	}

	public Track getTrackBean() {
		return this.trackBean;
	}

	public void setTrackBean(Track trackBean) {
		this.trackBean = trackBean;
	}

}