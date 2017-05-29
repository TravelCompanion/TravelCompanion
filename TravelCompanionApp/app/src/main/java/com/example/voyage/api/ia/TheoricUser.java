package com.example.voyage.api.ia;

import java.util.HashMap;

import com.example.voyage.api.cte.Constants;
import com.example.voyage.api.cte.PlaceType;
import com.example.voyage.api.tools.list.FileStruct;
import com.example.voyage.api.tools.math.CoordinatesDouble;

public class TheoricUser {
	private String id = "user";
	private CoordinatesDouble position;
	private HashMap<PlaceType, Double> preferences = new HashMap<PlaceType, Double>();
	private FileStruct<CoordinatesDouble> visitedRecently = new FileStruct<CoordinatesDouble>();
	
	public TheoricUser() {
		this.position = new CoordinatesDouble(new double[] { 49, 2 });
		int i = 0;
		for(PlaceType placeType : PlaceType.values())
		{
			preferences.put(placeType, ((i%4)+1)*0.2);
				i++;
		}
	}

	public TheoricUser(String id, CoordinatesDouble position) {
		this.id = id;
		this.position = position;
	}

	public CoordinatesDouble getPosition() {
		return position;
	}

	public HashMap<PlaceType, Double> getPreferences() {
		return preferences;
	}

	public void moveTo(CoordinatesDouble newPos) {
		this.position = newPos;
	}

	public void visitPlace(CoordinatesDouble pos) {
		if (visitedRecently.size() == Constants.MEM_SIZE)
			visitedRecently.extractFisrt();
		visitedRecently.add(pos);
	}

	public boolean hasVisited(CoordinatesDouble place) {
		return visitedRecently.contains(place);
	}

	public FileStruct<CoordinatesDouble> getVisitedRecently() {
		return visitedRecently;
	}

	public String getId() {
		return id;
	}

	public String toString() {
		return "[" + position + " , " + preferences + "]";
	}

}
