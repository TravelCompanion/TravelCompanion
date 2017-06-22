package com.example.voyage.api.model;

public class Message {
	
	private int id_user;
	private String date;
	private String user;
	private String corps;
	
	
	public Message(){
		
	}
	
	public Message(int id_user,String date, String user, String corps) {
		this.setId_user(id_user);
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

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
	

}
