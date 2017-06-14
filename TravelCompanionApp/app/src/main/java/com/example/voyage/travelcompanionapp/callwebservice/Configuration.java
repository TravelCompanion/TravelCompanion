package com.example.voyage.travelcompanionapp.callwebservice;
public class Configuration {
	
	
	public static String IpDevice(){
		
	return "http://37.187.181.40:8080";
	//37.187.181.40
		//"http://192.168.1.11";
		//192.168.56.1
		//http://localhost:8080
	}

	public static String getRestListMonument(){
		return "/restfulltc/rest/listmonumentproche";
	}

	public String sendData(){
		return "";
	}
}
