package bzh.medek.server.json.home;

public class JsonCollectionStats {
	private AlbumStats albums;
	private BookStats books;
	private MovieStats movies;
	private SerieStats series;
	
	public JsonCollectionStats() {
		super();
	}

	public JsonCollectionStats(AlbumStats albums, BookStats books,
			MovieStats movies, SerieStats series) {
		super();
		this.albums = albums;
		this.books = books;
		this.movies = movies;
		this.series = series;
	}

	public AlbumStats getAlbums() {
		return albums;
	}

	public void setAlbums(AlbumStats albums) {
		this.albums = albums;
	}

	public BookStats getBooks() {
		return books;
	}

	public void setBooks(BookStats books) {
		this.books = books;
	}

	public MovieStats getMovies() {
		return movies;
	}

	public void setMovies(MovieStats movies) {
		this.movies = movies;
	}

	public SerieStats getSeries() {
		return series;
	}

	public void setSeries(SerieStats series) {
		this.series = series;
	}
	
}
