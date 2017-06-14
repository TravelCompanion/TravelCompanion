package model;

public class Utilisateur {

	private int id_user;
	private String email;
	private String userName;
	private String password;
	private String preferences;

	public Utilisateur() {
	}

	public Utilisateur(int id_user, String email, String userName) {

		this.id_user = id_user;
		this.email = email;
		this.userName = userName;
	}

	public Utilisateur(int id_user, String email, String userName,
			String preferences) {
		this.id_user = id_user;
		this.email = email;
		this.userName = userName;
		this.preferences = preferences;
	}

	public Utilisateur(String email, String userName, String password,
			String preferences) {

		this.email = email;
		this.userName = userName;
		this.password = password;
		this.preferences = preferences;
	}

	public Utilisateur(int id_user, String userName) {

		this.id_user = id_user;
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}

}
