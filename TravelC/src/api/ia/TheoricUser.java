package api.ia;

import java.util.HashMap;

import api.cte.Constants;
import api.cte.PlaceType;
import api.cte.TypeConfiguration;
import api.externalData.UserConverter;
import api.externalData.VirtualUser;
import tools.list.FileStruct;
import tools.math.CoordinatesDouble;

public class TheoricUser implements UserConverter<TheoricUser>{
	private String id = "user";
	private CoordinatesDouble position;
	private HashMap<PlaceType, Double> preferences = new HashMap<PlaceType, Double>();
	private FileStruct<CoordinatesDouble> visitedRecently = new FileStruct<CoordinatesDouble>();
	
	public TheoricUser() {
		this.position = new CoordinatesDouble(new double[] { 49, 2 });
		int i = 0;
		for(PlaceType placeType : TypeConfiguration.types)
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

	public TheoricUser fromVirtualUser(VirtualUser virtualUser) {
		TheoricUser user = new TheoricUser(virtualUser.getId(), virtualUser.getPosition());
		for (PlaceType type : virtualUser.getPreferences().keySet())
			user.getPreferences().put(type, virtualUser.getPreferences().get(type));
		return user;
	}

	public VirtualUser toVirtualUser() {
		VirtualUser virtualUser = new VirtualUser(id, position);
		for (PlaceType type : preferences.keySet())
			virtualUser.getPreferences().put(type,preferences.get(type));
		return virtualUser;		
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
