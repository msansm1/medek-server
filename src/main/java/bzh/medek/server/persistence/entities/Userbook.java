package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERBOOK database table.
 * 
 */
@Entity
@Table(name="USERBOOK")
@NamedQuery(name="Userbook.findAll", query="SELECT u FROM Userbook u")
public class Userbook implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserbookPK id;

	@Column(name="`COMMENT`", length=100)
	private String comment;

	@Column(name="RATING")
	private Integer rating;

	//bi-directional many-to-one association to Book
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BOOK", nullable=false, insertable=false, updatable=false)
	private Book bookBean;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER", nullable=false, insertable=false, updatable=false)
	private User userBean;

	public Userbook() {
	}

	public UserbookPK getId() {
		return this.id;
	}

	public void setId(UserbookPK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Book getBookBean() {
		return this.bookBean;
	}

	public void setBookBean(Book bookBean) {
		this.bookBean = bookBean;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}