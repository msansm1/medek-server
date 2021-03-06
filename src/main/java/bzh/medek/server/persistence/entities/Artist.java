package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ARTIST database table.
 * 
 */
@Entity
@Table(name="ARTIST")
@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a")
public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="BIOLINK", length=45)
	private String biolink;

	@Column(name="FIRSTNAME", length=45)
	private String firstname;

	@Column(name="NAME", nullable=false, length=45)
	private String name;

	@Column(name="NATIONALITY", length=45)
	private String nationality;

	@Column(name="PICTURE", length=45)
	private String picture;

	//bi-directional many-to-one association to Albumartist
	@OneToMany(mappedBy="artistBean")
	private List<Albumartist> albumartists;

	//bi-directional many-to-one association to Artisttype
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE", nullable=false)
	private Artisttype artisttype;

	//bi-directional many-to-one association to Bookartist
	@OneToMany(mappedBy="artistBean")
	private List<Bookartist> bookartists;

	//bi-directional many-to-one association to Movieartist
	@OneToMany(mappedBy="artistBean")
	private List<Movieartist> movieartists;

	//bi-directional many-to-one association to Trackartist
	@OneToMany(mappedBy="artistBean")
	private List<Trackartist> trackartists;

	//bi-directional many-to-one association to Tvartist
	@OneToMany(mappedBy="artistBean")
	private List<Tvartist> tvartists;

	public Artist() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBiolink() {
		return this.biolink;
	}

	public void setBiolink(String biolink) {
		this.biolink = biolink;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNationality() {
		return this.nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public List<Albumartist> getAlbumartists() {
		return this.albumartists;
	}

	public void setAlbumartists(List<Albumartist> albumartists) {
		this.albumartists = albumartists;
	}

	public Albumartist addAlbumartist(Albumartist albumartist) {
		getAlbumartists().add(albumartist);
		albumartist.setArtistBean(this);

		return albumartist;
	}

	public Albumartist removeAlbumartist(Albumartist albumartist) {
		getAlbumartists().remove(albumartist);
		albumartist.setArtistBean(null);

		return albumartist;
	}

	public Artisttype getArtisttype() {
		return this.artisttype;
	}

	public void setArtisttype(Artisttype artisttype) {
		this.artisttype = artisttype;
	}

	public List<Bookartist> getBookartists() {
		return this.bookartists;
	}

	public void setBookartists(List<Bookartist> bookartists) {
		this.bookartists = bookartists;
	}

	public Bookartist addBookartist(Bookartist bookartist) {
		getBookartists().add(bookartist);
		bookartist.setArtistBean(this);

		return bookartist;
	}

	public Bookartist removeBookartist(Bookartist bookartist) {
		getBookartists().remove(bookartist);
		bookartist.setArtistBean(null);

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
		movieartist.setArtistBean(this);

		return movieartist;
	}

	public Movieartist removeMovieartist(Movieartist movieartist) {
		getMovieartists().remove(movieartist);
		movieartist.setArtistBean(null);

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
		trackartist.setArtistBean(this);

		return trackartist;
	}

	public Trackartist removeTrackartist(Trackartist trackartist) {
		getTrackartists().remove(trackartist);
		trackartist.setArtistBean(null);

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
		tvartist.setArtistBean(this);

		return tvartist;
	}

	public Tvartist removeTvartist(Tvartist tvartist) {
		getTvartists().remove(tvartist);
		tvartist.setArtistBean(null);

		return tvartist;
	}

}