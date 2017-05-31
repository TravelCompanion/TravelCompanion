package others.sim;

import java.util.ArrayList;

import common.data.Constants;
import common.data.VirtualDataBase;
import others.generators.UserGenerator;
import tools.parse.StringParser;

public class GenerateFriendListStart {
	
		public static void main(String args[]){
			VirtualDataBase.getDataBase();
			UserGenerator.generateLinks(VirtualDataBase.users);
			System.out.println(VirtualDataBase.getUser("User15").getFriendList());
			ArrayList<String> lines = new ArrayList<String>();
			for(String user : VirtualDataBase.users)
				lines.add(VirtualDataBase.getUser(user).toLog());
			StringParser.writeData(Constants.DB_PATH_USER,lines);
			
		}
		
}
