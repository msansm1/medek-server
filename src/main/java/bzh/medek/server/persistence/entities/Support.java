package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SUPPORT database table.
 * 
 */
@Entity
@Table(name="SUPPORT")
@NamedQuery(name="Support.findAll", query="SELECT s FROM Support s")
public class Support implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="NAME", nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="supportBean")
	private List<Album> albums;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="supportBean")
	private List<Movie> movies;

	//bi-directional many-to-one association to Tvshow
	@OneToMany(mappedBy="supportBean")
	private List<Tvshow> tvshows;

	public Support() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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