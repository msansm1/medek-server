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
	private String realisator;
	private Integer realisatorId;
	private String producer;
	private Integer producerId;
	private String scenarist;
	private Integer scenaristId;
	private List<JsonLang> langs;
	private List<JsonLang> subtitles;
	private Boolean mycollec;
	private Integer rating;
	
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

	public JsonMovie(Integer id, String title, String description,
			Date releaseDate, String cover, String support, Integer supportId,
			String genre, Integer genreId, String length, Boolean isCollector,
			Boolean mycollec, Integer rating) {
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
		this.mycollec = mycollec;
		this.rating = rating;
	}

	public JsonMovie(Integer id, String title, String description,
			Date releaseDate, String cover, String support, Integer supportId,
			String genre, Integer genreId, String length, Boolean isCollector,
			String realisator, Integer realisatorId, String producer,
			Integer producerId, String scenarist, Integer scenaristId,
			List<JsonLang> langs, List<JsonLang> subtitles, Boolean mycollec,
			Integer rating) {
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
		this.realisator = realisator;
		this.realisatorId = realisatorId;
		this.producer = producer;
		this.producerId = producerId;
		this.scenarist = scenarist;
		this.scenaristId = scenaristId;
		this.langs = langs;
		this.subtitles = subtitles;
		this.mycollec = mycollec;
		this.rating = rating;
	}

	public Integer getId() {
		return id;
	}

	public JsonMovie setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public JsonMovie setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public JsonMovie setDescription(String description) {
		this.description = description;
		return this;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public JsonMovie setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
		return this;
	}

	public String getCover() {
		return cover;
	}

	public JsonMovie setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public String getSupport() {
		return support;
	}

	public JsonMovie setSupport(String support) {
		this.support = support;
		return this;
	}

	public Integer getSupportId() {
		return supportId;
	}

	public JsonMovie setSupportId(Integer supportId) {
		this.supportId = supportId;
		return this;
	}

	public String getGenre() {
		return genre;
	}

	public JsonMovie setGenre(String genre) {
		this.genre = genre;
		return this;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public JsonMovie setGenreId(Integer genreId) {
		this.genreId = genreId;
		return this;
	}

	public String getLength() {
		return length;
	}

	public JsonMovie setLength(String length) {
		this.length = length;
		return this;
	}

	public Boolean getIsCollector() {
		return isCollector;
	}

	public JsonMovie setIsCollector(Boolean isCollector) {
		this.isCollector = isCollector;
		return this;
	}

	public List<JsonLang> getLangs() {
		return langs;
	}

	public JsonMovie setLangs(List<JsonLang> langs) {
		this.langs = langs;
		return this;
	}

	public List<JsonLang> getSubtitles() {
		return subtitles;
	}

	public JsonMovie setSubtitles(List<JsonLang> subtitles) {
		this.subtitles = subtitles;
		return this;
	}

	public String getRealisator() {
		return realisator;
	}

	public JsonMovie setRealisator(String realisator) {
		this.realisator = realisator;
		return this;
	}

	public Integer getRealisatorId() {
		return realisatorId;
	}

	public JsonMovie setRealisatorId(Integer realisatorId) {
		this.realisatorId = realisatorId;
		return this;
	}

	public String getProducer() {
		return producer;
	}

	public JsonMovie setProducer(String producer) {
		this.producer = producer;
		return this;
	}

	public Integer getProducerId() {
		return producerId;
	}

	public JsonMovie setProducerId(Integer producerId) {
		this.producerId = producerId;
		return this;
	}

	public String getScenarist() {
		return scenarist;
	}

	public JsonMovie setScenarist(String scenarist) {
		this.scenarist = scenarist;
		return this;
	}

	public Integer getScenaristId() {
		return scenaristId;
	}

	public JsonMovie setScenaristId(Integer scenaristId) {
		this.scenaristId = scenaristId;
		return this;
	}

	public Boolean getMycollec() {
		return mycollec;
	}

	public JsonMovie setMycollec(Boolean mycollec) {
		this.mycollec = mycollec;
		return this;
	}

	public Integer getRating() {
		return rating;
	}

	public JsonMovie setRating(Integer rating) {
		this.rating = rating;
		return this;
	}

}
