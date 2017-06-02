package api.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.convertion.iabdd.TheoricMonumentConverter;
import common.convertion.iabdd.TheoricUserConverter;
import common.data.NoPlaceFoundException;
import common.data.NoUserFoundException;
import common.type.TypeConfiguration;
import model.Monument;
import model.Utilisateur;
import persistence.PersistenceData;
import tools.math.CoordinatesDouble;

public class TheoricDataBase {

	private static TheoricDataBase dataBase;
	private static double searchRange = 20;
	public static TheoricMainUser mainUser;

	public static ArrayList<TheoricUser> friends = new ArrayList<TheoricUser>();
	private static HashMap<String, TheoricUser> hashFriends = new HashMap<String, TheoricUser>();

	public static ArrayList<TheoricPlace> places = new ArrayList<TheoricPlace>();
	private static HashMap<CoordinatesDouble, TheoricPlace> hashPlaces = new HashMap<CoordinatesDouble, TheoricPlace>();
	private static final TheoricUserConverter USER_CONVERTER = new TheoricUserConverter();
	private static final TheoricMonumentConverter MONUMENT_CONVERTER = new TheoricMonumentConverter();
	
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

	public static void requestMainUser(PersistenceData persistenceData, int id)
			throws SQLException, NoUserFoundException {
		TheoricDataBase.getDataBase();
		mainUser =USER_CONVERTER.fromDatabaseToMainUser(persistenceData.loadUser(id));
	}

	public static void requestUpateMainUser(PersistenceData persistenceData) {
		Utilisateur utilisateur = USER_CONVERTER.toDataBase(TheoricDataBase.mainUser);
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

	public static void requestNearPlace(PersistenceData persistenceData) throws NoPlaceFoundException, SQLException {
		TheoricDataBase.getDataBase();
		ArrayList<Monument> monuments = (ArrayList<Monument>) persistenceData.loadMonument(mainUser.getPosition().getX(),
				mainUser.getPosition().getY(), searchRange);
		TheoricPlace thplace;
		for (Monument place : monuments) {
			thplace = MONUMENT_CONVERTER.fromDatabase(place);
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

	public static double getSearchRange() {
		return searchRange;
	}

	public static TheoricUser getFriend(String name) {
		return hashFriends.get(name);
	}

	public static void changeSearchRange(double range) {
		searchRange = range;
	}
}
