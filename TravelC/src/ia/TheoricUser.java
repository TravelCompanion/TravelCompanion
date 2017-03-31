package ia;

import java.util.HashMap;

import cte.Constants;
import cte.PlaceType;
import tools.list.FileStruct;
import tools.math.CoordinatesDouble;

public class TheoricUser {
	private String id = "user";
	private CoordinatesDouble position;
	private HashMap<PlaceType, Double> preferences = new HashMap<PlaceType, Double>();
	private FileStruct<CoordinatesDouble> visitedRecently = new FileStruct<CoordinatesDouble>();

	public TheoricUser() {
		this.position = new CoordinatesDouble(new double[] { 0, 0 });
		preferences.put(PlaceType.MONUMENT1, 0.7);
		preferences.put(PlaceType.MONUMENT2, 0.8);
		preferences.put(PlaceType.MONUMENT3, 0.6);
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
