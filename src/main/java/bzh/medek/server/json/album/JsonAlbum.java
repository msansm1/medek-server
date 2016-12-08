package bzh.medek.server.json.album;

import java.util.Date;
import java.util.List;

public class JsonAlbum {
	private Integer id;
	private String title;
	private String cover;
	private Date releaseDate;
	private Integer cds;
	private String genre;
	private Integer genreId;
	private Integer nbTracks;
	private String support;
	private Integer supportId;
	private String artist;
	private Integer artistId;
	private Boolean mycollec;
	private Integer rating;
	private Boolean signed;
	private List<JsonTrack> tracks;
	
	public JsonAlbum() {
		super();
	}

	public JsonAlbum(Integer id, String title, String cover) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
	}
	
	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer nbTracks, String support,
			List<JsonTrack> tracks) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.nbTracks = nbTracks;
		this.support = support;
		this.tracks = tracks;
	}

	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer genreId,
			Integer nbTracks, String support, Integer supportId,
			List<JsonTrack> tracks) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.genreId = genreId;
		this.nbTracks = nbTracks;
		this.support = support;
		this.supportId = supportId;
		this.tracks = tracks;
	}

	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer genreId,
			String support, Integer supportId, Integer nbTracks) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.genreId = genreId;
		this.nbTracks = nbTracks;
		this.support = support;
		this.supportId = supportId;
	}

	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer genreId,
			String support, Integer supportId, Integer nbTracks,
			Boolean mycollec, Integer rating,
			Boolean signed) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.genreId = genreId;
		this.nbTracks = nbTracks;
		this.support = support;
		this.supportId = supportId;
		this.mycollec = mycollec;
		this.rating = rating;
		this.signed = signed;
	}

	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer genreId,
			String support, Integer supportId, Integer nbTracks,
			Integer cds) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.genreId = genreId;
		this.nbTracks = nbTracks;
		this.support = support;
		this.supportId = supportId;
		this.cds = cds;
	}

	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer genreId,
			String support, Integer supportId, Integer nbTracks,
			Integer cds, Integer rating,
			Boolean signed) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.genreId = genreId;
		this.nbTracks = nbTracks;
		this.support = support;
		this.supportId = supportId;
		this.cds = cds;
		this.rating = rating;
		this.signed = signed;
	}

	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer genreId,
			String support, Integer supportId, Integer nbTracks,
			Integer cds, Boolean mycollec, Integer rating,
			Boolean signed) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.genreId = genreId;
		this.nbTracks = nbTracks;
		this.support = support;
		this.supportId = supportId;
		this.cds = cds;
		this.mycollec = mycollec;
		this.rating = rating;
		this.signed = signed;
	}

	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer genreId,
			Integer nbTracks, String support, Integer supportId, String artist,
			Integer artistId, Boolean mycollec, Integer rating,
			Boolean signed, List<JsonTrack> tracks) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.genreId = genreId;
		this.nbTracks = nbTracks;
		this.support = support;
		this.supportId = supportId;
		this.artist = artist;
		this.artistId = artistId;
		this.mycollec = mycollec;
		this.rating = rating;
		this.signed = signed;
		this.tracks = tracks;
	}

	public JsonAlbum(Integer id, String title, String cover,
			Date releaseDate, String genre, Integer genreId, Integer cds,
			Integer nbTracks, String support, Integer supportId, String artist,
			Integer artistId, Boolean mycollec, Integer rating,
			Boolean signed, List<JsonTrack> tracks) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.releaseDate = releaseDate;
		this.genre = genre;
		this.genreId = genreId;
		this.cds = cds;
		this.nbTracks = nbTracks;
		this.support = support;
		this.supportId = supportId;
		this.artist = artist;
		this.artistId = artistId;
		this.mycollec = mycollec;
		this.rating = rating;
		this.signed = signed;
		this.tracks = tracks;
	}

	public Integer getId() {
		return id;
	}

	public JsonAlbum setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public JsonAlbum setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getCover() {
		return cover;
	}

	public JsonAlbum setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public JsonAlbum setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}

	public Integer getCds() {
		return cds;
	}

	public JsonAlbum setCds(Integer cds) {
		this.cds = cds;
		return this;
	}

	public String getGenre() {
		return genre;
	}

	public JsonAlbum setGenre(String genre) {
		this.genre = genre;
		return this;
	}

	public Integer getNbTracks() {
		return nbTracks;
	}

	public JsonAlbum setNbTracks(Integer nbTracks) {
		this.nbTracks = nbTracks;
		return this;
	}

	public String getSupport() {
		return support;
	}

	public JsonAlbum setSupport(String support) {
		this.support = support;
		return this;
	}

	public List<JsonTrack> getTracks() {
		return tracks;
	}

	public JsonAlbum setTracks(List<JsonTrack> tracks) {
		this.tracks = tracks;
		return this;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public JsonAlbum setGenreId(Integer genreId) {
		this.genreId = genreId;
		return this;
	}

	public Integer getSupportId() {
		return supportId;
	}

	public JsonAlbum setSupportId(Integer supportId) {
		this.supportId = supportId;
		return this;
	}

	public String getArtist() {
		return artist;
	}

	public JsonAlbum setArtist(String artist) {
		this.artist = artist;
		return this;
	}

	public Integer getArtistId() {
		return artistId;
	}

	public JsonAlbum setArtistId(Integer artistId) {
		this.artistId = artistId;
		return this;
	}

	public Boolean getMycollec() {
		return mycollec;
	}

	public JsonAlbum setMycollec(Boolean mycollec) {
		this.mycollec = mycollec;
		return this;
	}

	public Integer getRating() {
		return rating;
	}

	public JsonAlbum setRating(Integer rating) {
		this.rating = rating;
		return this;
	}

	public Boolean getSigned() {
		return signed;
	}

	public JsonAlbum setSigned(Boolean signed) {
		this.signed = signed;
		return this;
	}

}
