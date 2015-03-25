package bzh.medek.server.json.movie;

import java.util.Date;
import java.util.List;

import bzh.medek.server.json.JsonLang;

public class JsonMovie {
	private Integer id;
	private String title;
	private String description;
	private Date releaseDate;
	private String cover;
	private String support;
	private Integer supportId;
	private String genre;
	private Integer genreId;
	private String length;
	private Boolean isCollector;
	private List<JsonLang> langs;
	private List<JsonLang> subtitles;
	
	public JsonMovie() {
		super();
	}

	public JsonMovie(Integer id, String title, String description,
			Date releaseDate, String cover, String support,
			Integer supportId, String genre, Integer genreId, String length,
			Boolean isCollector) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.cover = cover;
		this.support = support;
		this.supportId = supportId;
		this.genre = genre;
		this.genreId = genreId;
		this.length = length;
		this.isCollector = isCollector;
	}

	public JsonMovie(Integer id, String title, String description,
			Date releaseDate, String cover, String support,
			Integer supportId, String genre, Integer genreId, String length,
			Boolean isCollector, List<JsonLang> langs, List<JsonLang> subtitles) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.cover = cover;
		this.support = support;
		this.supportId = supportId;
		this.genre = genre;
		this.genreId = genreId;
		this.length = length;
		this.isCollector = isCollector;
		this.langs = langs;
		this.subtitles = subtitles;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getSupport() {
		return support;
	}

	public void setSupport(String support) {
		this.support = support;
	}

	public Integer getSupportId() {
		return supportId;
	}

	public void setSupportId(Integer supportId) {
		this.supportId = supportId;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Boolean getIsCollector() {
		return isCollector;
	}

	public void setIsCollector(Boolean isCollector) {
		this.isCollector = isCollector;
	}

	public List<JsonLang> getLangs() {
		return langs;
	}

	public void setLangs(List<JsonLang> langs) {
		this.langs = langs;
	}

	public List<JsonLang> getSubtitles() {
		return subtitles;
	}

	public void setSubtitles(List<JsonLang> subtitles) {
		this.subtitles = subtitles;
	}

}
