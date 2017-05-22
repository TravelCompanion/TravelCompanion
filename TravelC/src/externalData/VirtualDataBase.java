package externalData;

import java.util.ArrayList;
import java.util.HashMap;

import tools.math.CoordinatesDouble;
import tools.parse.StringParser;
import cte.Constants;
import ia.TheoricUser;

public class VirtualDataBase {
	private static VirtualDataBase dataBase;
	private HashMap<CoordinatesDouble, VirtualPlace> places = new HashMap<CoordinatesDouble, VirtualPlace>(); 
	private HashMap<String, VirtualUser> users = new HashMap<String, VirtualUser>();
	
	
	private VirtualDataBase(){
		//data format : name,t1/t2/t3 ... ,x,y
		ArrayList<String> lines = StringParser.readData(Constants.DB_PATH_PLACES);
		//ArrayList<String> lines2 = StringParser.readData(Constants.DB_PATH_USER);
		//places = StringParser.convertDataLinesKeyMap(new TempDataDB(), lines,',',';');
		places = StringParser.convertDataLinesKeyMap(new VirtualPlace(), lines,'/',';');
		//users = StringParser.convertDataLinesKeyMap(new VirtualUser(), lines2, ',',';');
		
	}
	
	public static VirtualDataBase getDataBase() {
		if(dataBase == null) dataBase = new VirtualDataBase();
		return dataBase;
	}
	public HashMap<CoordinatesDouble, VirtualPlace> getPlaces() {
		return places;
	}
	
	public HashMap<String, VirtualUser> getUsers() {
		return users;
	}

	public HashMap<CoordinatesDouble, VirtualPlace> requestNearPlace(TheoricUser user,double distance){
		HashMap<CoordinatesDouble, VirtualPlace> req = new HashMap<CoordinatesDouble, VirtualPlace>();
		for(CoordinatesDouble data : places.keySet())
			if(CoordinatesDouble.distanceAtoB(user.getPosition(),data) <= distance)
				req.put(data,places.get(data));
		return req;
	}
	//otherRequest...
	
}
