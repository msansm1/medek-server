package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ALBUMARTIST database table.
 * 
 */
@Entity
@Table(name="ALBUMARTIST")
@NamedQuery(name="Albumartist.findAll", query="SELECT a FROM Albumartist a")
public class Albumartist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlbumartistPK id;

	//bi-directional many-to-one association to Album
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ALBUM", nullable=false, insertable=false, updatable=false)
	private Album albumBean;

	//bi-directional many-to-one association to Artist
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ARTIST", nullable=false, insertable=false, updatable=false)
	private Artist artistBean;

	//bi-directional many-to-one association to Artisttype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE", nullable=true)
	private Artisttype artisttype;

	public Albumartist() {
	}

	public AlbumartistPK getId() {
		return this.id;
	}

	public void setId(AlbumartistPK id) {
		this.id = id;
	}

	public Album getAlbumBean() {
		return this.albumBean;
	}

	public void setAlbumBean(Album albumBean) {
		this.albumBean = albumBean;
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

}