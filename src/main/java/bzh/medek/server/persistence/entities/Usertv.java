package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usertv database table.
 * 
 */
@Entity
@Table(name="USERTV")
@NamedQuery(name="Usertv.findAll", query="SELECT u FROM Usertv u")
public class Usertv implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UsertvPK id;

	@Column(name="`COMMENT`", length=100)
	private String comment;

	private Integer rating;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="USER", nullable=false, insertable=false, updatable=false)
	private User userBean;

	//bi-directional many-to-one association to Tvshow
	@ManyToOne
	@JoinColumn(name="TVSHOW", nullable=false, insertable=false, updatable=false)
	private Tvshow tvshowBean;

	public Usertv() {
	}

	public UsertvPK getId() {
		return this.id;
	}

	public void setId(UsertvPK id) {
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

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

	public Tvshow getTvshowBean() {
		return this.tvshowBean;
	}

	public void setTvshowBean(Tvshow tvshowBean) {
		this.tvshowBean = tvshowBean;
	}

}