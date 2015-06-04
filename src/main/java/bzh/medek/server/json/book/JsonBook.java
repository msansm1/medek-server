package bzh.medek.server.json.book;

import java.util.Date;

public class JsonBook {
	private Integer id;
	private String title;
	private String author;
	private Integer authorId;
	private String editor;
	private Integer editorId;
	private String collection;
	private Integer collectionId;
	private String cover;
	private String description;
	private Date publicationDate;
	private String genre;
	private Integer genreId;
	private String type;
	private Integer typeId;
	private String lang;
	private Integer langId;
	private String series;
	private Integer bookNb;
	private Boolean isSerieDone;
	private Boolean mycollec;
	private Integer rating;
	private Boolean signed;

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

	public JsonBook(Integer id, String title, String author, Integer authorId,
			String editor, Integer editorId, String collection,
			Integer collectionId, String cover, String description,
			Date publicationDate, String genre, Integer genreId, String type,
			Integer typeId, String lang, Integer langId, String series,
			Integer bookNb, Boolean isSerieDone, Boolean mycollec,
			Integer rating, Boolean signed) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.authorId = authorId;
		this.editor = editor;
		this.editorId = editorId;
		this.collection = collection;
		this.collectionId = collectionId;
		this.cover = cover;
		this.description = description;
		this.publicationDate = publicationDate;
		this.genre = genre;
		this.genreId = genreId;
		this.type = type;
		this.typeId = typeId;
		this.lang = lang;
		this.langId = langId;
		this.series = series;
		this.bookNb = bookNb;
		this.isSerieDone = isSerieDone;
		this.mycollec = mycollec;
		this.rating = rating;
		this.signed = signed;
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

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
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

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public Integer getEditorId() {
		return editorId;
	}

	public void setEditorId(Integer editorId) {
		this.editorId = editorId;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getLangId() {
		return langId;
	}

	public void setLangId(Integer langId) {
		this.langId = langId;
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
