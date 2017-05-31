package api.gps;

import java.util.ArrayList;
import java.util.HashMap;

import api.data.TheoricPlace;
import api.data.TheoricUser;
import common.data.NoPlaceFoundException;
import common.data.VirtualDataBase;
import common.data.VirtualPlace;
import common.data.VirtualUser;
import tools.math.CoordinatesDouble;

public class TheoricDataBase {
	private static TheoricDataBase dataBase;
	public static TheoricUser mainUser;
	public static ArrayList<TheoricUser> friends = new ArrayList<TheoricUser>();

	public static ArrayList<TheoricPlace> places = new ArrayList<TheoricPlace>();
	private static HashMap<CoordinatesDouble, TheoricPlace> hashPlaces = new HashMap<CoordinatesDouble, TheoricPlace>();
	private static HashMap<String, TheoricUser> hashFriends = new HashMap<String, TheoricUser>();

	private TheoricDataBase(TheoricUser theoricUser) {
		mainUser = theoricUser;
	}

	public static TheoricDataBase getDataBase(TheoricUser main) {
		if (dataBase == null)
			dataBase = new TheoricDataBase(main);
		return dataBase;
	}

	public static void restartDataBase(TheoricUser main) {
		dataBase = new TheoricDataBase(main);
	}

	public static void requestFriends() {
		friends.clear();
		ArrayList<VirtualUser> virusers = VirtualDataBase.requestFriends();
		TheoricUser theoricUser;
		for (VirtualUser user : virusers) {
			theoricUser = new TheoricUser().fromVirtualUser(user);
			friends.add(theoricUser);
			hashFriends.put(theoricUser.getId(), theoricUser);
		}
	}

	public static void requestNearPlace() throws NoPlaceFoundException {
		places.clear();
		ArrayList<VirtualPlace> virplaces = VirtualDataBase.requestNearPlace(mainUser.getPosition());
		TheoricPlace thplace;
		for (VirtualPlace place : virplaces) {
			thplace = new TheoricPlace().fromVirtualPlace(place);
			places.add(thplace);
			hashPlaces.put(thplace.getCoords(), thplace);
		}
		if(places.isEmpty())throw new NoPlaceFoundException();
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

	public static TheoricUser get(String name) {
		return hashFriends.get(name);
	}

}
