package api.gps;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import api.data.TheoricMainUser;
import api.data.TheoricPlace;
import api.data.TheoricUser;
import common.data.NoPlaceFoundException;
import common.data.NoUserFoundException;
import common.type.TypeConfiguration;
import model.Monument;
import model.Utilisateur;
import persistence.PersistenceData;
import tools.math.CoordinatesDouble;

public class TheoricDataBase {
	private static double searchRange = 20;
	private static TheoricDataBase dataBase;
	//private PersistenceData p;
	
	public static TheoricMainUser mainUser;
	public static ArrayList<TheoricUser> friends = new ArrayList<TheoricUser>();

	public static ArrayList<TheoricPlace> places = new ArrayList<TheoricPlace>();
	private static HashMap<CoordinatesDouble, TheoricPlace> hashPlaces = new HashMap<CoordinatesDouble, TheoricPlace>();
	private static HashMap<String, TheoricUser> hashFriends = new HashMap<String, TheoricUser>();

	private TheoricDataBase() {
		TypeConfiguration.getConfig();
	}

	public static TheoricDataBase getDataBase() {
		if (dataBase == null)
			dataBase = new TheoricDataBase();
		return dataBase;
		
	}

	public static void restartDataBase() {
		dataBase = new TheoricDataBase();
	}

	public static void requestMainUser(PersistenceData persistenceData,int id) throws SQLException, NoUserFoundException {
		TheoricDataBase.getDataBase();
		mainUser =  new TheoricMainUser().fromDatabaselUser(persistenceData.persisteUser(id));
	}
	
	public static void requestUpateMainUser(PersistenceData persistenceData){
		Utilisateur utilisateur = mainUser.toDatabaseUser();
		persistenceData.updateUserPref(utilisateur);
	}
	
	public static void requestFriends() {
		TheoricDataBase.getDataBase();
		/*
		 * friends.clear(); ArrayList<VirtualUser> virusers =
		 * VirtualDataBase.requestFriends(); TheoricUser theoricUser; for
		 * (VirtualUser user : virusers) { theoricUser = new
		 * TheoricUser().fromVirtualUser(user); friends.add(theoricUser);
		 * hashFriends.put(theoricUser.getId(), theoricUser); }
		 */
	}

	public static void requestNearPlace(PersistenceData p) throws NoPlaceFoundException, SQLException {
		TheoricDataBase.getDataBase();
		ArrayList<Monument> monuments = (ArrayList<Monument>) p.persisteMonument(mainUser.getPosition().getX(),
				mainUser.getPosition().getY(),searchRange);
		TheoricPlace thplace;
		for (Monument place : monuments) {
			thplace = new TheoricPlace().fromDatabasePlace(place);
			places.add(thplace);
			hashPlaces.put(thplace.getCoords(), thplace);
		}
	}

	public static TheoricPlace getPlace(int num) {
		return places.get(num);
	}

	public static TheoricPlace getPlace(CoordinatesDouble coordinatesDouble) {
		return hashPlaces.get(coordinatesDouble);
	}

	public static TheoricUser getFriend(int num) {
		return friends.get(num);
	}

	public double getSearchRange() {
		return searchRange;
	}

	public static TheoricUser get(String name) {
		return hashFriends.get(name);
	}

	public static void changeSearchRange(double range){
		searchRange = range;
	}
}
