package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TVARTIST database table.
 * 
 */
@Entity
@Table(name="TVARTIST")
@NamedQuery(name="Tvartist.findAll", query="SELECT t FROM Tvartist t")
public class Tvartist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TvartistPK id;

	//bi-directional many-to-one association to Artist
	@ManyToOne
	@JoinColumn(name="ARTIST", nullable=false, insertable=false, updatable=false)
	private Artist artistBean;

	//bi-directional many-to-one association to Artisttype
	@ManyToOne
	@JoinColumn(name="TYPE")
	private Artisttype artisttype;

	//bi-directional many-to-one association to Tvshow
	@ManyToOne
	@JoinColumn(name="TVSHOW", nullable=false, insertable=false, updatable=false)
	private Tvshow tvshowBean;

	public Tvartist() {
	}

	public TvartistPK getId() {
		return this.id;
	}

	public void setId(TvartistPK id) {
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

	public Tvshow getTvshowBean() {
		return this.tvshowBean;
	}

	public void setTvshowBean(Tvshow tvshowBean) {
		this.tvshowBean = tvshowBean;
	}

}