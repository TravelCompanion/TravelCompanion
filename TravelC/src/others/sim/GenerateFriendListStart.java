package others.sim;

import common.data.VirtualDataBase;
import others.generators.UserGenerator;

public class GenerateFriendListStart {
	
		public static void main(String args[]){
			VirtualDataBase.getDataBase();
			UserGenerator.generateLinks(VirtualDataBase.users);
			System.out.println(VirtualDataBase.getUser("User15").getFriendList());
			
		}
		
}
