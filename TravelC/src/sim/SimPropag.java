package sim;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.type.TypeConfiguration;
import common.type.TypeSafeMemory;
import model.Monument;
import persistence.PersistenceData;
import persistence.exception.NoPlaceFoundException;
import persistence.exception.NoUserFoundException;

public class SimPropag {
	public static void main(String[] args){
		PersistenceData persistenceData = new PersistenceData("root","","release1");
		TypeConfiguration.getConfig(new TypeSafeMemory());
		List<Monument> m;
		
		try {
			m = Propag.progation(persistenceData.User(1), (ArrayList<Monument>)persistenceData.persisteMonument(49.0428886, 2.084052299));
			System.out.println(m.size());
			for(Monument monu : m)
				System.out.println(monu);
		
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
