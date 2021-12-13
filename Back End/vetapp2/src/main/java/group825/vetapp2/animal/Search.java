package group825.vetapp2.animal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import group825.vetapp2.database.*;

import java.lang.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Search {
	OldDatabaseConnection dao;
	ArrayList<String> listSpecies;
	String query, tableName;
	int desiredNumberOfResults;
	
	int idx_SearchKey = 15;
	int idx_species = 2;
	int idx_names = 1;
	
	/**
	 * Connector to the database
	 */
	Connection dao2;

	
	
	public Search(int desiredNumberOfResults) {
//		this.dao = dao;
		this.dao2 = DatabaseConnection.getConnection();
		this.tableName = "ANIMAL";
		this.listSpecies = new ArrayList<String>();
		
		get_all_species();
		this.desiredNumberOfResults = desiredNumberOfResults;
		
		
	}
	
	/**
	 * Get all current animal species on the database
	 * @throws Exception
	 */
	private void get_all_species(){
//		query = "SELECT * FROM "+this.tableName;
//		String allRows = dao.getRows(query);
//		System.out.println(allRows); //get all the animals from the database
//		System.out.println("");
//		listSpecies = dao.parseInfoReturned(allRows, idx_species);
//		Set uniqueSpecies = new HashSet(listSpecies);
//		listSpecies = new ArrayList(uniqueSpecies);
//		System.out.println("listSpecies: " + listSpecies);
		
		
		// Execute SQL query to retrieve all animals
		try {
			PreparedStatement statement = this.dao2.prepareStatement("SELECT * FROM "+this.tableName);
			ResultSet results = statement.executeQuery();
			
			while (results.next()) {
				this.listSpecies.add(results.getString("Species"));
			}
			Set uniqueSpecies = new HashSet(listSpecies);
			listSpecies = new ArrayList(uniqueSpecies);
			statement.close();
//			System.out.println("listSpecies: " + listSpecies);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error gettting the species currently on database");
		}
			
	}
	
	
	/**
	 * Used by Administrator who runs the system to update the List of Species recorded inside the database
	 * @throws SQLException
	 */
	public void updateListOfSpecies() throws Exception { 
		get_all_species();
	}
	
	
	public ArrayList<Animal> searchForName(String animalName, String searchSpecies, boolean onlyAvailableAnimals) { //sample: "Bobby horse" or "horse Bobby"
		//PART ONE - EXACT NAME MATCHES --------------------------------------------------------------------------------------------
		
//		String extra ="";
//		if (onlyAvailableAnimals) {extra = " AND A.Animal_Status='Available'";}
//		
//		
//		if(searchSpecies != null) {
//			query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Species='"+searchSpecies+"' AND A.Animal_Name='"+animalName+"'" + extra + " ORDER BY A.Animal_ID;";
//		}
//		else {
//			query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Animal_Name='"+animalName+"'" + extra + " ORDER BY A.Animal_ID;";
//		}
//		System.out.println(query);
//		
//		String allRows = dao.getRows(query);
//		System.out.println("\t>>> Got response from Server");
//		System.out.println("'"+allRows+"'");
//		System.out.println("---------");
//		ArrayList<String> exactMatches = new ArrayList<String>();
//		for (String row: allRows.split("\n")) {exactMatches.add(row);}
//		if (!isResultsEmpty(allRows)) { return exactMatches;} //if several exact matches to input have been received, return exact matches
//		System.out.println("\t>>> Did not get exact matches from query\n");
		

		String extra ="";
		if (onlyAvailableAnimals) {extra = " AND A.Availability_Status='Available'";}
		
		
		if(searchSpecies != null) {
			query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Animal_Name=?  AND A.Species=? " + extra + " ORDER BY A.Animal_ID;";
		}
		else {
			query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Animal_Name=?" + extra + " ORDER BY A.Animal_ID;";
		}
		System.out.println(query);
		
		try {
			PreparedStatement statement = this.dao2.prepareStatement(query);
			statement.setString(1, animalName);
			if(searchSpecies != null) {statement.setString(2, searchSpecies);}
			
			ResultSet results = statement.executeQuery();
			System.out.println("\t>>> Got response from Server");
			
//			System.out.println("'"+allRows+"'");
//			System.out.println("---------");
			ArrayList<Animal> exactMatches = new ArrayList<Animal>();
			while (results.next()) {
				exactMatches.add(new Animal(results.getInt("Animal_ID"), results.getString("Animal_Name"),
						results.getString("Species"), results.getString("Breed"), results.getInt("Tattoo_Num"),
						results.getString("City_Tattoo"), LocalDate.parse(results.getString("Birth_Date")),
						results.getString("Sex").charAt(0), results.getString("RFID"), results.getString("Microchip"),
						results.getString("Health_Status"), results.getBoolean("Availability_Status"),
						results.getString("Colour"), results.getString("Additional_Info"), 
						results.getInt("Length_Name"), results.getString("SearchKey_Name")));
			}
			
			if (exactMatches.size() > 0) {
				return exactMatches;
			}
			
		}catch (Exception e) {//
			e.printStackTrace();
			System.out.println("Error searching for exact match");
//			System.out.println("No animals were found for the user entered name :" + animalName);
//			System.out.println("\n\n >>> Proceed to part two with ");
		}
	
		
		
		
		
		//PART TWO - SEARCH FOR SIMILAR NAME MATCHES ------------------------------------------------------------------------------------------
		
		System.out.println("\t>>> Did not get exact matches from query\n");
		System.out.println("\n\n >>> Proceed to part two to search for names that are similar");
//		String allRowsFromEveryQuery = getMinNumOfResults((searchSpecies != null), searchSpecies, animalName);
//		ArrayList<String> allResultsFromDb = new ArrayList<String>();
//		for (String rowResult: allRowsFromEveryQuery.split("\n")) {allResultsFromDb.add(rowResult);}
////		System.out.println("allRowsFromEveryQuery = '"+allRowsFromEveryQuery+"'");
		
		//Get an arraylist of Animals whose names are of similar length to the entered name
		ArrayList<Animal> minMatches =  getMinNumOfResults((searchSpecies != null), searchSpecies, animalName);
		
		
		//if nothing was found at all, return blank ArrayList
		if(minMatches.size() == 0) {return new ArrayList<Animal>();}
		
		//Otherwise, organize findings based on most similar search key
		System.out.println("allFindingsSearchKeys:");
//		ArrayList<String> allFindingsSearchKeys = dao.parseInfoReturned(allRowsFromEveryQuery, idx_SearchKey);
		ArrayList<String> allFindingsSearchKeys = new ArrayList<String>();
		for (Animal currAnimal: minMatches){ 
			allFindingsSearchKeys.add(currAnimal.getSearchKeyName());
		}

		System.out.println("animalName = '" + animalName+"'");
		String newSearchKey = generateSearchKey(animalName);
//		calcDifference(newSearchKey, allFindingsSearchKeys.get(0)); // test
		ArrayList<Integer> list_differences = new ArrayList<Integer>();
		for (String dbSearchKey: allFindingsSearchKeys) {
			list_differences.add(calcDifference(newSearchKey, dbSearchKey));
		}
		
		System.out.println("\nlist_differences = "+list_differences);
//		System.out.println("list_names = "+ dao.parseInfoReturned(allRowsFromEveryQuery, idx_names));
		
		
//		//organize db results based on closest similarity
//		int numResultsReturned = 0;
//		ArrayList<String> organizedResults = new ArrayList<String>();
//		//trial for get the minimum then remove 
////		int idxMin = getIndexOfMin(list_differences);
////		organizedResults.add(allResultsFromDb.get(idxMin));
////		allResultsFromDb.remove(idxMin);
//		
//		
//		while(numResultsReturned < this.desiredNumberOfResults && list_differences.size()!=0) {
//			int idxMin = getIndexOfMin(list_differences);
//			organizedResults.add(allResultsFromDb.get(idxMin));
//			allResultsFromDb.remove(idxMin);
//			list_differences.remove(idxMin);
//			numResultsReturned++;
//		}
		
		
		// PART THREE - organize Database results based on closest similarity-----------------------------------------------------------------------
		int numResultsReturned = 0;
		ArrayList<Animal> organizedResults = new ArrayList<Animal>();
		//trial for get the minimum then remove 
//		int idxMin = getIndexOfMin(list_differences);
//		organizedResults.add(allResultsFromDb.get(idxMin));
//		allResultsFromDb.remove(idxMin);
		
		
		while(numResultsReturned < this.desiredNumberOfResults && list_differences.size()!=0) {
			int idxMin = getIndexOfMin(list_differences);
			organizedResults.add(minMatches.get(idxMin));
			minMatches.remove(idxMin);
			list_differences.remove(idxMin);
			numResultsReturned++;
		}
		
		
//		System.out.println("organizedResults: '"+ organizedResults+"'");
//		System.out.println("allResultsFromDb: '"+ allResultsFromDb+"'");
		System.out.println("organizedResults: ");
		for(Animal currAnimal: organizedResults) {
			System.out.println("\t -> "+currAnimal.getName());
		}
		System.out.println(" ----");
		System.out.println("remaining of allResultsFromDb: ");
		for(Animal currAnimal:  minMatches) {
			System.out.println("\t -> "+currAnimal.getName());
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
	
	/**
	 * Get an an ArrayList of animals which will have at least the desired number of results entered in the constructor
	 * This arrayList is populated by animal's whose names are of similar length.
	 * Longer and shorter names are added until the minimum number of desired results is achieved.
	 * 
	 * getMinOfResults will return empty arraylist if the database has no names of the same length and will stop incrementing and decrementing
	 * the name length when no results is found in either situations.
	 * 
	 * @param includesAnimalType = boolean to determine whether or not to include the animal's species in the search query
	 * @param searchSpecies = species entered by user
	 * @param animalName = name of the animal entered by the user
	 * @return ArrayList<Animal> containing the minimum number of results whose names are of similar length to the search query
	 * @throws SQLException
	 */
	private ArrayList<Animal> getMinNumOfResults(boolean includesAnimalType, String searchSpecies, String animalName){	
//		//Send search query to search for name of the same length
//		if(includesAnimalType) {
//			query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Species='"+searchSpecies+"' AND A.Length_Name='"+animalName.length()+"';";
//		}
//		else {
//			query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Length_Name='"+animalName.length()+"';";
//		}
//		System.out.println(query);
//		String allRowsFromEveryQuery = dao.getRows(query);
//		System.out.println("\t>>> Got response from Server");
//		System.out.println(allRowsFromEveryQuery);
//		System.out.println("---------");
		
		
		//Send search query to search for name of the same length
		ArrayList<Animal> minMatches = new ArrayList<Animal>();
		
		if(includesAnimalType) {
			query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Length_Name=? AND A.Species=?;";
		}
		else {
			query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Length_Name=?;";
		}
		System.out.println("query = "+query);
		
		try{
			PreparedStatement statement = this.dao2.prepareStatement(query);
			statement.setInt(1, animalName.length());
			if(includesAnimalType) {statement.setString(2, searchSpecies);}
			
			ResultSet results = statement.executeQuery();
			
//			while (results.next()) {
//				minMatches.add(new Animal(results.getInt("Animal_ID"), results.getString("Animal_Name"),
//						results.getString("Species"), results.getString("Breed"), results.getInt("Tattoo_Num"),
//						results.getString("City_Tattoo"), LocalDate.parse(results.getString("Birth_Date")),
//						results.getString("Sex").charAt(0), results.getString("RFID"), results.getString("Microchip"),
//						results.getString("Health_Status"), results.getBoolean("Availability_Status"),
//						results.getString("Colour"), results.getString("Additional_Info"), 
//						results.getInt("Length_Name"), results.getString("SearchKey_Name")));
//			}
			minMatches = createListAnimal(results, minMatches);
			System.out.println("\t>>> Got response from Server - Grabbed names of the same length");
			statement.close();
			
		}catch(Exception e) {
			System.out.println("Error grabbing names of the same length");
		}
		
	
		
		boolean noMoreSmallerNames = false; 
		boolean noMoreBiggerNames = false;
		int lengthDeviationFromOriginalName = 1;

//		System.out.println("allRowsFromEveryQuery.split(n).length = " + allRowsFromEveryQuery.split("\n").length);

//		while(allRowsFromEveryQuery.split("\n").length < this.desiredNumberOfResults && (!noMoreSmallerNames || !noMoreBiggerNames)) {
		while(minMatches.size() < this.desiredNumberOfResults && (!noMoreSmallerNames || !noMoreBiggerNames)) {
			//while loop ends when the number of desired search queries is found 
			//or when no more names can be found that are greater or less than the size of the animalName that is in the search input
			try {
				if (!noMoreSmallerNames) {
					System.out.println("------query for names of SMALLER size");
					ArrayList<Animal> smallerNames = new ArrayList<Animal>();
					
					if(includesAnimalType) {
						query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Length_Name=? AND A.Species=?";
					}
					else {
						query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Length_Name=?";
					}
//					String allRows = dao.getRows(query);
//					System.out.println("\t>>> Got response from Server");
//					System.out.println(allRows);
//					System.out.println("---------");
//					allRowsFromEveryQuery = allRowsFromEveryQuery + allRows;
					
					PreparedStatement statement = this.dao2.prepareStatement(query);
					statement.setInt(1, animalName.length()-lengthDeviationFromOriginalName);
					if(includesAnimalType) {statement.setString(2, searchSpecies);}
					
					ResultSet results = statement.executeQuery();
					smallerNames = createListAnimal(results, smallerNames);
			
					for (Animal newAnimal: smallerNames) {minMatches.add(newAnimal);}
					
					if (smallerNames.size() ==0) {noMoreSmallerNames = true;}
					statement.close();
				}
				
				if (!noMoreBiggerNames) {
//					System.out.println("------query for names of LARGER size");
//					
//					if(includesAnimalType) {
//						query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Species='"+searchSpecies+"' AND A.Length_Name='"+(animalName.length()+lengthDeviationFromOriginalName )+"';";
//					}
//					else {
//						query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Length_Name='"+(animalName.length()+lengthDeviationFromOriginalName )+"';";
//					}
//					String allRows = dao.getRows(query);
//					System.out.println("\t>>> Got response from Server");
//					System.out.println(allRows);
//					System.out.println("---------");
//					allRowsFromEveryQuery = allRowsFromEveryQuery + allRows;
//					if (isResultsEmpty(allRows)) {noMoreBiggerNames = true;}
//					
					
					
					System.out.println("------query for names of LARGER size");
					ArrayList<Animal> largerNames = new ArrayList<Animal>();
					
					if(includesAnimalType) {
						query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Length_Name=? AND A.Species=?";
					}
					else {
						query = "SELECT * FROM "+this.tableName +" AS A WHERE A.Length_Name=?";
					}
//					String allRows = dao.getRows(query);
//					System.out.println("\t>>> Got response from Server");
//					System.out.println(allRows);
//					System.out.println("---------");
//					allRowsFromEveryQuery = allRowsFromEveryQuery + allRows;
					
					PreparedStatement statement = this.dao2.prepareStatement(query);
					statement.setInt(1, animalName.length()+lengthDeviationFromOriginalName);
					if(includesAnimalType) {statement.setString(2, searchSpecies);}
					
					ResultSet results = statement.executeQuery();
					largerNames = createListAnimal(results, largerNames);
					
					for (Animal newAnimal: largerNames) {minMatches.add(newAnimal);}
					
					if (largerNames.size() ==0) {noMoreBiggerNames = true;}
					statement.close();
					
				}
				
				lengthDeviationFromOriginalName++;
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("Error getting names for smaller and bigger names");
				break; //break out of while loop if error encountered
			}
			
			
		}
		
		System.out.println("\n---- now have minimum number of search queries or no more names could be found");
//		System.out.println("All Findings: ");
//		System.out.println(allRowsFromEveryQuery);
//		System.out.println("");
//		ArrayList<String> allFindingsSearchKeys = dao.parseInfoReturned(allRowsFromEveryQuery, 16);
//		System.out.println(allFindingsSearchKeys);
		
		
		return minMatches;
	}
	
	
	private ArrayList<Animal> createListAnimal(ResultSet results, ArrayList<Animal> currArr) throws Exception{
		while (results.next()) {
			currArr.add(new Animal(results.getInt("Animal_ID"), results.getString("Animal_Name"),
					results.getString("Species"), results.getString("Breed"), results.getInt("Tattoo_Num"),
					results.getString("City_Tattoo"), LocalDate.parse(results.getString("Birth_Date")),
					results.getString("Sex").charAt(0), results.getString("RFID"), results.getString("Microchip"),
					results.getString("Health_Status"), results.getBoolean("Availability_Status"),
					results.getString("Colour"), results.getString("Additional_Info"), 
					results.getInt("Length_Name"), results.getString("SearchKey_Name")));
		}
		
		return currArr;
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
