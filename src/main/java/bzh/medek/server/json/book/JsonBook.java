package bzh.medek.server.json.book;

public class JsonBook {
	private Integer id;
	private String name;
	private String author;
	private String editor;
	
	public JsonBook() {
		super();
	}

	public JsonBook(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.author = "";
		this.editor = "";
	}

	public JsonBook(Integer id, String name, String author, String editor) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.editor = editor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
