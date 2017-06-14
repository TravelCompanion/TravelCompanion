package model;

import java.util.List;

public class InscriptionEntry {

	private String email;
	private String userName;
	private String password;
	private List<String> prefSelect;

	public InscriptionEntry() {
	}

	public InscriptionEntry(String email, String userName, String password,
			List<String> prefSelect) {
		super();
		this.email = email;
		this.userName = userName;
		this.password = password;
		this.prefSelect = prefSelect;
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

	public List<String> getPrefSelect() {
		return prefSelect;
	}

	public void setPrefSelect(List<String> prefSelect) {
		this.prefSelect = prefSelect;
	}

}
