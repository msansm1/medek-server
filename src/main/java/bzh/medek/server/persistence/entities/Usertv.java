package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERTV database table.
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

	@Column(name="RATING")
	private Integer rating;

	//bi-directional many-to-one association to Tvshow
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TVSHOW", nullable=false, insertable=false, updatable=false)
	private Tvshow tvshowBean;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER", nullable=false, insertable=false, updatable=false)
	private User userBean;

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

	public Tvshow getTvshowBean() {
		return this.tvshowBean;
	}

	public void setTvshowBean(Tvshow tvshowBean) {
		this.tvshowBean = tvshowBean;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}