package bzh.medek.server.json.admin.user;

public class JsonAdminUser {
	private Integer id;
	private String login;
	private String email;
	private String password;
	private String confpassword;
	
	public JsonAdminUser() {
		super();
	}

	public JsonAdminUser(Integer id, String login, String email) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
	}

	public JsonAdminUser(Integer id, String login, String email, String password) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfpassword() {
		return confpassword;
	}

	public void setConfpassword(String confpassword) {
		this.confpassword = confpassword;
	}

}
