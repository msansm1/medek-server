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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getCds() {
		return cds;
	}

	public void setCds(Integer cds) {
		this.cds = cds;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getNbTracks() {
		return nbTracks;
	}

	public void setNbTracks(Integer nbTracks) {
		this.nbTracks = nbTracks;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public List<JsonTrack> getTracks() {
		return tracks;
	}

	public void setTracks(List<JsonTrack> tracks) {
		this.tracks = tracks;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public Integer getSupportId() {
		return supportId;
	}

	public void setSupportId(Integer supportId) {
		this.supportId = supportId;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getArtistId() {
		return artistId;
	}

	public void setArtistId(Integer artistId) {
		this.artistId = artistId;
	}

	public Boolean getMycollec() {
		return mycollec;
	}

	public void setMycollec(Boolean mycollec) {
		this.mycollec = mycollec;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Boolean getSigned() {
		return signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

}
