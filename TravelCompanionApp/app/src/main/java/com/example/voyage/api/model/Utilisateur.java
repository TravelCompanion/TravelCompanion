package com.example.voyage.api.model;

public class Utilisateur {

	private String email;
	private String userName;
	private String password;
	private String preferences;

	public Utilisateur() {
	}

	
	
	public Utilisateur(String email, String userName) {
		super();
		this.email = email;
		this.userName = userName;
	}

	public Utilisateur(String email, String userName, String preferences) {
		super();
		this.email = email;
		this.userName = userName;
		this.preferences = preferences;
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

	@Override
	public String toString() {
		return "Utilisateur [email=" + email + ", userName=" + userName + ", password=" + password + ", preferences="
				+ preferences + "]";
	}

	
}
