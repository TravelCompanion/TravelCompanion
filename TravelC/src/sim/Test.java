package sim;

import java.sql.SQLException;
import java.util.ArrayList;

import api.data.TheoricPlace;
import api.gps.TheoricDataBase;
import api.ia.IAManager;
import common.data.NoPlaceFoundException;
import common.data.NoUserFoundException;
import persistence.PersistenceData;
import tools.math.CoordinatesDouble;
import tools.math.compare.CompareUnitDouble;
import tools.math.random.RandomDouble;

public class Test {
	public static void main(String[] args){
		PersistenceData persistenceData = new PersistenceData();
		try {
			CoordinatesDouble defaultPos = new CoordinatesDouble(new double[]{49.0429711,2.0845123000000285});
			TheoricDataBase.requestMainUser(persistenceData, 1);
			TheoricDataBase.mainUser.moveTo(defaultPos);
			TheoricDataBase.requestNearPlace(persistenceData);
			System.out.println(TheoricDataBase.places);
			ArrayList<CompareUnitDouble<TheoricPlace>> list= IAManager.choosePlaces(TheoricDataBase.mainUser, TheoricDataBase.places);
			double rnd = RandomDouble.generate(5,1);
			IAManager.learn(TheoricDataBase.mainUser,list.get(0).getElement(), rnd);
			TheoricDataBase.mainUser.updatePref(IAManager.ia.getWeights());
			TheoricDataBase.requestUpateMainUser(persistenceData);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoUserFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoPlaceFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
