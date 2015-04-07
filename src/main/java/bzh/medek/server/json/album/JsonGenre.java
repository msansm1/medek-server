package bzh.medek.server.json.album;

public class JsonGenre {
	private Integer id;
	private String name;
	
	public JsonGenre() {
		super();
	}

	public JsonGenre(Integer id, String name) {
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
