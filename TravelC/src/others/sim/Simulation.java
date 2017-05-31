package others.sim;

import java.util.ArrayList;
import java.util.Random;

import api.data.TheoricPlace;
import api.data.TheoricUser;
import api.gps.TheoricDataBase;
import api.ia.IAManager;
import common.data.NoPlaceFoundException;
import common.data.VirtualDataBase;
import common.type.TypeConfiguration;
import others.generators.DataGenerator;
import tools.math.Matrix;
import tools.math.compare.CompareUnitDouble;
import tools.math.random.RandomInt;

public class Simulation {
	@SuppressWarnings("static-access")
	public static void main(String args[]) {
		TypeConfiguration.getConfig();
		System.out.println(TypeConfiguration.number);;
		Random rand = new Random();
		TheoricDataBase.getDataBase(new TheoricUser().fromVirtualUser(VirtualDataBase.getDataBase().getUser(0)));
		int numbeIt = 100;
		for(int i = 0; i < numbeIt ;i++){
		
			try {
				TheoricDataBase.requestNearPlace();
			} catch (NoPlaceFoundException e) {
				TheoricDataBase.mainUser.moveTo(DataGenerator.generatePosition());
				System.out.println("Diantre");
			}
		ArrayList<CompareUnitDouble<TheoricPlace>> list = IAManager.choosePlaces(TheoricDataBase.mainUser,
				TheoricDataBase.places);
		TheoricPlace place =IAManager.selectPlace(0, list); 
		
		
		double note = RandomInt.generate(rand,5,1);
		
		IAManager.learn(TheoricDataBase.mainUser, place, note);
		TheoricDataBase.mainUser.updatePreferences(IAManager.ia.getWeights());
		System.out.println(Matrix.trans(TheoricDataBase.mainUser.getPreferences()));
		TheoricDataBase.mainUser.moveTo(place.getCoords());
		}
		

	}
}
