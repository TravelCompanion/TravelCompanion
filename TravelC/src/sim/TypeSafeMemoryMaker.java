package sim;

import java.util.ArrayList;

import common.type.TypeConfiguration;
import tools.parse.StringParser;

public class TypeSafeMemoryMaker {
	
	private ArrayList<String> fileLines = new ArrayList<String>();
	
	public void jump(){
		fileLines.add("");
	}
	
	private void newAttribute(String name, String type,String visibility,String properties,String defaultValue ){
		String elt = Visibility.getName(visibility);
		if(properties != "")
			elt+=" "+properties;
		elt+=" "+type +" "+name;
		
		if(defaultValue != "")
			elt +=" = "+defaultValue;
		elt+=";";
		fileLines.add(elt);
	}
	
	public String buildAttribute(String type,String value){
		return "new "+type+""+value;
	}
	
	public static void main(String[] args){
		TypeSafeMemoryMaker typeSafeMemoryMaker = new TypeSafeMemoryMaker();
		TypeConfiguration.getConfig();
		ArrayList<String> lines = StringParser.readData("./data/typesMatrix.txt");
		String core  = "{"+'"'+TypeConfiguration.get(0).getName()+'"';
		for(int i = 1; i < TypeConfiguration.number;i++)
			core+= ","+'"'+TypeConfiguration.get(i).getName()+'"';
		core += "}";

		ArrayList<String> fileCode = new ArrayList<String>();
		typeSafeMemoryMaker.fileLines.add("package common.type;");
		typeSafeMemoryMaker.jump();
		typeSafeMemoryMaker.fileLines.add("public class TypeSafeMemory {");
		typeSafeMemoryMaker.jump();
		typeSafeMemoryMaker.newAttribute("memory","String[]", "+", "final", typeSafeMemoryMaker.buildAttribute("String[]", core));
		typeSafeMemoryMaker.newAttribute("typesRelation","String", "+", "final", '"'+lines.get(0)+'"');
		typeSafeMemoryMaker.jump();
		typeSafeMemoryMaker.fileLines.add("}");
		
		StringParser.writeData("./src/common/type/TypeSafeMemory.java", typeSafeMemoryMaker.fileLines);
	}

	public enum Visibility{
		PUBLIC("public"),PRIVATE("private"),PROTECTED("protected"),UNDEFINED("");
		private String txt;
		private Visibility(String txt){
			this.txt = txt;
		}
		public static Visibility get(String id){
			switch (id) {
			case "+": return PUBLIC;
			case "-": return PRIVATE;
			case "#": return PROTECTED;
			case ".": return UNDEFINED;
			default:
				 return UNDEFINED;
				 }
		}
		
		public static String getName(String id){
			switch (id) {
			case "+": return PUBLIC.txt;
			case "-": return PRIVATE.txt;
			case "#": return PROTECTED.txt;
			case ".": return UNDEFINED.txt;
			default:
				 return UNDEFINED.txt;
				 }
		}
	}
}
