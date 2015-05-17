package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USERALBUM database table.
 * 
 */
@Entity
@Table(name="USERALBUM")
@NamedQuery(name="Useralbum.findAll", query="SELECT u FROM Useralbum u")
public class Useralbum implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UseralbumPK id;

	@Column(name="`COMMENT`", length=100)
	private String comment;

	@Column(name="ISSIGNED", nullable=false)
	private Boolean issigned;

	@Column(name="RATING")
	private Integer rating;

	//bi-directional many-to-one association to Album
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ALBUM", nullable=false, insertable=false, updatable=false)
	private Album albumBean;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER", nullable=false, insertable=false, updatable=false)
	private User userBean;

	public Useralbum() {
	}

	public UseralbumPK getId() {
		return this.id;
	}

	public void setId(UseralbumPK id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIssigned() {
		return this.issigned;
	}

	public void setIssigned(Boolean issigned) {
		this.issigned = issigned;
	}

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Album getAlbumBean() {
		return this.albumBean;
	}

	public void setAlbumBean(Album albumBean) {
		this.albumBean = albumBean;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}