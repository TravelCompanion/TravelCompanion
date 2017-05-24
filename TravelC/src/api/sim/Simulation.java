package api.sim;


import api.cte.Constants;
import api.externalData.VirtualDataBase;
import api.gps.GPSMap;
import api.gps.NoPlaceFoundException;
import api.ia.IAManager;
import api.ia.TheoricUser;
import tools.math.CoordinatesDouble;

public class Simulation {
	public static void main(String[] args) {

		/*
		 * HashMap<String, VirtualUser> users=
		 * VirtualDataBase.getDataBase().getUsers(); ArrayList<String> lines =
		 * new ArrayList<String>(); for(VirtualUser user : users.values())
		 * lines.add(user.toLog()); System.out.println(users);
		 * System.out.println(users.size()+" "+lines.size());
		 * StringParser.writeData(Constants.DB_PATH_USERDEST, lines);
		 */
		/**
		 * Simulate for numberIteration iterations the behavior of user on a virtual map and
		 * screen the evolution of its profile
		 */
		TheoricUser user = new TheoricUser();
		GPSMap.getMap();
		VirtualDataBase virtualDataBase = VirtualDataBase.getDataBase();

		CoordinatesDouble next;

		double note;
		int numberIteration = 1;
		for (int i = 0; i < numberIteration; i++) {
			System.out.println("\niteration : " + (i + 1));
			try {
				GPSMap.fillMap(virtualDataBase.requestNearPlace(user, Constants.SCAN_SIZE));
			} catch (NoPlaceFoundException e) {
				e.printStackTrace();
			}
			System.out.println(GPSMap.placeInRange() + " : " + GPSMap.getMap().toString());
			//System.out.println(user);
			next = IAManager.choosePlace(user);
			System.out.println(IAManager.chooseListOfPlace(user));
			user.moveTo(next);
			note = IAManager.notePlace(user, GPSMap.getPlaceAt(next)) / Constants.MAX_NOTE;
			user.visitPlace(next);
			System.out.println(GPSMap.getPlaceAt(next));
			System.out.println(note);
			System.out.println(user);
			IAManager.learn(user, GPSMap.getPlaceAt(next), note, 0.1);
			//IAManager.perceptronLearn(user, GPSMap.getPlaceAt(next), note, 0.2);
			//voir contrainte à réaliser.
			System.out.println(user);
		}
	}

}
