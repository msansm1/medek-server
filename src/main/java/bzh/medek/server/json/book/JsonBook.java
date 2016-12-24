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

	public JsonBook(Integer id, String title,
			String editor, Integer editorId, String cover, String description,
			Date publicationDate, String genre, Integer genreId, String type,
			Integer typeId, String lang, Integer langId, String series,
			Integer bookNb, Boolean isSerieDone) {
		super();
		this.id = id;
		this.title = title;
		this.editor = editor;
		this.editorId = editorId;
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
	}

	public JsonBook(Integer id, String title,
			String editor, Integer editorId, String collection,
			Integer collectionId, String cover, String description,
			Date publicationDate, String genre, Integer genreId, String type,
			Integer typeId, String lang, Integer langId, String series,
			Integer bookNb, Boolean isSerieDone) {
		super();
		this.id = id;
		this.title = title;
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

	public JsonBook setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public JsonBook setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getEditor() {
		return editor;
	}

	public JsonBook setEditor(String editor) {
		this.editor = editor;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public JsonBook setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getCollection() {
		return collection;
	}

	public JsonBook setCollection(String collection) {
		this.collection = collection;
		return this;
	}

	public String getCover() {
		return cover;
	}

	public JsonBook setCover(String cover) {
		this.cover = cover;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public JsonBook setDescription(String description) {
		this.description = description;
		return this;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public JsonBook setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
		return this;
	}

	public String getGenre() {
		return genre;
	}

	public JsonBook setGenre(String genre) {
		this.genre = genre;
		return this;
	}

	public String getType() {
		return type;
	}

	public JsonBook setType(String type) {
		this.type = type;
		return this;
	}

	public String getLang() {
		return lang;
	}

	public JsonBook setLang(String lang) {
		this.lang = lang;
		return this;
	}

	public String getSeries() {
		return series;
	}

	public JsonBook setSeries(String series) {
		this.series = series;
		return this;
	}

	public Integer getBookNb() {
		return bookNb;
	}

	public JsonBook setBookNb(Integer bookNb) {
		this.bookNb = bookNb;
		return this;
	}

	public Boolean getIsSerieDone() {
		return isSerieDone;
	}

	public JsonBook setIsSerieDone(Boolean isSerieDone) {
		this.isSerieDone = isSerieDone;
		return this;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public JsonBook setAuthorId(Integer authorId) {
		this.authorId = authorId;
		return this;
	}

	public Integer getEditorId() {
		return editorId;
	}

	public JsonBook setEditorId(Integer editorId) {
		this.editorId = editorId;
		return this;
	}

	public Integer getCollectionId() {
		return collectionId;
	}

	public JsonBook setCollectionId(Integer collectionId) {
		this.collectionId = collectionId;
		return this;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public JsonBook setGenreId(Integer genreId) {
		this.genreId = genreId;
		return this;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public JsonBook setTypeId(Integer typeId) {
		this.typeId = typeId;
		return this;
	}

	public Integer getLangId() {
		return langId;
	}

	public JsonBook setLangId(Integer langId) {
		this.langId = langId;
		return this;
	}

	public Boolean getMycollec() {
		return mycollec;
	}

	public JsonBook setMycollec(Boolean mycollec) {
		this.mycollec = mycollec;
		return this;
	}

	public Integer getRating() {
		return rating;
	}

	public JsonBook setRating(Integer rating) {
		this.rating = rating;
		return this;
	}

	public Boolean getSigned() {
		return signed;
	}

	public JsonBook setSigned(Boolean signed) {
		this.signed = signed;
		return this;
	}

}
