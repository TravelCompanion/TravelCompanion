package api.externalData.generators;

import java.util.ArrayList;
import java.util.HashMap;

import api.cte.PlaceType;
import api.externalData.VirtualUser;
import tools.math.CoordinatesDouble;

public class UserGenerator extends DataGenerator{
	public static ArrayList<VirtualUser>generateUserList(int numberOfUsers){
		ArrayList<VirtualUser> users = new ArrayList<VirtualUser>();
		for(int i = 0; i < numberOfUsers; i ++)
			users.add(generateUser(i+1));
		return users;
	}
	
	public static VirtualUser generateUser(int number){
		String name = generateName("User",number);
		CoordinatesDouble position = generatePosition();
		HashMap<PlaceType, Double> preferences = generatePreferences();
		return new VirtualUser(name,position,preferences);
	}
	
}
