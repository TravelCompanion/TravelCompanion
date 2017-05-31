package api.data;


import common.convertion.UserConverter;
import common.data.Constants;
import common.data.VirtualUser;
import common.type.TypeConfiguration;
import common.type.TypeManager;
import tools.list.FileStruct;
import tools.math.CoordinatesDouble;
import tools.math.Matrix;

public class TheoricUser implements UserConverter<TheoricUser>{
	
	private String id = "user";
	private CoordinatesDouble position;
	private Matrix preferences = Matrix.kmatrix(1,TypeConfiguration.number, 0.5);
	private FileStruct<CoordinatesDouble> visitedRecently = new FileStruct<CoordinatesDouble>();
	
	public TheoricUser() {
		this.position = new CoordinatesDouble(new double[] { 49, 2 });
	}

	public TheoricUser(String id, CoordinatesDouble position) {
		this.id = id;
		this.position = position;
	}

	public CoordinatesDouble getPosition() {
		return position;
	}

	public Matrix getPreferences() {
		return preferences;
	}

	public void moveTo(CoordinatesDouble newPos) {
		this.position = newPos;
	}

	public TheoricUser fromVirtualUser(VirtualUser virtualUser) {
		TheoricUser user = new TheoricUser(virtualUser.getId(), virtualUser.getPosition());
		preferences = TypeManager.typeMapToColumnMatrix(virtualUser.getPreferences());
		return user;
	}

	public VirtualUser toVirtualUser() {
		VirtualUser virtualUser = new VirtualUser(id, position);
		virtualUser.getPreferences().putAll(TypeManager.matrixTotypeMap(preferences));
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

	public void updatePreferences(Matrix weights) {
		this.preferences = weights;
	}
	

}
