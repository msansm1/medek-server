package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the artisttype database table.
 * 
 */
@Entity
@Table(name="ARTISTTYPE")
@NamedQuery(name="Artisttype.findAll", query="SELECT a FROM Artisttype a")
public class Artisttype implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String type;

	//bi-directional many-to-one association to Albumartist
	@OneToMany(mappedBy="artisttype", fetch=FetchType.EAGER)
	private List<Albumartist> albumartists;

	//bi-directional many-to-one association to Bookartist
	@OneToMany(mappedBy="artisttype", fetch=FetchType.EAGER)
	private List<Bookartist> bookartists;

	//bi-directional many-to-one association to Movieartist
	@OneToMany(mappedBy="artisttype", fetch=FetchType.EAGER)
	private List<Movieartist> movieartists;

	//bi-directional many-to-one association to Trackartist
	@OneToMany(mappedBy="artisttype", fetch=FetchType.EAGER)
	private List<Trackartist> trackartists;

	//bi-directional many-to-one association to Tvartist
	@OneToMany(mappedBy="artisttype", fetch=FetchType.EAGER)
	private List<Tvartist> tvartists;

	public Artisttype() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Albumartist> getAlbumartists() {
		return this.albumartists;
	}

	public void setAlbumartists(List<Albumartist> albumartists) {
		this.albumartists = albumartists;
	}

	public Albumartist addAlbumartist(Albumartist albumartist) {
		getAlbumartists().add(albumartist);
		albumartist.setArtisttype(this);

		return albumartist;
	}

	public Albumartist removeAlbumartist(Albumartist albumartist) {
		getAlbumartists().remove(albumartist);
		albumartist.setArtisttype(null);

		return albumartist;
	}

	public List<Bookartist> getBookartists() {
		return this.bookartists;
	}

	public void setBookartists(List<Bookartist> bookartists) {
		this.bookartists = bookartists;
	}

	public Bookartist addBookartist(Bookartist bookartist) {
		getBookartists().add(bookartist);
		bookartist.setArtisttype(this);

		return bookartist;
	}

	public Bookartist removeBookartist(Bookartist bookartist) {
		getBookartists().remove(bookartist);
		bookartist.setArtisttype(null);

		return bookartist;
	}

	public List<Movieartist> getMovieartists() {
		return this.movieartists;
	}

	public void setMovieartists(List<Movieartist> movieartists) {
		this.movieartists = movieartists;
	}

	public Movieartist addMovieartist(Movieartist movieartist) {
		getMovieartists().add(movieartist);
		movieartist.setArtisttype(this);

		return movieartist;
	}

	public Movieartist removeMovieartist(Movieartist movieartist) {
		getMovieartists().remove(movieartist);
		movieartist.setArtisttype(null);

		return movieartist;
	}

	public List<Trackartist> getTrackartists() {
		return this.trackartists;
	}

	public void setTrackartists(List<Trackartist> trackartists) {
		this.trackartists = trackartists;
	}

	public Trackartist addTrackartist(Trackartist trackartist) {
		getTrackartists().add(trackartist);
		trackartist.setArtisttype(this);

		return trackartist;
	}

	public Trackartist removeTrackartist(Trackartist trackartist) {
		getTrackartists().remove(trackartist);
		trackartist.setArtisttype(null);

		return trackartist;
	}

	public List<Tvartist> getTvartists() {
		return this.tvartists;
	}

	public void setTvartists(List<Tvartist> tvartists) {
		this.tvartists = tvartists;
	}

	public Tvartist addTvartist(Tvartist tvartist) {
		getTvartists().add(tvartist);
		tvartist.setArtisttype(this);

		return tvartist;
	}

	public Tvartist removeTvartist(Tvartist tvartist) {
		getTvartists().remove(tvartist);
		tvartist.setArtisttype(null);

		return tvartist;
	}

}