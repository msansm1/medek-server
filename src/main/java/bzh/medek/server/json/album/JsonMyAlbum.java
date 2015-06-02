package bzh.medek.server.json.album;

public class JsonMyAlbum {
	private Integer albumId;
	private Integer userId;
	private Integer rating;
	private String comment;
	private Boolean signed;
	
	public JsonMyAlbum() {
		super();
	}

	public JsonMyAlbum(Integer albumId, Integer userId, Integer rating,
			String comment, Boolean signed) {
		super();
		this.albumId = albumId;
		this.userId = userId;
		this.rating = rating;
		this.comment = comment;
		this.signed = signed;
	}

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getSigned() {
		return signed;
	}

	public void setSigned(Boolean signed) {
		this.signed = signed;
	}

}
