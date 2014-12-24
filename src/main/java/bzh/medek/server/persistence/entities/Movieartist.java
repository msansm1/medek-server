package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the MOVIEARTIST database table.
 * 
 */
@Entity
@Table(name="MOVIEARTIST")
@NamedQuery(name="Movieartist.findAll", query="SELECT m FROM Movieartist m")
public class Movieartist implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MovieartistPK id;

	//bi-directional many-to-one association to Artist
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ARTIST", nullable=false, insertable=false, updatable=false)
	private Artist artistBean;

	//bi-directional many-to-one association to Artisttype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE")
	private Artisttype artisttype;

	//bi-directional many-to-one association to Movie
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="MOVIE", nullable=false, insertable=false, updatable=false)
	private Movie movieBean;

	public Movieartist() {
	}

	public MovieartistPK getId() {
		return this.id;
	}

	public void setId(MovieartistPK id) {
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

	public Movie getMovieBean() {
		return this.movieBean;
	}

	public void setMovieBean(Movie movieBean) {
		this.movieBean = movieBean;
	}

}