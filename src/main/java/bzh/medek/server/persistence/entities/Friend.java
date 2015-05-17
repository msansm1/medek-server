package bzh.medek.server.persistence.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FRIEND database table.
 * 
 */
@Entity
@Table(name="FRIEND")
@NamedQuery(name="Friend.findAll", query="SELECT f FROM Friend f")
public class Friend implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FriendPK id;

	@Column(name="ISACCEPTED", nullable=false)
	private Boolean isaccepted;

	@Column(name="ISSHAREDCOLLECTION", nullable=false)
	private Boolean issharedcollection;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FRIEND", nullable=false, insertable=false, updatable=false)
	private User user1;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER", nullable=false, insertable=false, updatable=false)
	private User user2;

	public Friend() {
	}

	public FriendPK getId() {
		return this.id;
	}

	public void setId(FriendPK id) {
		this.id = id;
	}

	public Boolean getIsaccepted() {
		return this.isaccepted;
	}

	public void setIsaccepted(Boolean isaccepted) {
		this.isaccepted = isaccepted;
	}

	public Boolean getIssharedcollection() {
		return this.issharedcollection;
	}

	public void setIssharedcollection(Boolean issharedcollection) {
		this.issharedcollection = issharedcollection;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}