package bzh.medek.server.json.tvshow;

public class JsonShow {
	private Integer id;
	private String title;
	private String cover;
	private String description;
	
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

}
