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

	public String getRealisator() {
		return realisator;
	}

	public void setRealisator(String realisator) {
		this.realisator = realisator;
	}

	public Integer getRealisatorId() {
		return realisatorId;
	}

	public void setRealisatorId(Integer realisatorId) {
		this.realisatorId = realisatorId;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Integer getProducerId() {
		return producerId;
	}

	public void setProducerId(Integer producerId) {
		this.producerId = producerId;
	}

	public String getScenarist() {
		return scenarist;
	}

	public void setScenarist(String scenarist) {
		this.scenarist = scenarist;
	}

	public Integer getScenaristId() {
		return scenaristId;
	}

	public void setScenaristId(Integer scenaristId) {
		this.scenaristId = scenaristId;
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

}
