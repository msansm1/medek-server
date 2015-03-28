package bzh.medek.server.json.book;

public class JsonEditor {
	private Integer id;
	private String name;
	
	public JsonEditor() {
		super();
	}

	public JsonEditor(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
