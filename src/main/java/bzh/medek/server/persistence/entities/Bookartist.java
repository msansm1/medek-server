package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the BOOKARTIST database table.
 * 
 */
@Entity
@Table(name="BOOKARTIST")
@NamedQuery(name="Bookartist.findAll", query="SELECT b FROM Bookartist b")
public class Bookartist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BookartistPK id;

	//bi-directional many-to-one association to Artist
	@ManyToOne
	@JoinColumn(name="ARTIST", nullable=false, insertable=false, updatable=false)
	private Artist artistBean;

	//bi-directional many-to-one association to Artisttype
	@ManyToOne
	@JoinColumn(name="TYPE")
	private Artisttype artisttype;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="BOOK", nullable=false, insertable=false, updatable=false)
	private Book bookBean;

	public Bookartist() {
	}

	public BookartistPK getId() {
		return this.id;
	}

	public void setId(BookartistPK id) {
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

	public Book getBookBean() {
		return this.bookBean;
	}

	public void setBookBean(Book bookBean) {
		this.bookBean = bookBean;
	}

}