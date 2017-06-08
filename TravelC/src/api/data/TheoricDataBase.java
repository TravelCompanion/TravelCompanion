package api.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import common.convertion.ia.bdd.TheoricPlaceConvertionDB;
import common.convertion.ia.bdd.TheoricUserConvertionDB;
import common.data.NoPlaceFoundException;
import common.data.NoUserFoundException;
import common.data.YouHaveNoFriendsExeption;
import common.type.TypeConfiguration;
import model.Monument;
import model.Utilisateur;
import persistence.PersistenceData;
import tools.math.CoordinatesDouble;

public class TheoricDataBase {
	/**
	 * The class manage the data used by the AI and manage the different /
	 * request to the others part
	 */
	private static TheoricDataBase dataBase;
	private static double searchRange = 20;
	public static TheoricMainUser mainUser;
	/** this attribute represent the user currently logged */
	public static ArrayList<TheoricUser> friends = new ArrayList<TheoricUser>();
	/** contains the list of the main users friends */
	private static HashMap<String, TheoricUser> hashFriends = new HashMap<String, TheoricUser>();

	public static ArrayList<TheoricPlace> places = new ArrayList<TheoricPlace>();
	/** contains the list of the places nearby the main user */
	private static HashMap<CoordinatesDouble, TheoricPlace> hashPlaces = new HashMap<CoordinatesDouble, TheoricPlace>();
	private static final TheoricUserConvertionDB USER_CONVERTER = new TheoricUserConvertionDB();
	private static final TheoricPlaceConvertionDB MONUMENT_CONVERTER = new TheoricPlaceConvertionDB();

	private TheoricDataBase() {
		TypeConfiguration.getConfig();
	}

	public static TheoricDataBase getDataBase() {
		if (dataBase == null)
			dataBase = new TheoricDataBase();
		return dataBase;

	}

	public static void restartDataBase() {
		/** this method is for reinitialize the database */
		dataBase = new TheoricDataBase();
	}

	public static void requestMainUser(PersistenceData persistenceData, int id)
			throws SQLException, NoUserFoundException {
		/**
		 * request to load the data of user with the corresponding id to the
		 * database and set it as main user
		 */
		TheoricDataBase.getDataBase();
		mainUser = USER_CONVERTER.convertFromToMainUser(persistenceData.loadUser(id));
	}

	public static void requestUpateMainUser(PersistenceData persistenceData) {
		/** request for update the data of the main user in the database */
		Utilisateur utilisateur = USER_CONVERTER.convertTo(TheoricDataBase.mainUser);
		persistenceData.updateUserPref(utilisateur);
	}

	public static void requestFriends(PersistenceData persistenceData) throws SQLException, YouHaveNoFriendsExeption {
		TheoricDataBase.getDataBase();
		friends.clear();
		ArrayList<Utilisateur> datas = (ArrayList<Utilisateur>) persistenceData.persisteAmis(mainUser.getId());
		TheoricUser theoricUser;
		for (Utilisateur user : datas) {
			theoricUser = USER_CONVERTER.convertFrom(user);
			friends.add(theoricUser);
			hashFriends.put(theoricUser.getUserName(), theoricUser);
		}
	}

	public static void requestNearPlace(PersistenceData persistenceData) throws NoPlaceFoundException, SQLException {
		/** request to get the places nearby the user */
		TheoricDataBase.getDataBase();
		ArrayList<Monument> monuments = (ArrayList<Monument>) persistenceData
				.loadMonument(mainUser.getPosition().getX(), mainUser.getPosition().getY(), searchRange);
		TheoricPlace thplace;
		for (Monument place : monuments) {
			thplace = MONUMENT_CONVERTER.convertFrom(place);
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
