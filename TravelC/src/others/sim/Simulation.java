package others.sim;

import java.util.HashMap;

import common.data.VirtualDataBase;
import common.data.VirtualUser;

public class Simulation {
		public static void main(String args[]){
			HashMap<String,VirtualUser> users = VirtualDataBase.getDataBase().getUsers();
			for(VirtualUser user : users.values())
				System.out.println(user);
		}		
}
