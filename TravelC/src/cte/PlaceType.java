package cte;

public enum PlaceType {
	MONUMENT1, MONUMENT2, MONUMENT3;
	public static PlaceType getPlaceType(String type) {
		switch (type) {
		case "monument1":
			return MONUMENT1;
		case "monument2":
			return MONUMENT2;
		case "monument3":
			return MONUMENT3;
		default:
			return null;

		}
	}
}
