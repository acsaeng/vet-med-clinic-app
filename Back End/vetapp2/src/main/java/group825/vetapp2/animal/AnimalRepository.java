package group825.vetapp2.animal;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.OldDatabaseConnection;

import java.util.ArrayList;


/**
 * Repository that stores Animal information
 *
 * @author Aron Saengchan, Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Repository("tempAnimalRepo")
public class AnimalRepository {
	/**
	 * Table name from database
	 */
	String tableName = "ANIMAL";
	
	/**
	 * Desired number of search results
	 */
	int desiredNumberOfResults = 4;
	
	/**
	 * Database connection boundary class
	 */
	OldDatabaseConnection dao;
	
	/**
	 * Any query that is sent to the database
	 */
	String query;
	
	/**
	 * Search object with methods to perform animal searches
	 */
	Search search;
	
	/**
	 * Max Animal ID currently recorded on the database
	 */
	int latestID;
	
	/**
     * Constructor that initializes the AnimalRepository
     * Update the latestID data member holding the max animal ID from the database
     * instantiate the Search class holding the methods to query the database to search for animals
     */
	public AnimalRepository() throws Exception {
		dao = new OldDatabaseConnection();
		search = new Search(dao, tableName, desiredNumberOfResults);
		getLatestAnimalId();
	}

    /**
     * Retrieves all animals from the database
     * @return ArrayList<String> where each String holds all the information for one animal
     */
    public ArrayList<String> selectAllAnimals() throws Exception{
    	query = "SELECT * FROM "+this.tableName;
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
    }

    /**
     * Adds an animal to the database
     * @param animal = animal to be added
     * @return Integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
     */
    public int addAnimal(Animal animal) throws Exception{
//    	String queryBegin = "INSERT INTO ANIMAL (Animal_ID, Animal_Name, Species, Breed, Tattoo_Num, City_Tattooo, Birth_Date, Sex, "
//    			+"RFID, Microchip, Animal_Status, Colour, Weight, Additional_Information, Length_Name, SearchKey_Name)\r\n"
//    			+ "VALUES";
//		query = queryBegin + "( '"+animal.getAnimalID()+"', '" + animal.getName()+"', '" + animal.getSpecies()+"', '" + animal.getBreed() +"', '" + animal.getTattoo() +"', '" 
//    			+ animal.getCityTattoo() +"', '" + animal.getDob()+"', '" + animal.getSex() +"', '" + animal.getRfid()+"', '" + animal.getMicrochip()
//    			+"', '" + animal.getStatus()+"', '" + animal.getColor() +"', '" + animal.getWeight()+"', '" + animal.getMoreInfo() 
//    			+"', '" + animal.getNameLength() +"', '" + animal.getSearchKeyName()+"');";
//		System.out.println(query);
//		try {
//			int responseCheck = dao.manipulateRows(query);
//		}catch(Exception e) {
//			getLatestAnimalId();
//			query = queryBegin + "( '"+(this.latestID+1)+"', '" + animal.getName()+"', '" + animal.getSpecies()+"', '" + animal.getBreed() +"', '" + animal.getTattoo() +"', '" 
//	    			+ animal.getCityTattoo() +"', '" + animal.getDob()+"', '" + animal.getSex() +"', '" + animal.getRfid()+"', '" + animal.getMicrochip()
//	    			+"', '" + animal.getStatus()+"', '" + animal.getColor() +"', '" + animal.getWeight()+"', '" + animal.getMoreInfo() 
//	    			+"', '" + animal.getNameLength() +"', '" + animal.getSearchKeyName()+"');";
//			int responseCheck = dao.manipulateRows(query);
//		}
		return 1;
    }

    /**
     * Updates an animal's information
     * @param animalID = animal's ID number
     * @param update = Animal object containing the updated animal information
     * @return 1 if the update was successful, 0 otherwise
     */
    public int updateAnimalById(int animalID, Animal update) throws Exception{
   	 String query = "UPDATE " + tableName + " AS C SET Animal_ID='" + animalID + "', Animal_Name='" + update.getName() 
	   	 + "', Species='" + update.getSpecies() +"', Breed='" + update.getBreed() +"', Tattoo_Num='" + update.getTattoo()
	   	 + "', City_Tattooo='" + update.getCityTattoo() + "', Birth_Date='" + update.getDob()+ "', Sex='" + update.getSex() 
	   	 +"', RFID='" + update.getRfid() +"', Microchip='" + update.getMicrochip() +"', Animal_Status='" + update.getStatus() 
	   	 +"', Colour='" + update.getColor()  +"', Additional_Information='" + update.getMoreInfo()  
	   	 + "', Length_Name='" + update.getNameLength() + "', SearchKey_Name='" + update.getSearchKeyName() 
	   	 + "' WHERE C.Animal_ID='"+ animalID +"';";
   	 
   	 System.out.println("query = "+query);
	   int responseCheck = dao.manipulateRows(query);
	   return responseCheck;
	   }
    

    /**
     * Searches for an animal by name in the database
     * @param name = animal's name
     * @param species =  animal's species
     * @param onlyAvailableAnimals = boolean deciding whether to return all animals or only available animals
     * @return specified animal if found, null otherwise
     */
    public ArrayList<String> searchAnimalByName(String name, String species, boolean onlyAvailableAnimals) throws Exception{
    	ArrayList<String> foundResults = search.searchForName(name, species, onlyAvailableAnimals);
    	return foundResults;
    }

    /**
     * Searches for an animal by ID number in the database
     * @param animalID = animal's ID number
     * @return ArrayList<String> which should contains one String holding the information for one particular animal or the String is empty
     * @throws Exception when there is an SQL Exception
     */
    public ArrayList<String> searchAnimalById(int animalID) throws Exception{
    	query = "SELECT * FROM "+this.tableName +" AS C WHERE C.Animal_ID='"+animalID+"';";
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
    }
    
    /**
	 * get the latest Animal Id for the primary key for Animal objects from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestAnimalId() throws Exception{
		String queryMaxId = "SELECT MAX(A.Animal_ID) FROM ANIMAL AS A ";
//		System.out.println("queryMaxId = " + queryMaxId );
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.latestID = Integer.valueOf(latestId);
	}
	
	/**
     * @return the Split placeholder saved in the "DatabaseConnection.java"
     */
    public String getSplitPlaceholder() {
    	return dao.getSplitPlaceholder();
    }
    
}