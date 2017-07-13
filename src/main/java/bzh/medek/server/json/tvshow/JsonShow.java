package bzh.medek.server.json.tvshow;

import java.util.Date;
import java.util.List;

import bzh.medek.server.json.JsonLang;

public class JsonShow {
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
	private Integer season;
	private String series;
	private Boolean isSeriesDone;
	private List<JsonLang> langs;
	private List<JsonLang> subtitles;
	private Boolean mycollec;
	private Integer rating;
	
	public JsonShow() {
		super();
	}

	public JsonShow(Integer id, String title, String cover, String description) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
		this.description = description;
	}

	public JsonShow(Integer id, String title, String description,
			Date releaseDate, String cover, String length, Integer season,
			String series, Boolean isSeriesDone) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.cover = cover;
		this.length = length;
		this.season = season;
		this.series = series;
		this.isSeriesDone = isSeriesDone;
	}


	public JsonShow(Integer id, String title, String description,
			Date releaseDate, String cover, String support, Integer supportId,
			String genre, Integer genreId, String length, Integer season,
			String series, Boolean isSeriesDone) {
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
		this.season = season;
		this.series = series;
		this.isSeriesDone = isSeriesDone;
	}

	public JsonShow(Integer id, String title, String description,
			Date releaseDate, String cover, String support, Integer supportId,
			String genre, Integer genreId, String length, Integer season,
			String series, Boolean isSeriesDone, List<JsonLang> langs,
			List<JsonLang> subtitles, Boolean mycollec, Integer rating) {
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
		this.season = season;
		this.series = series;
		this.isSeriesDone = isSeriesDone;
		this.langs = langs;
		this.subtitles = subtitles;
		this.mycollec = mycollec;
		this.rating = rating;
	}

	public Integer getId() {
		return id;
	}

	public JsonShow setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public JsonShow setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getCover() {
		return cover;
	}

	public JsonShow setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public JsonShow setDescription(String description) {
		this.description = description;
		return this;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public JsonShow setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}

	public String getSupport() {
		return support;
	}

	public JsonShow setSupport(String support) {
		this.support = support;
		return this;
	}

	public Integer getSupportId() {
		return supportId;
	}

	public JsonShow setSupportId(Integer supportId) {
		this.supportId = supportId;
		return this;
	}

	public String getGenre() {
		return genre;
	}

	public JsonShow setGenre(String genre) {
		this.genre = genre;
		return this;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public JsonShow setGenreId(Integer genreId) {
		this.genreId = genreId;
		return this;
	}

	public String getLength() {
		return length;
	}

	public JsonShow setLength(String length) {
		this.length = length;
		return this;
	}

	public Integer getSeason() {
		return season;
	}

	public JsonShow setSeason(Integer season) {
		this.season = season;
		return this;
	}

	public String getSeries() {
		return series;
	}

	public JsonShow setSeries(String series) {
		this.series = series;
		return this;
	}

	public Boolean getIsSeriesDone() {
		return isSeriesDone;
	}

	public JsonShow setIsSeriesDone(Boolean isSeriesDone) {
		this.isSeriesDone = isSeriesDone;
		return this;
	}

	public List<JsonLang> getLangs() {
		return langs;
	}

	public JsonShow setLangs(List<JsonLang> langs) {
		this.langs = langs;
		return this;
	}

	public List<JsonLang> getSubtitles() {
		return subtitles;
	}

	public JsonShow setSubtitles(List<JsonLang> subtitles) {
		this.subtitles = subtitles;
		return this;
	}

	public Boolean getMycollec() {
		return mycollec;
	}

	public JsonShow setMycollec(Boolean mycollec) {
		this.mycollec = mycollec;
		return this;
	}

	public Integer getRating() {
		return rating;
	}

	public JsonShow setRating(Integer rating) {
		this.rating = rating;
		return this;
	}

}
