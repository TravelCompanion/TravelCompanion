package api.externalData.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import api.cte.PlaceType;
import api.externalData.VirtualDataBase;
import api.externalData.VirtualUser;
import tools.math.CoordinatesDouble;
import tools.math.Matrix;
import tools.math.random.RandomInt;

public class UserGenerator extends DataGenerator{
	public static ArrayList<VirtualUser>generateUserList(int numberOfUsers){
		ArrayList<VirtualUser> users = new ArrayList<VirtualUser>();
		for(int i = 0; i < numberOfUsers; i ++)
			users.add(generateUser(i+1)); 
		return users;
	}
	
	private static Matrix generateLinkMatrix(ArrayList<String> users) {
		Matrix m = new Matrix(users.size(),users.size());
		Random rand = new Random();
		for(int x = 0; x < m.sizeX/2; x++)
			for(int y = 0; y< m.sizeY/2;y++){
				m.getMatrix()[x][y] = RandomInt.generate(rand,1, 0);
				m.getMatrix()[y][x] = m.getMatrix()[x][y];
			}
		for(int k = 0; k < users.size(); k++)
			m.getMatrix()[k][k] = 0;
		return m;
	}
	
	public static void generateLinks(ArrayList<String> users) {
		Matrix m = generateLinkMatrix(users);
		for(int x = 0; x < m.sizeX;x++)
			for(int y = 0; y < m.sizeY;y++)
				if(m.getMatrix()[x][y] == 1)
					VirtualDataBase.getUser(x).newFriend(VirtualDataBase.getUser(y));
	}
	
	public static VirtualUser generateUser(int number){
		String name = generateName("User",number);
		CoordinatesDouble position = generatePosition();
		HashMap<PlaceType, Double> preferences = generatePreferences();
		return new VirtualUser(name,position,preferences);
	}
	
}
