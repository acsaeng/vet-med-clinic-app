package group825.vetapp2.animal;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.Application_DbConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository that stores Animal information
 *
 * @author Aron Saengchan, Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Repository("tempAnimalRepo")
public class AnimalRepository {
	String table_name = "ANIMAL";
	int desiredNumberOfResults = 4;
	Application_DbConnection dao;
	String query;
	Search search;
	int Latest_animal_id;
	
	public AnimalRepository() throws Exception {
		dao = new Application_DbConnection();
		search = new Search(dao, table_name, desiredNumberOfResults);
		getLatestAnimalId();
	}

    /**
     * Retrieves all animals from the database
     * @return list of all animals
     */
    public ArrayList<String> selectAllAnimals() throws Exception{
    	query = "SELECT * FROM "+this.table_name;
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
    }

    /**
     * Adds an animal to the database
     * @param animal animal to be added
     * @return Integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
     */
    public int addAnimal(Animal animal) throws Exception{
    	String query_begin = "INSERT INTO ANIMAL (Animal_ID, Animal_Name, Species, Breed, Tattoo_Num, City_Tattooo, Birth_Date, Sex, "
    			+"RFID, Microchip, Animal_Status, Colour, Weight, Additional_Information, Length_Name, SearchKey_Name)\r\n"
    			+ "VALUES";
		query = query_begin + "( '"+animal.getId()+"', '" + animal.getName()+"', '" + animal.getSpecies()+"', '" + animal.getBreed() +"', '" + animal.getTattoo() +"', '" 
    			+ animal.getCityTattoo() +"', '" + animal.getDob()+"', '" + animal.getSex() +"', '" + animal.getRfid()+"', '" + animal.getMicrochip()
    			+"', '" + animal.getStatus()+"', '" + animal.getColor() +"', '" + animal.getWeight()+"', '" + animal.getMoreInfo() 
    			+"', '" + animal.getNameLength() +"', '" + animal.getSearchKey_Name()+"');";
//		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			getLatestAnimalId();
			query = query_begin + "( '"+(this.Latest_animal_id+1)+"', '" + animal.getName()+"', '" + animal.getSpecies()+"', '" + animal.getBreed() +"', '" + animal.getTattoo() +"', '" 
	    			+ animal.getCityTattoo() +"', '" + animal.getDob()+"', '" + animal.getSex() +"', '" + animal.getRfid()+"', '" + animal.getMicrochip()
	    			+"', '" + animal.getStatus()+"', '" + animal.getColor() +"', '" + animal.getWeight()+"', '" + animal.getMoreInfo() 
	    			+"', '" + animal.getNameLength() +"', '" + animal.getSearchKey_Name()+"');";
			int responseCheck = dao.manipulateRows(query);
		}
		return 1;
    }

    /**
     * Updates an animal's information
     * @param id animal's new or existing ID number
     * @param name animal's new or existing name
     * @param type animal's new or existing type
     * @param species animal's new or existing species
     * @param sex animal's new or existing sex
     * @param weight animal's new or existing weight
     * @return 1 if the update was sucessful, 0 otherwise
     */
    public int updateAnimalById(int id, Animal update) throws Exception{
   	 String query = "UPDATE " + table_name + " AS C SET Animal_ID='" + update.getId() + "', Animal_Name='" + update.getName() 
   	 + "', Species='" + update.getSpecies() +"', Breed='" + update.getBreed() +"', Tattoo_Num='" + update.getTattoo()
   	 + "', City_Tattooo='" + update.getCityTattoo() + "', Birth_Date='" + update.getDob()+ "', Sex='" + update.getSex() 
   	 +"', RFID='" + update.getRfid() +"', Microchip='" + update.getMicrochip() +"', Animal_Status='" + update.getStatus() 
   	 +"', Colour='" + update.getColor() +"', Weight='" + update.getWeight() +"', Additional_Information='" + update.getMoreInfo()  
   	 + "', Length_Name='" + update.getNameLength() + "', SearchKey_Name='" + update.getSearchKey_Name() 
   	 + "' WHERE C.Animal_ID='"+update.getId() +"';";
	   int responseCheck = dao.manipulateRows(query);
	   return responseCheck;
	   }
    
    
//    public int editAnimal(UUID id, String name, String type, String species, char sex, double weight) {
//        return searchAnimalById(id).map(animal -> {
//            // Finds the index of the selected animal in the database
//            int accountIdx = database.indexOf(animal);
//
//            // Updates the animal's information
//            if (accountIdx >= 0) {
//                database.get(accountIdx).setName(name);
//                database.get(accountIdx).setType(type);
//                database.get(accountIdx).setSpecies(species);
//                database.get(accountIdx).setSex(sex);
//                database.get(accountIdx).setWeight(weight);
//
//                return 1;
//            }
//
//            return 0;
//        }).orElse(0);
//    }

    /**
     * Searches for an animal by name in the database
     * @param name animal's name
     * @param species  animal's species
     * @return specified animal if found, null otherwise
     */
    public ArrayList<String> searchAnimalByName(String name, String species, boolean onlyAvailableAnimals) throws Exception{
    	ArrayList<String> foundResults = search.searchForName(name, species, onlyAvailableAnimals);
    	return foundResults;
    }

    /**
     * Searches for an animal by ID number in the database
     * @param id animal's ID number
     * @return ArrayList<String> which contains one particular animal or is empty
     * @throws Exception when there is an SQL Exception
     */
    public ArrayList<String> searchAnimalById(int id) throws Exception{
    	query = "SELECT * FROM "+this.table_name +" AS C WHERE C.Animal_ID='"+id+"';";
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
//    	return database.stream().filter(animal -> animal.getId().equals(id)).findFirst();
    }
    
    /**
	 * get the latest Id for the primary key for Animal object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestAnimalId() throws Exception{
		String queryMaxId = "SELECT MAX(A.Animal_ID) FROM ANIMAL AS A ";
//		System.out.println("queryMaxId = " + queryMaxId );
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.Latest_animal_id = Integer.valueOf(latestId);
	}
	
    /**
     * @return the Split placeholder saved in the "Application_DbConnection.java"
     */
    public String getSplitPlaceholder() {
    	return dao.getSplitPlaceholder();
    }
    
}