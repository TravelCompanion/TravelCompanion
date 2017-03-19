package sim;

import javax.security.sasl.SaslClient;

import cte.Constants;
import externalData.VirtualDataBase;
import gps.GPSMap;
import ia.IAManager;
import ia.TheoricUser;
import tools.math.CoordinatesDouble;

public class Simulation {
	public static void main(String[] args) {
		TheoricUser user = new TheoricUser();
		GPSMap.getMap();
		VirtualDataBase virtualDataBase = VirtualDataBase.getDataBase();
		
		CoordinatesDouble next;
		
		double note;
		for(int i = 0; i < 20; i++){
			GPSMap.fillMap(virtualDataBase.requestNearPlace(user, Constants.SCAN_SIZE));
			System.out.println(GPSMap.placeInRange() + " " + GPSMap.getMap().toString());
			next = IAManager.choosePlace(user, GPSMap.getMap());
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
