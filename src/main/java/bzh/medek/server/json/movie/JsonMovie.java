package bzh.medek.server.json.movie;

public class JsonMovie {
	private Integer id;
	private String title;
	
	public JsonMovie() {
		super();
	}

	public JsonMovie(Integer id, String title) {
		super();
		this.id = id;
		this.title = title;
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

}
