package bzh.medek.server.json.album;

public class JsonTrack {
	private Integer id;
	private Integer albumId;
	private String title;
	private Integer trackNb;
	private String length;
	private String artist;
	
	public JsonTrack() {
		super();
	}

	public JsonTrack(Integer id, Integer albumId, String title,
			Integer trackNb, String length, String artist) {
		super();
		this.id = id;
		this.albumId = albumId;
		this.title = title;
		this.trackNb = trackNb;
		this.length = length;
		this.artist = artist;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTrackNb() {
		return trackNb;
	}

	public void setTrackNb(Integer trackNb) {
		this.trackNb = trackNb;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

}
