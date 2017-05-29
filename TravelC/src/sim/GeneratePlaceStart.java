package sim;

import java.util.ArrayList;

import api.cte.Constants;
import api.externalData.VirtualPlace;
import api.externalData.generators.PlaceGenerator;
import tools.parse.StringParser;

public class GeneratePlaceStart {
	public static void main(String[] args){
		ArrayList<VirtualPlace> places = PlaceGenerator.generatePlaceList(200);
		ArrayList<String> lines = new ArrayList<String>();
		
		for(int i = 0; i < places.size(); i++)
			lines.add(places.get(i).toLog());
		System.out.println(places);
		System.out.println(lines);
		StringParser.writeData(Constants.DB_PATH_PLACES,lines);
		//TheoricUser theoricUser = new TheoricUser().fromVirtualUser(new VirtualUser());
	} 
}
