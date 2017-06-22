package com.example.voyage.api.sim;

import java.sql.SQLException;
import java.util.ArrayList;

import com.example.voyage.api.api.data.TheoricDataBase;
import com.example.voyage.api.api.data.TheoricPlace;
import com.example.voyage.api.api.ia.IAManager;
import com.example.voyage.api.persistence.PersistenceData;
import com.example.voyage.api.persistence.exception.NoPlaceFoundException;
import com.example.voyage.api.persistence.exception.NoUserFoundException;
import com.example.voyage.api.tools.math.CoordinatesDouble;
import com.example.voyage.api.tools.math.MathTools;
import com.example.voyage.api.tools.math.Matrix;
import com.example.voyage.api.tools.math.compare.CompareUnitDouble;
import com.example.voyage.api.tools.math.random.RandomInt;

public class TestMicro {
	public static void main(String[] args){

		CoordinatesDouble coordinatesDouble = new CoordinatesDouble(new double[] { 49.0428886, 2.084052299 });
		PersistenceData persistenceData = new PersistenceData("root", "", "release1");

			try {
				TheoricDataBase.requestMainUser(persistenceData, 1);
				TheoricDataBase.mainUser.moveTo(coordinatesDouble);

			} catch (SQLException | NoUserFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	
			

				TheoricDataBase.mainUser.moveTo(coordinatesDouble);
				try {
					TheoricDataBase.requestNearPlace(persistenceData);
					System.out.println(TheoricDataBase.places.size());
					ArrayList<CompareUnitDouble<TheoricPlace>> places = IAManager.choosePlaces(TheoricDataBase.mainUser,
							TheoricDataBase.places);

					int i = 0;
					while (TheoricDataBase.mainUser.getVisitedRecently().contains(places.get(i).getElement())) {
						i++;
					}

					TheoricPlace choice = IAManager.selectPlace(i, places);
					coordinatesDouble = persistenceData.positionMonument(choice.getId());
					TheoricDataBase.mainUser.hasVisited(choice);

					System.out.println(choice.getName()+" "+choice.getDistance());
					System.out.println("pref : " + Matrix.trans(TheoricDataBase.mainUser.getPreferences()));
					IAManager.shortLearn(TheoricDataBase.mainUser, choice);
					System.out.println(" pref : " + Matrix.trans(TheoricDataBase.mainUser.getPreferences()));
				} catch (NoPlaceFoundException e) {
					coordinatesDouble = new CoordinatesDouble(new double[] { 49.0428886, 2.084052299 });
					System.out.println("no place");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
		
	}
}
