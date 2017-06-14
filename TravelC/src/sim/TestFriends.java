package sim;

import java.sql.SQLException;
import java.util.ArrayList;

import api.data.TheoricDataBase;
import api.data.TheoricPlace;
import api.data.TheoricUser;
import api.ia.IAManager;
import model.Utilisateur;
import persistence.PersistenceData;
import persistence.exception.NoPlaceFoundException;
import persistence.exception.NoUserFoundException;
import persistence.exception.YouHaveNoFriendsExeption;
import tools.math.CoordinatesDouble;
import tools.math.MathTools;
import tools.math.compare.CompareUnitDouble;

public class TestFriends {
	public static void main(String[] args){
		CoordinatesDouble coordinatesDouble = new CoordinatesDouble(new double[]{49.0428886, 2.084052299});
		PersistenceData persistenceData = new PersistenceData("root","","release1");
		try {
			Utilisateur utilisateur = persistenceData.User(1);
			ArrayList<Utilisateur> utilisateurs = (ArrayList<Utilisateur>) persistenceData.persisteAmis(1);
			System.out.println(utilisateur);
			System.out.println(utilisateurs);
			TheoricDataBase.requestMainUser(persistenceData, 1);
			TheoricDataBase.mainUser.moveTo(coordinatesDouble);

			TheoricDataBase.requestFriends(persistenceData);
			TheoricDataBase.requestNearPlace(persistenceData);
			for(TheoricUser user : TheoricDataBase.friends)
			System.out.println(user);
			
			ArrayList<CompareUnitDouble<TheoricPlace>> places = IAManager.friendsChoice(TheoricDataBase.friends, TheoricDataBase.places);
			for(CompareUnitDouble<TheoricPlace> placeData : places)
			System.out.println("place name : "+placeData.getElement().getName()+"\nresult : "+MathTools.roundAt(placeData.getValue(), 3));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoUserFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YouHaveNoFriendsExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoPlaceFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
