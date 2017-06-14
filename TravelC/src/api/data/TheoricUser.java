package api.data;

import java.util.ArrayList;

import api.ia.IAManager;
import tools.list.FileStruct;
import tools.math.CoordinatesDouble;
import tools.math.Matrix;
import tools.polls.Elector;
import tools.polls.Elegible;

public class TheoricUser implements Elector{
	protected int id;
	private String userName;
	private CoordinatesDouble position;
	private ArrayList<TheoricPlace> visitedRecently = new ArrayList<TheoricPlace>();
	protected Matrix preferences;

	public TheoricUser() {

	}

	public TheoricUser(int id,String userName, Matrix pref) {
		this.id = id;
		this.userName = userName;
		this.preferences = pref;
	}

	public TheoricUser(String userName) {
		this.userName = userName;
	}

	public TheoricUser(int id_user, String userName) {
		this.id = id_user;
		this.userName = userName;
	}

	public CoordinatesDouble getPosition() {
		return position;
	}

	public void moveTo(CoordinatesDouble newPosistion) {
		this.position = newPosistion;
	}

	public boolean hasVisited(TheoricPlace place) {
		return visitedRecently.contains(place);
	}

	public String toString() {
		return "TheoricUser [position=" + position + ", preferences=" + preferences + "]";
	}

	public void updatePref(Matrix m){
		this.preferences = m;
	}
	
	public Matrix getPreferences() {
		return preferences;
	}

	public String getUserName() {
		return userName;
	}

	public double vote(Elegible elegible) {
		return IAManager.electionVote((TheoricPlace)elegible,this);
	}

	public int getId() {
		return id;
	}

	public ArrayList<TheoricPlace> getVisitedRecently() {
		return visitedRecently;
	}

}
