package sim;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import api.data.TheoricDataBase;
import api.data.TheoricPlace;
import api.data.TheoricUser;
import api.ia.IAManager;
import model.Monument;
import model.Utilisateur;
import persistence.PersistenceData;
import persistence.exception.NoPlaceFoundException;
import persistence.exception.NoUserFoundException;
import persistence.exception.YouHaveNoFriendsExeption;
import tools.math.CoordinatesDouble;
import tools.math.MathTools;
import tools.math.Matrix;
import tools.math.compare.CompareUnitDouble;
import tools.math.random.RandomInt;

public class TestLearn {
	public static void main(String[] args) {

		CoordinatesDouble coordinatesDouble = new CoordinatesDouble(new double[] { 49.0428886, 2.084052299 });
		PersistenceData persistenceData = new PersistenceData("root", "", "release1");

			try {
				TheoricDataBase.requestMainUser(persistenceData, 1);
				TheoricDataBase.mainUser.moveTo(coordinatesDouble);

			} catch (SQLException | NoUserFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			
		for (int k = 0; k < 5; k++) {
				TheoricDataBase.mainUser.moveTo(coordinatesDouble);
				try {
					TheoricDataBase.requestNearPlace(persistenceData);
					ArrayList<CompareUnitDouble<TheoricPlace>> places = IAManager.choosePlaces(TheoricDataBase.mainUser,
							TheoricDataBase.places);

					int i = 0;
					while (i <TheoricDataBase.mainUser.getVisitedRecently().size() &&  !TheoricDataBase.mainUser.getVisitedRecently().get(i).equals(places.get(i).getElement())) {
						i++;
					}

					TheoricPlace choice = IAManager.selectPlace(i, places);
					coordinatesDouble = persistenceData.positionMonument(choice.getId());
					TheoricDataBase.mainUser.getVisitedRecently().add(choice);
					int note = RandomInt.generate(5, 0);
					System.out.println(choice.getName()+" "+choice.getDistance());
					System.out.println("pref : " + Matrix.trans(TheoricDataBase.mainUser.getPreferences()));
					IAManager.learn(TheoricDataBase.mainUser, choice, note);
					System.out.println(
							"Give note : " + note + " expected note : " + MathTools.roundAt(places.get(0).getValue() * 5, 3)
									+ " updated pref : " + Matrix.trans(TheoricDataBase.mainUser.getPreferences()));
				} catch (NoPlaceFoundException e) {
					coordinatesDouble = new CoordinatesDouble(new double[] { 49.0428886, 2.084052299 });
					System.out.println("no place");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
		}
	}
	
}
