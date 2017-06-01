package persistence;

import java.io.IOException;
import java.sql.SQLException;

import model.Monument;

public class TestJDBC {

	

	public static void main(String[] args) throws SQLException, IOException {

		// System.out.print(PersistenceData.VerifierUtilisateur("cc", "sa",
		// "ss"));
		
//		Parametre a = new Parametre("root","zinebtazi", "release1");
//		DataBase db = new DataBase(Parametre.Host, Parametre.username,
//				Parametre.password, Parametre.IPHOST, Parametre.Port);
//		
//		ResultSet Res;
//
//		Res = db.executionQuery("SELECT * FROM musee WHERE haversine_distance(latitude, longitude,49.036407,2.0760956) < 10 limit 3");
//
//		while (Res.next()) {
//			System.out.println(Res.getString(2));
//
//		}
		
//		 List<Musee> musees = new ArrayList<Musee>();
//		
//		musees=PersistenceData.persisteMusee(49.036407,2.0760956);
//		
//		for(Musee m:musees){
//			System.out.println(m.getDistance());
//		}
		
		PersistenceData p = new PersistenceData("root", "zinebtazi", "release1");
		
		
//
	}
	
	

}
