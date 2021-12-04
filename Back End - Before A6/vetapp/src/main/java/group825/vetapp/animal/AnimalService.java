package group825.vetapp.animal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group825.vetapp.animal.comments.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service that performs Animal requests
 *
 * @author Aron Saengchan, Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Service
public class AnimalService {
    /**
     * Animal repository that accesses the database
     */
    private final AnimalRepository repo;

    /**
     * Constructor that initializes the AnimalService
     * @param repo repository that accesses the database
     */
    public AnimalService(@Qualifier("tempAnimalRepo") AnimalRepository repo) {
        this.repo = repo;
    }

    /**
     * Retrieves all animals from the database
     * @return a list of all stored animals
     */
    public List<Animal> selectAllAnimals() throws Exception{
    	ArrayList<String> results = repo.selectAllAnimals();
		List<Animal> listResults = createListAnimal(results);
    	return listResults;
    }

    /**
     * Adds an animal to the database
     * @param animal animal to be added
     * @return 1 if addition was successful, 0 otherwise
     */
    public int addAnimal(Animal animal) throws Exception{
        return this.repo.addAnimal(animal);
    }

    /**
     * Updates an animal's information
     * @param id animal's new or existing ID number
     * @param name animal's new or existing name
     * @param type animal's new or existing type
     * @param species animal's new or existing species
     * @param sex animal's new or existing sex
     * @param weight animal's new or existing weight
     * @return 1 if the update was successful, 0 otherwise
     */
    public int updateAnimalById(int id, Animal animal) throws Exception{
		return this.repo.updateAnimalById(id, animal);
	}
//    public int editAnimal(UUID id, String name, String type, String species, char sex, double weight) {
//        return this.repo.editAnimal(id, name, type, species, sex, weight);
//    }

    /**
     * Searches for an animal by name in the database
     * @param name animal's name
     * @return specified animal if found, null otherwise
     */
    public List<Animal> searchAnimalByName(String name, String species, boolean onlyAvailableAnimals) throws Exception{
    	ArrayList<String> foundResults = this.repo.searchAnimalByName(name, species, onlyAvailableAnimals);
    	List<Animal> listResults = createListAnimal(foundResults);
    	return listResults;
    }

    /**
     * Searches for an animal by ID number in the database
     * @param id animal's ID number
     * @return specified animal if found, null otherwise
     * @throws Exception when there is an SQL Exception
     */
    public List<Animal> searchAnimalById(int id) throws Exception {
//        return this.repo.searchAnimalById(id);
    	ArrayList<String> results =  this.repo.searchAnimalById(id);
		List<Animal> listResults = createListAnimal(results);
		return listResults;
    }
    
    /**
	  * Create a list of Animal objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Animal> where each object was created from the data in each String from the ArrayList input
	 */
    public List<Animal> createListAnimal(ArrayList<String> foundResults){
    	List<Animal> listResults = new ArrayList<Animal>(); 
    	//review against Database setup
    	int idx_id=0, idx_name=1, idx_breed=3, idx_species=2, idx_sex=7, idx_weight=12, idx_tattoo=4, idx_cityTattoo=5, 
    			idx_dob=6, idx_rfid=8, idx_microchip=9, idx_status=10, idx_color=11, idx_moreInfo=13, 
    			idx_nameLength=14, idx_searchKey_Name=15;
    	for (String result: foundResults) {
    		String[] resultSplit = result.split( repo.getSplitPlaceholder());
//    		for (int count=0; count <resultSplit.length; count++) {
//    			System.out.println(count + " - "+ resultSplit[count]);
//    		}
//    		System.out.println(Double.valueOf(resultSplit[idx_weight]));
    	Animal temp =  new Animal( Integer.valueOf(resultSplit[idx_id]), resultSplit[idx_name], resultSplit[idx_species], resultSplit[idx_breed], 
    			resultSplit[idx_sex], Double.valueOf(resultSplit[idx_weight]), resultSplit[idx_tattoo], resultSplit[idx_cityTattoo], 
    			resultSplit[idx_dob], resultSplit[idx_rfid], resultSplit[idx_microchip], resultSplit[idx_status], 
    			resultSplit[idx_color], resultSplit[idx_moreInfo], Integer.valueOf(resultSplit[idx_nameLength]), 
    			resultSplit[idx_searchKey_Name]);
    	listResults.add(temp);
    }
    System.out.println("\nPrepared List to send as json response to API endpoint:");
    System.out.println(listResults);

    return listResults;
    }
}