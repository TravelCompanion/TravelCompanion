package com.example.voyage.api.model;

public class Musee {

	private String nomRegion;
	private String nomDep;
	private String nomMusee;
	private String adresse;
	private int cp;
	private String ville;
	private String tel;
	private String fax;
	private String siteWeb;
	private String fermAnn;
	private String perOuv;
	private Double latitude;
	private Double longitude;
	
	private Double distance;

	public Musee() {

	}
	
	

	public Musee(String nomRegion, String nomDep, String nomMusee,
			String adresse, int cp, String ville, String tel, String fax,
			String siteWeb, String fermAnn, String perOuv, Double latitude,
			Double longitude, Double distance) {
		super();
		this.nomRegion = nomRegion;
		this.nomDep = nomDep;
		this.nomMusee = nomMusee;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.tel = tel;
		this.fax = fax;
		this.siteWeb = siteWeb;
		this.fermAnn = fermAnn;
		this.perOuv = perOuv;
		this.latitude = latitude;
		this.longitude = longitude;
		this.distance = distance;
	}



	public String getNomRegion() {
		return nomRegion;
	}

	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}

	public String getNomDep() {
		return nomDep;
	}

	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}

	public String getNomMusee() {
		return nomMusee;
	}

	public void setNomMusee(String nomMusee) {
		this.nomMusee = nomMusee;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSiteWeb() {
		return siteWeb;
	}

	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}

	public String getFermAnn() {
		return fermAnn;
	}

	public void setFermAnn(String fermAnn) {
		this.fermAnn = fermAnn;
	}

	public String getPerOuv() {
		return perOuv;
	}

	public void setPerOuv(String perOuv) {
		this.perOuv = perOuv;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	

}
