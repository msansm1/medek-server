package bzh.medek.server.json.movie;

public class JsonMyMovie {
	private Integer movieId;
	private Integer userId;
	private Integer rating;
	private String comment;
	
	public JsonMyMovie() {
		super();
	}

	public JsonMyMovie(Integer movieId, Integer userId, Integer rating,
			String comment) {
		super();
		this.movieId = movieId;
		this.userId = userId;
		this.rating = rating;
		this.comment = comment;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
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
