package com.example.voyage.travelcompanionapp.callwebservice;
public class Configuration {


	public static String IpDevice() {

		return "http://37.187.181.40:8080";
		//37.187.181.40
		//"http://192.168.1.11";
		//192.168.56.1
		//http://localhost:8080
	}

	public static String getRestListMonument(String lat, String lon) {
		return "/restfulltc/rest/listmonumentproche/" + lat + "/" + lon;
	}

	public String sendData() {
		return "";
	}


	public static String getRestUser(String iduser) {
		return "/restfulltc/rest/user/" + iduser;
	}

	public static String getIdUser(String email, String mdp) {
		return "/restfulltc/rest/connexion/" + email + "/" + mdp;
	}


	public static String inscription(String username, String email, String mdp, String type) {
		return "/restfulltc/rest/inscription/" + username + "/" + email + "/" + mdp + "/" + type;
	}

	public static String updateuser(int id,String pref) {
		return "/restfulltc/rest/updatepreferencesuser/"+ id + "/" +pref;
	}


}
