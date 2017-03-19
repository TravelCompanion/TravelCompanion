package externalData;

import java.util.ArrayList;
import java.util.HashMap;

import tools.math.CoordinatesDouble;
import tools.parse.StringParser;
import cte.Constants;
import ia.TheoricUser;

public class VirtualDataBase {
	private static VirtualDataBase dataBase;
	private HashMap<CoordinatesDouble, TempDataDB> datas = new HashMap<CoordinatesDouble, TempDataDB>(); 
	private VirtualDataBase(){
		//data format : name,t1/t2/t3 ... ,x,y
		ArrayList<String> lines = StringParser.readData(Constants.DB_PATH);
		datas = StringParser.convertDataLinesKeyMap(new TempDataDB(), lines,',',';');
	}
	
	public static VirtualDataBase getDataBase() {
		if(dataBase == null) dataBase = new VirtualDataBase();
		return dataBase;
	}
	public HashMap<CoordinatesDouble, TempDataDB> getDatas() {
		return datas;
	}
	
	public HashMap<CoordinatesDouble, TempDataDB> requestNearPlace(TheoricUser user,double distance){
		HashMap<CoordinatesDouble, TempDataDB> req = new HashMap<CoordinatesDouble, TempDataDB>();
		for(CoordinatesDouble data : datas.keySet())
			if(CoordinatesDouble.distanceAtoB(user.getPosition(),data) <= distance)
				req.put(data,datas.get(data));
		return req;
	}
	//otherRequest...
	
}
