package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the support database table.
 * 
 */
@Entity
@Table(name="SUPPORT")
@NamedQuery(name="Support.findAll", query="SELECT s FROM Support s")
public class Support implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String support;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="supportBean", fetch=FetchType.EAGER)
	private List<Album> albums;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="supportBean", fetch=FetchType.EAGER)
	private List<Movie> movies;

	//bi-directional many-to-one association to Tvshow
	@OneToMany(mappedBy="supportBean", fetch=FetchType.EAGER)
	private List<Tvshow> tvshows;

	public Support() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSupport() {
		return this.support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setSupportBean(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setSupportBean(null);

		return album;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Movie addMovy(Movie movy) {
		getMovies().add(movy);
		movy.setSupportBean(this);

		return movy;
	}

	public Movie removeMovy(Movie movy) {
		getMovies().remove(movy);
		movy.setSupportBean(null);

		return movy;
	}

	public List<Tvshow> getTvshows() {
		return this.tvshows;
	}

	public void setTvshows(List<Tvshow> tvshows) {
		this.tvshows = tvshows;
	}

	public Tvshow addTvshow(Tvshow tvshow) {
		getTvshows().add(tvshow);
		tvshow.setSupportBean(this);

		return tvshow;
	}

	public Tvshow removeTvshow(Tvshow tvshow) {
		getTvshows().remove(tvshow);
		tvshow.setSupportBean(null);

		return tvshow;
	}

}