package common.data;

import java.util.ArrayList;
import java.util.HashMap;

import common.data.Constants;
import common.type.TypeConfiguration;
import tools.math.CoordinatesDouble;
import tools.parse.StringParser;

public class VirtualDataBase {
	private static VirtualDataBase dataBase;
	public static ArrayList<String> users = new ArrayList<String>();
	public static ArrayList<String> places = new ArrayList<String>();
	private static HashMap<CoordinatesDouble, VirtualPlace> placesHash = new HashMap<CoordinatesDouble, VirtualPlace>(); 
	private static HashMap<String, VirtualUser> usersHash = new HashMap<String, VirtualUser>();
	
	
	private VirtualDataBase(){
		//data format : name,t1/t2/t3 ... ,x,y
		TypeConfiguration.getConfig();
		ArrayList<String> lines = StringParser.readData(Constants.DB_PATH_PLACES);
		ArrayList<String> lines2 = StringParser.readData(Constants.DB_PATH_USER);
		for(String line2 : lines2)
			users.add(StringParser.sliceLine(line2, ',').get(0));
		for(String line : lines)
			places.add(StringParser.sliceLine(line, ',').get(0));
		//places = StringParser.convertDataLinesKeyMap(new TempDataDB(), lines,',',';');
		placesHash = StringParser.convertDataLinesKeyMap(new VirtualPlace(), lines,'/',';');
		usersHash = StringParser.convertDataLinesKeyMap(new VirtualUser(), lines2, ',',';');	
	}
	
	public static VirtualDataBase getDataBase() {
		if(dataBase == null) dataBase = new VirtualDataBase();
		return dataBase;
	}
	
	public HashMap<String, VirtualUser> getUsers() {
		return usersHash;
	}
	
	public static VirtualUser getUser(int id){
		return usersHash.get(users.get(id));
	}
	
	public static VirtualUser getUser(String name){
		return usersHash.get(name);
	}
	
	public HashMap<CoordinatesDouble, VirtualPlace> requestNearPlace(VirtualUser user,double distance){
		HashMap<CoordinatesDouble, VirtualPlace> req = new HashMap<CoordinatesDouble, VirtualPlace>();
		for(CoordinatesDouble data : placesHash.keySet())
			if(CoordinatesDouble.distanceAtoB(user.getPosition(),data) <= distance)
				req.put(data,placesHash.get(data));
		return req;
	}
	//otherRequest...

	public static ArrayList<VirtualPlace> requestNearPlace(CoordinatesDouble pos) {
		ArrayList<VirtualPlace> virtualPlaces = new ArrayList<VirtualPlace>();
		for(VirtualPlace place : placesHash.values())
			if(CoordinatesDouble.distanceAtoB(pos, place.getPosition()) < Constants.SCAN_SIZE)
				virtualPlaces.add(place);
		return virtualPlaces;
	}

	public static ArrayList<VirtualUser> requestFriends() {
		// TODO Auto-generated method stub
		return null;
	}


}
