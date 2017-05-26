package sim;

import java.util.HashMap;

import api.externalData.VirtualDataBase;
import api.externalData.VirtualUser;

public class Simulation {
		public static void main(String args[]){
			HashMap<String,VirtualUser> users = VirtualDataBase.getDataBase().getUsers();
			for(VirtualUser user : users.values())
				System.out.println(user);
		}		
}
