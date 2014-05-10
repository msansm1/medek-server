package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the lang database table.
 * 
 */
@Entity
@Table(name="LANG")
@NamedQuery(name="Lang.findAll", query="SELECT l FROM Lang l")
public class Lang implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=45)
	private String lang;

	//bi-directional many-to-one association to Book
	@OneToMany(mappedBy="langBean", fetch=FetchType.EAGER)
	private List<Book> books;

	//bi-directional many-to-many association to Movie
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="movielang"
		, joinColumns={
			@JoinColumn(name="LANG", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="MOVIE", nullable=false)
			}
		)
	private List<Movie> movies1;

	//bi-directional many-to-many association to Movie
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="moviesubtitle"
		, joinColumns={
			@JoinColumn(name="SUBTITLE", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="MOVIE", nullable=false)
			}
		)
	private List<Movie> movies2;

	//bi-directional many-to-many association to Tvshow
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="tvlang"
		, joinColumns={
			@JoinColumn(name="LANG", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="TVSHOW", nullable=false)
			}
		)
	private List<Tvshow> tvshows1;

	//bi-directional many-to-many association to Tvshow
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="tvsubtitle"
		, joinColumns={
			@JoinColumn(name="SUBTITLE", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="TVSHOW", nullable=false)
			}
		)
	private List<Tvshow> tvshows2;

	public Lang() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public List<Book> getBooks() {
		return this.books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book addBook(Book book) {
		getBooks().add(book);
		book.setLangBean(this);

		return book;
	}

	public Book removeBook(Book book) {
		getBooks().remove(book);
		book.setLangBean(null);

		return book;
	}

	public List<Movie> getMovies1() {
		return this.movies1;
	}

	public void setMovies1(List<Movie> movies1) {
		this.movies1 = movies1;
	}

	public List<Movie> getMovies2() {
		return this.movies2;
	}

	public void setMovies2(List<Movie> movies2) {
		this.movies2 = movies2;
	}

	public List<Tvshow> getTvshows1() {
		return this.tvshows1;
	}

	public void setTvshows1(List<Tvshow> tvshows1) {
		this.tvshows1 = tvshows1;
	}

	public List<Tvshow> getTvshows2() {
		return this.tvshows2;
	}

	public void setTvshows2(List<Tvshow> tvshows2) {
		this.tvshows2 = tvshows2;
	}

}