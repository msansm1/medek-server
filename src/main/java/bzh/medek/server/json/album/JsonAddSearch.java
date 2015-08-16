package bzh.medek.server.json.album;

public class JsonAddSearch {
	private String name;
	private String artist;
	private Integer year;
	
	public JsonAddSearch() {
		super();
	}

	public JsonAddSearch(String name, String artist, Integer year) {
		super();
		this.name = name;
		this.artist = artist;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
}
