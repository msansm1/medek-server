package bzh.medek.server.json.tvshow;

public class JsonMyShow {
	private Integer serieId;
	private Integer userId;
	private Integer rating;
	private String comment;
	
	public JsonMyShow() {
		super();
	}

	public JsonMyShow(Integer serieId, Integer userId, Integer rating,
			String comment) {
		super();
		this.serieId = serieId;
		this.userId = userId;
		this.rating = rating;
		this.comment = comment;
	}

	public Integer getSerieId() {
		return serieId;
	}

	public void setSerieId(Integer serieId) {
		this.serieId = serieId;
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

}
