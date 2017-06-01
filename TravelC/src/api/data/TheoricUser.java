package api.data;


import common.convertion.UserConverter;
import common.data.Constants;
import model.Utilisateur;
import tools.list.FileStruct;
import tools.math.CoordinatesDouble;
import tools.math.Matrix;

public class TheoricUser implements UserConverter<TheoricUser>{
	private String userName;
	private CoordinatesDouble position;
	private FileStruct<CoordinatesDouble> visitedRecently = new FileStruct<CoordinatesDouble>();
	protected Matrix preferences;
	
	public TheoricUser(){

	}
	
	
	
	public TheoricUser(String userName, Matrix pref) {
		this.userName = userName;
		this.preferences = pref;
	}



	public CoordinatesDouble getPosition() {
		return position;
	}

	public void moveTo(CoordinatesDouble newPos){
		this.position = newPos;
	}
	
	public void visitPlace(CoordinatesDouble pos){
		if(visitedRecently.size() == Constants.MEM_SIZE)
			visitedRecently.extractFisrt();
		visitedRecently.add(pos);
	}
	
	public boolean hasVisited(CoordinatesDouble place){
		return visitedRecently.contains(place);
	}
	
	public String toString() {
		return "TheoricUser [position=" + position + ", preferences="
				+ preferences + "]";
	}



	public TheoricUser fromDatabaselUser(Utilisateur virtualUser) {
		// TODO Auto-generated method stub
		return null;
	}



	public Utilisateur toDatabaseUser() {
		String pref = ""+preferences.getValue(0,0);
		for(int i = 1; i < preferences.sizeY;i++)
			pref += preferences.getValue(0, i)+",";
		return new Utilisateur("",userName,pref);
	}



	public Matrix getPreferences() {
		return preferences;
	}
	
}
