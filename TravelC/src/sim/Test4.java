package sim;

import java.sql.SQLException;

import api.data.TheoricDataBase;
import common.data.NoUserFoundException;
import common.data.YouHaveNoFriendsExeption;
import persistence.PersistenceData;

public class Test4 {
	public static void main(String[] args) throws NoUserFoundException{
		PersistenceData persistenceData = new PersistenceData();
		TheoricDataBase.getDataBase();
		try {
			TheoricDataBase.requestMainUser(persistenceData, 1);
			TheoricDataBase.requestFriends(persistenceData);
			System.out.println(TheoricDataBase.friends);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (YouHaveNoFriendsExeption e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
