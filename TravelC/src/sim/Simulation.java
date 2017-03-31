package sim;

import java.util.ArrayList;
import java.util.HashMap;

import cte.Constants;
import externalData.VirtualDataBase;
import externalData.VirtualUser;
import gps.GPSMap;
import ia.IAManager;
import ia.TheoricUser;
import tools.math.CoordinatesDouble;
import tools.parse.StringParser;

public class Simulation {
	public static void main(String[] args) {
		
		/*HashMap<String, VirtualUser> users= VirtualDataBase.getDataBase().getUsers();
		ArrayList<String> lines = new ArrayList<String>();
		for(VirtualUser user : users.values())
			lines.add(user.toLog());
		System.out.println(users);
		System.out.println(users.size()+" "+lines.size());
		StringParser.writeData(Constants.DB_PATH_USERDEST, lines);
		*/
		TheoricUser user = new TheoricUser();
		GPSMap.getMap();
		VirtualDataBase virtualDataBase = VirtualDataBase.getDataBase();
		
		CoordinatesDouble next;
		
		double note;
		for(int i = 0; i < 20; i++){
			System.out.println("\niteration : "+(i+1));
			GPSMap.fillMap(virtualDataBase.requestNearPlace(user, Constants.SCAN_SIZE));
			System.out.println(GPSMap.placeInRange() + " : " + GPSMap.getMap().toString());
			next = IAManager.choosePlace(user);
			user.moveTo(next);
			note = IAManager.notePlace(user, GPSMap.getPlaceAt(next)) / Constants.MAX_NOTE;
			user.visitPlace(next);
			System.out.println(note);
			System.out.println(user);
			IAManager.learn(user, GPSMap.getPlaceAt(next), note, 0.2);
			System.out.println(user);
		}
		
	}

}
