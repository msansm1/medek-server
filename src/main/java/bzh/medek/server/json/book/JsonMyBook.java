package bzh.medek.server.json.book;

public class JsonMyBook {
	private Integer bookId;
	private Integer userId;
	private Integer rating;
	private String comment;
	private Boolean signed;
	
	public JsonMyBook() {
		super();
	}

	public JsonMyBook(Integer bookId, Integer userId, Integer rating,
			String comment, Boolean signed) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.rating = rating;
		this.comment = comment;
		this.signed = signed;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
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
