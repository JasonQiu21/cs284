import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {

	final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
			89, 97, 101 };
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;

	// constructor //
	public Anagrams() {
		letterTable = new HashMap<Character, Integer>();
		anagramTable = new HashMap<Long, ArrayList<String>>();
		buildLetterTable();

	}

	public Map<Character, Integer> getLetterTable() {
		return this.letterTable;
	}

	public Map<Long, ArrayList<String>> getAnagramTable() {
		return this.anagramTable;
	}

	// builds the letter table by assigning letters (keys) to their respective
	// primes (values) //
	public void buildLetterTable() {
		char c = 'a';
		for(int i: primes){
			letterTable.put(c, i);
			c++;
		}
	}

	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null) {
			this.addWord(strLine);
		}
		br.close();
	}

	// finds the hash code of the given parameter string s. set to public for
	// testing purposes. //
	public Long myHashCode(String s) {
		char[] c = s.toCharArray();
		Long p = 1L;
		for(char i: c){
			p *= letterTable.get(i);
		}
		return p;
	}

	// adds word to value and hash code to anagram table for first anagram with that
	// code being added; //
	// for anagrams not being added first, only the anagram itself is added to the
	// values of anagram table. //
	public void addWord(String s) {
		if(anagramTable.containsKey(myHashCode(s))){
			anagramTable.get(myHashCode(s)).add(s);
		} else {
			ArrayList<String> l = new ArrayList<String>();
			l.add(s);
			anagramTable.put(myHashCode(s), l);
		}
	}

	// finds set of max entries + the key it belongs to. set to public for testing
	// purposes //
	public ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
		ArrayList l = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
		int max = 0;
		for(Map.Entry<Long, ArrayList<String>> i: anagramTable.entrySet()){
			if(max == i.getValue().size()){
				l.add(i);
			} else if(max < i.getValue().size()){
				l = new ArrayList<Map.Entry<Long, ArrayList<String>>>();
				l.add(i);
				max = i.getValue().size();
			}
		}
		return l;
	}

	public static void main(String[] args) {
		Anagrams a = new Anagrams();
		final long startTime = System.nanoTime();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime / 1000000000);
		System.out.println("Elapsed Time: " + seconds);
		System.out.println(a.letterTable.toString());
		System.out.println("Key of max anagrams: " + maxEntries.get(0).getKey());
		System.out.println("List of max anagrams: " + maxEntries.get(0).getValue());
		System.out.println("Length of list of max anagrams: " + maxEntries.get(0).getValue().size());

	}

}