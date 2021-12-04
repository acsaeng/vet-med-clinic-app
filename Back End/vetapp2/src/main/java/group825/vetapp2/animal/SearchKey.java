package group825.vetapp2.animal;

import java.util.ArrayList;

public class SearchKey {
	public static String generateSearchKey(String animalName) {
		//create a dictionary for all the letters in the alphabet
		ArrayList<DictionaryLetter> dict = createLetterDictionary();
		
		//update dictionary to count number of each letter in the animalName
//		dict = updateForName(dict, animalName);
		for (int idx=0; idx<animalName.length(); idx++) {
			char letter = animalName.toLowerCase().charAt(idx);
//			System.out.println(letter);
			dict.get((int)letter-(int)'a').value ++;
		}
		
		//create a search key consisting of the letter, the number of times the letter appears in the name, and delimiter '-'
		String newSearchKey = "";
		for (int idx=0; idx<26; idx++) {
			char letter = (char)('a'+idx);
			newSearchKey =newSearchKey + String.valueOf(letter) +  dict.get((int)letter-(int)'a').value + "-";
		}
		newSearchKey = newSearchKey.substring(0, newSearchKey.length()-1);
		System.out.print("newSearchKey: ");
		System.out.println(newSearchKey);
		return newSearchKey;
	}
	

	
	public static ArrayList<DictionaryLetter> createLetterDictionary(){
		ArrayList<DictionaryLetter> dict = new ArrayList<DictionaryLetter>();
		for (int idx=0; idx<26; idx++) {
//			System.out.println((char)('a'+idx));
			dict.add(new DictionaryLetter((char)('a'+idx), 0));
		}
		return dict;
	}
}
