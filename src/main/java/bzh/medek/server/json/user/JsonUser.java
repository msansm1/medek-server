package bzh.medek.server.json.user;

public class JsonUser {
	private Integer id;
	private String login;
	private String mail;
	
	public JsonUser() {
		super();
	}

	public JsonUser(Integer id, String login, String mail) {
		super();
		this.id = id;
		this.login = login;
		this.mail = mail;
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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
