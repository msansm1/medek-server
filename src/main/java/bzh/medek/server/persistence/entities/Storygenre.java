package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the storygenre database table.
 * 
 */
@Entity
@Table(name="STORYGENRE")
@NamedQuery(name="Storygenre.findAll", query="SELECT s FROM Storygenre s")
public class Storygenre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String genre;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="storygenre", fetch=FetchType.EAGER)
	private List<Book> books;

	//bi-directional many-to-one association to Movie
	@OneToMany(mappedBy="storygenre", fetch=FetchType.EAGER)
	private List<Movie> movies;

	//bi-directional many-to-one association to Tvshow
	@OneToMany(mappedBy="storygenre", fetch=FetchType.EAGER)
	private List<Tvshow> tvshows;

	public Storygenre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
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