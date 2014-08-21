package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERMOVIE database table.
 * 
 */
@Entity
@Table(name="USERMOVIE")
@NamedQuery(name="Usermovie.findAll", query="SELECT u FROM Usermovie u")
public class Usermovie implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsermoviePK id;

	@Column(name="`COMMENT`", length=100)
	private String comment;

	@Column(name="RATING")
	private int rating;

	//bi-directional many-to-one association to Movie
	@ManyToOne
	@JoinColumn(name="MOVIE", nullable=false, insertable=false, updatable=false)
	private Movie movieBean;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER", nullable=false, insertable=false, updatable=false)
	private User userBean;

	public Usermovie() {
	}

	public UsermoviePK getId() {
		return this.id;
	}

	public void setId(UsermoviePK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Movie getMovieBean() {
		return this.movieBean;
	}

	public void setMovieBean(Movie movieBean) {
		this.movieBean = movieBean;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}