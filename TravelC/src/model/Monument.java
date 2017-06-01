package model;

public class Monument {

	private int id_monument;
	private String name_monument;
	private String city;
	private String region;
	private int note;
	private Double latitude;
	private Double longitude;
	private String description;
	private String type;

	private Double distance;

	public Monument() {

	}

	public Monument(int id_monument, String name_monument, String city,
			String region, int note, Double latitude, Double longitude,
			String description, String type, Double distance) {

		this.id_monument = id_monument;
		this.name_monument = name_monument;
		this.city = city;
		this.region = region;
		this.note = note;
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
		this.type = type;
		this.distance = distance;
	}

	public int getId_monument() {
		return id_monument;
	}

	public void setId_monument(int id_monument) {
		this.id_monument = id_monument;
	}

	public String getName_monument() {
		return name_monument;
	}

	public void setName_monument(String name_monument) {
		this.name_monument = name_monument;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Monument [id_monument=" + id_monument + ", name_monument=" + name_monument + ", city=" + city
				+ ", region=" + region + ", note=" + note + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", description=" + description + ", type=" + type + ", distance=" + distance + "]";
	}
	
}
