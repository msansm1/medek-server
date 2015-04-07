package bzh.medek.server.json.album;

import java.util.List;

public class JsonAlbum {
	private Integer id;
	private String title;
	private String cover;
	private String releaseDate;
	private String genre;
	private Integer genreId;
	private Integer nbTracks;
	private String support;
	private Integer supportId;
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
			String releaseDate, String genre, Integer nbTracks, String support,
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
			String releaseDate, String genre, Integer genreId,
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

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
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

}
