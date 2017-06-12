package com.example.voyage.api.common.type;

public class PlaceType {
	/**This class define what a place is*/
	private String name;
	private int id;
	/**position in the list of type*/

	private PlaceType() {
	}

	private PlaceType(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public static PlaceType createType(String name, int id) {
		return new PlaceType(name, id);
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return "PlaceType [name=" + name + ", id=" + id + "]";
	}

}
