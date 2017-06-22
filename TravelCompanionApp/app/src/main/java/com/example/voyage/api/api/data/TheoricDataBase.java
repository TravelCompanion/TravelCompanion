package com.example.voyage.api.api.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.voyage.api.common.convertion.ia.bdd.TheoricPlaceConvertionDB;
import com.example.voyage.api.common.convertion.ia.bdd.TheoricUserConvertionDB;
import com.example.voyage.api.common.type.TypeConfiguration;
import com.example.voyage.api.common.type.TypeSafeMemory;
import com.example.voyage.api.model.Monument;
import com.example.voyage.api.model.Utilisateur;
import com.example.voyage.api.persistence.PersistenceData;
import com.example.voyage.api.persistence.exception.NoPlaceFoundException;
import com.example.voyage.api.persistence.exception.NoUserFoundException;
import com.example.voyage.api.persistence.exception.YouHaveNoFriendsExeption;
import com.example.voyage.api.tools.math.CoordinatesDouble;

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
	public static final TheoricUserConvertionDB USER_CONVERTER = new TheoricUserConvertionDB();
	public static final TheoricPlaceConvertionDB MONUMENT_CONVERTER = new TheoricPlaceConvertionDB();

	private TheoricDataBase() {
		TypeConfiguration.getConfig(new TypeSafeMemory());
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
		mainUser = USER_CONVERTER.convertFromToMainUser(persistenceData.User(id));
	}

	public static void requestUpateMainUser(PersistenceData persistenceData) {
		/** request for update the data of the main user in the database */
		//Utilisateur utilisateur = USER_CONVERTER.convertTo(TheoricDataBase.mainUser);
		//persistenceData.updateUserPref(utilisateur);
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
		places.clear();
		hashPlaces.clear();
		ArrayList<Monument> monuments = (ArrayList<Monument>) persistenceData
				.persisteMonument(mainUser.getPosition().getX(), mainUser.getPosition().getY());
		TheoricPlace thplace;
		for (Monument place : monuments) {
			thplace = MONUMENT_CONVERTER.convertFrom(place);
			places.add(thplace);
			hashPlaces.put(thplace.getCoords(), thplace);
		}
	}

	public static void newMainUser(Utilisateur user){
		mainUser = (TheoricMainUser) USER_CONVERTER.convertFromToMainUser(user);
	}

	public static void newClosePlaces(ArrayList<Monument> monuments){
		ArrayList<TheoricPlace> theoricPlaces = new ArrayList<TheoricPlace>();
		for(Monument monument : monuments)
			theoricPlaces.add(MONUMENT_CONVERTER.convertFrom(monument));
		places = theoricPlaces;
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
