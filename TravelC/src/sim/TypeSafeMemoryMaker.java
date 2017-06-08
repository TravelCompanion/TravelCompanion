package sim;

import java.util.ArrayList;

import common.type.TypeConfiguration;
import tools.parse.StringParser;

public class TypeSafeMemoryMaker {
	public static void main(String[] args){
		TypeConfiguration.getConfig();
		
		String core  = "public final String[] memory = new String[]{"+'"'+TypeConfiguration.get(0).getName()+'"';
		for(int i = 1; i < TypeConfiguration.number;i++)
			core+= ","+'"'+TypeConfiguration.get(i).getName()+'"';
		core += "};";

		ArrayList<String> fileCode = new ArrayList<String>();
		fileCode.add("package common.type;");
		fileCode.add("");
		fileCode.add("public class TypeSafeMemory {");
		fileCode.add("");
		fileCode.add(core);
		fileCode.add("");
		fileCode.add("}");
		
		StringParser.writeData("./src/common/type/TypeSafeMemory.java", fileCode);
	}
}
