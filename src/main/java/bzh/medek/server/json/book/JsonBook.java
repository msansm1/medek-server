package bzh.medek.server.json.book;

public class JsonBook {
	private Integer id;
	private String title;
	private String author;
	private String editor;
	private String collection;
	private String cover;
	private String description;
	private String publicationDate;
	private String genre;
	private String type;
	private String lang;
	private String series;
	private Integer bookNb;
	private Boolean isSerieDone;

	public JsonBook() {
		super();
	}

	public JsonBook(Integer id, String title) {
		super();
		this.id = id;
		this.setTitle(title);
		this.author = "";
		this.editor = "";
	}

	public JsonBook(Integer id, String title, String author, String editor) {
		super();
		this.id = id;
		this.setTitle(title);
		this.author = author;
		this.editor = editor;
	}

	public JsonBook(Integer id, String title, String author, String editor,
			String collection, String cover, String description,
			String publicationDate, String genre, String type, String lang,
			String series, Integer bookNb, Boolean isSerieDone) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.editor = editor;
		this.collection = collection;
		this.cover = cover;
		this.description = description;
		this.publicationDate = publicationDate;
		this.genre = genre;
		this.type = type;
		this.lang = lang;
		this.series = series;
		this.bookNb = bookNb;
		this.isSerieDone = isSerieDone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public Integer getBookNb() {
		return bookNb;
	}

	public void setBookNb(Integer bookNb) {
		this.bookNb = bookNb;
	}

	public Boolean getIsSerieDone() {
		return isSerieDone;
	}

	public void setIsSerieDone(Boolean isSerieDone) {
		this.isSerieDone = isSerieDone;
	}

}
