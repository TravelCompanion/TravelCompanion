package others.sim;

import java.util.ArrayList;

import common.data.Constants;
import common.data.VirtualUser;
import common.type.TypeConfiguration;
import others.generators.UserGenerator;
import tools.parse.StringParser;

public class GenerateUserStart {
	public static void main(String args[]){
		TypeConfiguration.getConfig();
		ArrayList<VirtualUser> users = UserGenerator.generateUserList(50);
		ArrayList<String> lines = new ArrayList<String>();
		
		for(int i = 0; i < users.size(); i++)
			lines.add(users.get(i).toLog());
		System.out.println(users);
		System.out.println(lines);
		StringParser.writeData(Constants.DB_PATH_USER,lines);
	}
}
