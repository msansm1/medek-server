package bzh.medek.server.json.auth;

public class JsonAuth {
	private Integer id;
	private String login;
	private String email;
	private String oldpassword;
	private String newpassword;
	private String newconfpassword;
	private String token;
	
	public JsonAuth() {
		super();
	}

	public JsonAuth(Integer id, String login, String email,
			String oldpassword, String newpassword, String newconfpassword, String token) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.oldpassword = oldpassword;
		this.newpassword = newpassword;
		this.newconfpassword = newconfpassword;
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getNewpassword() {
		return newpassword;
	}

	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}

	public String getNewconfpassword() {
		return newconfpassword;
	}

	public void setNewconfpassword(String newconfpassword) {
		this.newconfpassword = newconfpassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
