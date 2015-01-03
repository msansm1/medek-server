package bzh.medek.server.json.auth;

public class JsonLogin {
	private String login;
	private String password;
	
	public JsonLogin() {
		super();
	}

	public JsonLogin(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
