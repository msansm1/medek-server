package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the STORYGENRE database table.
 * 
 */
@Entity
@Table(name="STORYGENRE")
@NamedQuery(name="Storygenre.findAll", query="SELECT s FROM Storygenre s")
public class Storygenre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID", unique=true, nullable=false)
	private Integer id;

	@Column(name="NAME", nullable=false, length=45)
	private String name;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="storygenre")
	private List<Book> books;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="storygenre")
	private List<Movie> movies;

	//bi-directional many-to-one association to Tvshow
	@OneToMany(mappedBy="storygenre")
	private List<Tvshow> tvshows;

	public Storygenre() {
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

	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book addBook(Book book) {
		getBooks().add(book);
		book.setStorygenre(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setStorygenre(null);

		return book;
	}

	public List<Movie> getMovies() {
		return this.movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Movie addMovy(Movie movy) {
		getMovies().add(movy);
		movy.setStorygenre(this);

		return movy;
	}

	public Movie removeMovy(Movie movy) {
		getMovies().remove(movy);
		movy.setStorygenre(null);

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
		tvshow.setStorygenre(this);

		return tvshow;
	}

	public Tvshow removeTvshow(Tvshow tvshow) {
		getTvshows().remove(tvshow);
		tvshow.setStorygenre(null);

		return tvshow;
	}

}