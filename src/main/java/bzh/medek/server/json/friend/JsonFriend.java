package bzh.medek.server.json.friend;

public class JsonFriend {
	private Integer userId;
	private Integer friendId;
	private String friendLogin;
	private Boolean accepted;
	private Boolean sharedCollection;
	
	public JsonFriend() {
		super();
	}

	public JsonFriend(Integer userId, Integer friendId, String friendLogin, Boolean accepted,
			Boolean sharedCollection) {
		super();
		this.userId = userId;
		this.friendId = friendId;
		this.friendLogin = friendLogin;
		this.accepted = accepted;
		this.sharedCollection = sharedCollection;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFriendId() {
		return friendId;
	}

	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}

	public String getFriendLogin() {
		return friendLogin;
	}

	public void setFriendLogin(String friendLogin) {
		this.friendLogin = friendLogin;
	}

	public Boolean getAccepted() {
		return accepted;
	}

	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}

	public Boolean getSharedCollection() {
		return sharedCollection;
	}

	public void setSharedCollection(Boolean sharedCollection) {
		this.sharedCollection = sharedCollection;
	}
}
