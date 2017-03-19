package tools.parse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class StringParser {
	/**
	 * Class for parse a String, convert it into object,read file, convert
	 * object into String, write file
	 */
	// Log Operations
	public static ArrayList<String> logDatas(HashMap<String, StringParseLoggable> datas) {
		ArrayList<String> words = new ArrayList<String>();
		for (StringParseLoggable data : datas.values())
			words.add(data.toLog());
		return words;
	}

	// Generation Operations
	public static <T1, T2> T1 generateFromParse(StringParseGenerable<T1, T2> generator,String line, char separator,
			char endOfLine) {
		ArrayList<String> args = sliceLine(line, separator, endOfLine);
		T1 item = generator.generateItem(args);
		return item;
	}

	public static <T1, T2> T1 generateFromParse(StringParseGenerable<T1, T2> generator, String line, char separator) {
		ArrayList<String> args = sliceLine(line, separator);
		T1 item = generator.generateItem(args);
		return item;
	}

	public static <T1, T2> HashMap<String, T1> convertDataLinesStringMap(StringParseGenerable<T1, T2> generable,
			ArrayList<String> words) {
		HashMap<String, T1> items = new HashMap<String, T1>();
		for (String word : words) {
			T1 item = StringParser.generateFromParse(generable, word, ',', ';');
			items.put(generable.getStringKey(), item);
		}
		return items;
	}

	public static <T1, T2> HashMap<T2, T1> convertDataLinesKeyMap(StringParseGenerable<T1, T2> generable,
			ArrayList<String> words,char separator,char endOfLine) {
		HashMap<T2, T1> items = new HashMap<T2, T1>();
		for (String word : words) {
			StringParseGenerable<T1, T2> gen = generable.init();
			T1 item = StringParser.generateFromParse(gen, word, separator,endOfLine);
			items.put(gen.getKey(),item);
		}
		return items;
	}
	
	// String Operations

	public static int getIndexOfWord(String w, String chain) {
		/**
		 * get the index of the first character of the word w if chain contains
		 * the word w or -1 if chain does'nt contains w.
		 */
		int i = w.length();
		while (i < chain.length()) {
			if (chain.substring(i - w.length(), i) == w)
				return i;
			i++;
		}
		return -1;
	}

	public static String getNextWord(String line, int index, char separator) {
		/** get the whole text between index and the next separation */
		int last = index;
		while (line.charAt(index) != separator && !isLineEnd(index, line))
			index++;		
		String word = isLineEnd(index, line) ?  line.substring(last, index+1) : line.substring(last, index);
		return word;
	}

	public static String getNextWord(String line, int index, char separator, char endOfLine) {
		/** get the whole text between index and the next separation */
		int last = index;
		while (line.charAt(index) != separator && line.charAt(index) != endOfLine)
			index++;
		String word = line.substring(last, index);
		return word;
	}

	public static ArrayList<String> sliceLine(String line, char separator, char endOfLine) {
		/** get the whole text between index and the next separation */
		ArrayList<String> words = new ArrayList<String>();
		int index = 0;
		while (true) {
			String word = getNextWord(line, index, separator, endOfLine);
			words.add(word);
			index += word.length();
			if (line.charAt(index) == endOfLine)
				break;
			index++;
		}
		return words;
	}

	public static ArrayList<String> sliceLine(String line, char separator) {
		/** get the whole text between index and the next separation */
		ArrayList<String> words = new ArrayList<String>();
		int index = 0;
		while (true) {
			if(line.charAt(index) == separator ) index++;
			String word = getNextWord(line, index, separator);
			words.add(word);
			index+=word.length();
			
			if (isLineEnd(index, line))
				break;
		}
		return words;
	}

	public static ArrayList<String> readData(String fileName) {
		/**
		 * Read the file and place each lines of the text in an
		 * ArrayList<String>
		 */
		ArrayList<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				lines.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}

	public void writeData(String path, ArrayList<String> words) {
		try {

			FileWriter fw = new FileWriter(path, true);
			BufferedWriter output = new BufferedWriter(fw);

			for (int i = 0; i < words.size(); i++)
				output.write(words.get(0));
			output.flush();
			output.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public static boolean isLineEnd(int index, String line) {
		return index >= line.length()-1;
	}
}
