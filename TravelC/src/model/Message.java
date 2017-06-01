package model;

public class Message {
	
	private String date;
	private String user;
	private String corps;
	
	
	public Message(){
		
	}
	
	public Message(String date, String user, String corps) {
		super();
		this.date = date;
		this.user = user;
		this.corps = corps;
	}
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCorps() {
		return corps;
	}
	public void setCorps(String corps) {
		this.corps = corps;
	}
	
	

}
