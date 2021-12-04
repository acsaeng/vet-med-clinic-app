package group825.vetapp.animal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.lang.*;

import group825.vetapp.database.*;

public class Search {
	Application_DbConnection dao;
	ArrayList<String> list_species;
	String query, table_name;
	int desiredNumberOfResults;
	
	int idx_SearchKey = 15;
	int idx_species = 2;
	int idx_names = 1;
	
	
	public Search(Application_DbConnection dao, String table_name, int desiredNumberOfResults) throws Exception{
		this.dao = dao;
		this.table_name = table_name;
		get_all_species();
		this.desiredNumberOfResults = desiredNumberOfResults;
	}
	
	private void get_all_species() throws Exception{
		query = "SELECT * FROM "+this.table_name;
		String allRows = dao.getRows(query);
		System.out.println(allRows); //get all the animals from the database
		System.out.println("");
		list_species = dao.parseInfoReturned(allRows, idx_species);
		Set uniqueSpecies = new HashSet(list_species);
		list_species = new ArrayList(uniqueSpecies);
		System.out.println("list_species: " + list_species);
	}
	
	public void updateListOfSpecies() throws Exception { //method is used by Administrator to update the List of Species recorded inside the database
		get_all_species();
	}
	
	
	public ArrayList<String> searchForName(String animalName, String searchSpecies, boolean onlyAvailableAnimals) throws Exception { //sample: "Bobby horse" or "horse Bobby"
		String extra ="";
		if (onlyAvailableAnimals) {extra = " AND A.Animal_Status='Available'";}
		
		
		if(searchSpecies != null) {
			query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Species='"+searchSpecies+"' AND A.Animal_Name='"+animalName+"'" + extra + ";";
		}
		else {
			query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Animal_Name='"+animalName+"'" + extra + ";";
		}
		System.out.println(query);
		
		String allRows = dao.getRows(query);
		System.out.println("\t>>> Got response from Server");
		System.out.println("'"+allRows+"'");
		System.out.println("---------");
		ArrayList<String> exactMatches = new ArrayList<String>();
		for (String row: allRows.split("\n")) {exactMatches.add(row);}
		if (!isResultsEmpty(allRows)) { return exactMatches;} //if several exact matches to input have been received, return exact matches
		System.out.println("\t>>> Did not get exact matches from query\n");
		
		//Begin search by Name for similar names ----------------------------------------------------------------
//		if(includesAnimalType) {
//			query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Species='"+searchSpecies+"' AND A.Length_Name='"+animalName.length()+"';";
//		}
//		else {
//			query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Length_Name='"+animalName.length()+"';";
//		}
//		System.out.println(query);
//		allRows = dao.getRows(query);
//		System.out.println("\t>>> Got response from Server");
//		System.out.println(allRows);
//		System.out.println("---------");
//		ArrayList<String> findings_searchKeys = dao.parseInfoReturned(allRows, 16);
//		System.out.println(findings_searchKeys);
//		//UPDATE THIS PORTION^^^^^^^^
		String allRowsFromEveryQuery = getMinNumOfResults((searchSpecies != null), searchSpecies, animalName);
		ArrayList<String> allResultsFromDb = new ArrayList<String>();
		for (String rowResult: allRowsFromEveryQuery.split("\n")) {allResultsFromDb.add(rowResult);}
//		System.out.println("allRowsFromEveryQuery = '"+allRowsFromEveryQuery+"'");
		
		//if nothing was found at all, return blank ArrayList
		if(allRowsFromEveryQuery == "") {return new ArrayList<String>();}
		
		//Otherwise, organize findings based on most similar
		System.out.println("allFindings_searchKeys:");
		ArrayList<String> allFindings_searchKeys = dao.parseInfoReturned(allRowsFromEveryQuery, idx_SearchKey);

		System.out.println("animalName = '" + animalName+"'");
		String newSearchKey = generateSearchKey(animalName);
//		calcDifference(newSearchKey, allFindings_searchKeys.get(0)); // test
		ArrayList<Integer> list_differences = new ArrayList<Integer>();
		for (String dbSearchKey: allFindings_searchKeys) {
			list_differences.add(calcDifference(newSearchKey, dbSearchKey));
		}
		
		System.out.println("\nlist_differences = "+list_differences);
		System.out.println("list_names = "+ dao.parseInfoReturned(allRowsFromEveryQuery, idx_names));
		
		//organize db results based on closest similarity
		int numResultsReturned = 0;
		ArrayList<String> organizedResults = new ArrayList<String>();
		//trial for get the minimum then remove 
//		int idxMin = getIndexOfMin(list_differences);
//		organizedResults.add(allResultsFromDb.get(idxMin));
//		allResultsFromDb.remove(idxMin);
		
		
		while(numResultsReturned < this.desiredNumberOfResults && list_differences.size()!=0) {
			int idxMin = getIndexOfMin(list_differences);
			organizedResults.add(allResultsFromDb.get(idxMin));
			allResultsFromDb.remove(idxMin);
			list_differences.remove(idxMin);
			numResultsReturned++;
		}
//		System.out.println("organizedResults: '"+ organizedResults+"'");
//		System.out.println("allResultsFromDb: '"+ allResultsFromDb+"'");
		System.out.println("organizedResults: ");
		for(String result: organizedResults) {
			System.out.println("\t -> "+result);
		}
		System.out.println(" ----");
		System.out.println("remaining of allResultsFromDb: ");
		for(String result: allResultsFromDb) {
			System.out.println("\t -> "+result);
		}
		System.out.println(" ----");
		
		return organizedResults;
		
	}
	
	private int getIndexOfMin(ArrayList<Integer> list_differences) {
//		System.out.println("list_differences = " + list_differences);
		int minIdx =0;
		Integer minValue =  list_differences.get(minIdx);
		for (int idx=0; idx<list_differences.size(); idx++) {
			if ( list_differences.get(idx) < minValue) {
				minValue = list_differences.get(idx);
				minIdx = idx;
			}
		}
		System.out.println("\t ==> Min value found was: "+minValue);
		return minIdx;
	}
	
	private String getMinNumOfResults(boolean includesAnimalType, String searchSpecies, String animalName) throws Exception{	
		//Send search query to search for name of the same length
		if(includesAnimalType) {
			query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Species='"+searchSpecies+"' AND A.Length_Name='"+animalName.length()+"';";
		}
		else {
			query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Length_Name='"+animalName.length()+"';";
		}
		System.out.println(query);
		String allRowsFromEveryQuery = dao.getRows(query);
		System.out.println("\t>>> Got response from Server");
		System.out.println(allRowsFromEveryQuery);
		System.out.println("---------");
		
		
		
		boolean noMoreSmallerNames = false; 
		boolean noMoreBiggerNames = false;
		int lengthDeviationFromOriginalName = 1;
		System.out.println("allRowsFromEveryQuery = '"+allRowsFromEveryQuery+"'");
//		System.out.println("allRowsFromEveryQuery.split(n).length = " + allRowsFromEveryQuery.split("\n").length);
//		System.out.println(allRowsFromEveryQuery);
		while(allRowsFromEveryQuery.split("\n").length < this.desiredNumberOfResults && (!noMoreSmallerNames || !noMoreBiggerNames)) {
			//while loop ends when the number of desired search queries is found 
			//or when no more names can be found that are greater or less than the size of the animalName that is in the search input
			
			if (!noMoreSmallerNames) {
				System.out.println("------query for names of SMALLER size");
				
				if(includesAnimalType) {
					query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Species='"+searchSpecies+"' AND A.Length_Name='"+(animalName.length()-lengthDeviationFromOriginalName )+"';";
				}
				else {
					query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Length_Name='"+(animalName.length()-lengthDeviationFromOriginalName )+"';";
				}
				String allRows = dao.getRows(query);
				System.out.println("\t>>> Got response from Server");
				System.out.println(allRows);
				System.out.println("---------");
				allRowsFromEveryQuery = allRowsFromEveryQuery + allRows;
				if (isResultsEmpty(allRows)) {noMoreSmallerNames = true;}
			}
			
			if (!noMoreBiggerNames) {
				System.out.println("------query for names of LARGER size");
				
				if(includesAnimalType) {
					query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Species='"+searchSpecies+"' AND A.Length_Name='"+(animalName.length()+lengthDeviationFromOriginalName )+"';";
				}
				else {
					query = "SELECT * FROM "+this.table_name +" AS A WHERE A.Length_Name='"+(animalName.length()+lengthDeviationFromOriginalName )+"';";
				}
				String allRows = dao.getRows(query);
				System.out.println("\t>>> Got response from Server");
				System.out.println(allRows);
				System.out.println("---------");
				allRowsFromEveryQuery = allRowsFromEveryQuery + allRows;
				if (isResultsEmpty(allRows)) {noMoreBiggerNames = true;}
			}
			
			lengthDeviationFromOriginalName++;
			
		}
		
		System.out.println("\n---- now have minimum number of search queries or no more names could be found");
//		System.out.println("All Findings: ");
//		System.out.println(allRowsFromEveryQuery);
//		System.out.println("");
//		ArrayList<String> allFindings_searchKeys = dao.parseInfoReturned(allRowsFromEveryQuery, 16);
//		System.out.println(allFindings_searchKeys);
		
		
		return allRowsFromEveryQuery;
	}
	
	
	private int calcDifference(String newSearchKey, String dbSearchKey) {
		System.out.println("\n --within calcDifference()");
		System.out.println("newSearchKey: "+newSearchKey);
		System.out.println("dbSearchKey: "+dbSearchKey);
		ArrayList<DictionaryLetter> dict_new = createLetterDictionary_bySearchKey(newSearchKey);
		ArrayList<DictionaryLetter> dict_db = createLetterDictionary_bySearchKey(dbSearchKey);
		
		int difference = 0;
		for (int idx=0; idx<dict_new.size(); idx++) {
			int letter_difference = Math.abs(dict_new.get(idx).value - dict_db.get(idx).value);
			difference = difference + letter_difference;
		}
//		System.out.println("difference = "+difference);
		return difference;
	}
	
	private boolean isResultsEmpty(String queryResults) {
		if(queryResults == "") {return true;}
		return false;
	}
	
	private ArrayList<DictionaryLetter> createLetterDictionary_bySearchKey(String searchKey){
//		System.out.println("\n --within createLetterDictionary_bySearchKey()");
		System.out.println("searchKey: "+searchKey);
		ArrayList<DictionaryLetter> dict = createLetterDictionary();
		for (String subString: searchKey.split("-")) {
			subString = subString.replaceAll("\\s", "");
			char letter = subString.charAt(0);
//			System.out.println("letter = "+letter);
			dict.get((int)letter-(int)'a').value = dict.get((int)letter-(int)'a').value +Integer.valueOf(subString.substring(1));
		}
//		System.out.println(dict);
		return dict;
	}
	
	private String generateSearchKey(String animalName) {
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
	
	public ArrayList<DictionaryLetter> createLetterDictionary(){
		ArrayList<DictionaryLetter> dict = new ArrayList<DictionaryLetter>();
		for (int idx=0; idx<26; idx++) {
//			System.out.println((char)('a'+idx));
			dict.add(new DictionaryLetter((char)('a'+idx), 0));
		}
		return dict;
	}
	
	
}
