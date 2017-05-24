package api.cte;

public enum PlaceType {
	MONUMENT1, MONUMENT2, MONUMENT3, EGLISE, JARDIN, PARC, IMMEUBLE, MUSEE, SOCLE, 
	CHATEAU, MAISON, PAVILLON, HOTEL, PARC_ATTRACTION, AUBERGE, VILLA, CAVES;
	// église,jardin,parc,immeuble,musee,socle,
	// chateau,maison,pavillon,hotel
	public static PlaceType getType(String type) {
		PlaceType placeType;
		placeType = getPlaceType(type);
		if (getPlaceType(type) != null)
			return placeType;
		return null;
	}

	public static PlaceType getPlaceType(String type) {
		switch (type) {
		case "monument1":
			return MONUMENT1;
		case "monument2":
			return MONUMENT2;
		case "monument3":
			return MONUMENT3;
		case "église":
			return EGLISE;
		case "jardin":
			return JARDIN;
		case "parc":
			return PARC;
		case "immeuble":
			return IMMEUBLE;
		case "musee":
			return MUSEE;
		case "socle":
			return SOCLE;
		case "chateau":
			return CHATEAU;
		case "maison":
			return MAISON;
		case "pavillon":
			return PAVILLON;
		case "hôtel":
			return HOTEL;
		case "parc_attraction":
			return PARC_ATTRACTION;
		case "auberge":
			return AUBERGE;
		case "villa":
			return VILLA;
		case "caves":
			return CAVES;
		default:
			return null;

		}
	}

}
