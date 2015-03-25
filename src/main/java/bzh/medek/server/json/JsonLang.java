package bzh.medek.server.json;

public class JsonLang {
	private Integer id;
	private String name;
	
	public JsonLang() {
		super();
	}

	public JsonLang(Integer id, String name) {
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
