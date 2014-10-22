package bzh.medek.server.json.album;

public class JsonAlbum {
	private Integer id;
	private String title;
	private String cover;
	
	public JsonAlbum() {
		super();
	}

	public JsonAlbum(Integer id, String title, String cover) {
		super();
		this.id = id;
		this.title = title;
		this.cover = cover;
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

}
