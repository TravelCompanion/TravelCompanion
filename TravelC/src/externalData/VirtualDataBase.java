package externalData;

import java.util.ArrayList;
import java.util.HashMap;

import tools.math.CoordinatesDouble;
import tools.parse.StringParser;
import cte.Constants;
import ia.TheoricUser;

public class VirtualDataBase {
	private static VirtualDataBase dataBase;
	private HashMap<CoordinatesDouble, TempDataDB> places = new HashMap<CoordinatesDouble, TempDataDB>(); 
	private HashMap<String, VirtualUser> users = new HashMap<String, VirtualUser>();
	
	
	private VirtualDataBase(){
		//data format : name,t1/t2/t3 ... ,x,y
		ArrayList<String> lines = StringParser.readData(Constants.DB_PATH_PLACES);
		ArrayList<String> lines2 = StringParser.readData(Constants.DB_PATH_USER);
		places = StringParser.convertDataLinesKeyMap(new TempDataDB(), lines,',',';');
		users = StringParser.convertDataLinesKeyMap(new VirtualUser(), lines2, ',',';');
		
	}
	
	public static VirtualDataBase getDataBase() {
		if(dataBase == null) dataBase = new VirtualDataBase();
		return dataBase;
	}
	public HashMap<CoordinatesDouble, TempDataDB> getPlaces() {
		return places;
	}
	
	public HashMap<String, VirtualUser> getUsers() {
		return users;
	}

	public HashMap<CoordinatesDouble, TempDataDB> requestNearPlace(TheoricUser user,double distance){
		HashMap<CoordinatesDouble, TempDataDB> req = new HashMap<CoordinatesDouble, TempDataDB>();
		for(CoordinatesDouble data : places.keySet())
			if(CoordinatesDouble.distanceAtoB(user.getPosition(),data) <= distance)
				req.put(data,places.get(data));
		return req;
	}
	//otherRequest...
	
}
