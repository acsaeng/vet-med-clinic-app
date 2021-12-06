package group825.vetapp2.animal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//import group825.vetapp.animal.comments.Comment;

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
     * @param animal = animal to be added
     * @return 1 if addition was successful, 0 otherwise
     */
    public int addAnimal(Animal animal) throws Exception{
        return this.repo.addAnimal(animal);
    }

    /**
     * Updates an animal's information
     * @param animalID = animal's ID number
     * @param animal = Animal object with all the updated animal information
     * @return 1 if the update was successful, 0 otherwise
     */
    public int updateAnimalById(int animalID, Animal animal) throws Exception{
		return this.repo.updateAnimalById(animalID, animal);
	}

    /**
     * Searches for an animal by name in the database
     * @param name = animal's name
     * @species = species of animal
     * @param onlyAvailableAnimals = whether to check for only available animals or for all animals
     * @return specified animal if found, null otherwise
     */
    public List<Animal> searchAnimalByName(String name, String species, boolean onlyAvailableAnimals) throws Exception{
    	ArrayList<String> foundResults = this.repo.searchAnimalByName(name, species, onlyAvailableAnimals);
    	List<Animal> listResults = createListAnimal(foundResults);
    	return listResults;
    }

    /**
     * Searches for an animal by ID number in the database
     * @param animalID = animal's ID number
     * @return specified animal if found, null otherwise
     * @throws Exception when there is an SQL Exception
     */
    public List<Animal> searchAnimalById(int animalID) throws Exception {
    	ArrayList<String> results =  this.repo.searchAnimalById(animalID);
		List<Animal> listResults = createListAnimal(results);
		return listResults;
    }
    
    /**
	  * Create a list of Animal objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> of preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Animal> where each object was created from the data in each String from the ArrayList input
	 */
    public List<Animal> createListAnimal(ArrayList<String> foundResults){
    	List<Animal> listResults = new ArrayList<Animal>(); 
    	//review against Database setup
    	int idxAnimalID=0, idxName=1, idxBreed=3, idxSpecies=2, idxSex=7, idxWeight=12, idxTattoo=4, idxCityTattoo=5, 
    			idxDob=6, idxRfid=8, idxMicrochip=9, idxStatus=10, idxColor=11, idxMoreInfo=13, 
    			idxNameLength=14, idxSearchKeyName=15;
    	
    	for (String result: foundResults) {
    		String[] resultSplit = result.split( repo.getSplitPlaceholder());
//    		for (int count=0; count <resultSplit.length; count++) {
//    			System.out.println(count + " - "+ resultSplit[count]);
//    		}
//    		System.out.println(Double.valueOf(resultSplit[idxWeight]));
    	Animal temp =  new Animal( Integer.valueOf(resultSplit[idxAnimalID]), resultSplit[idxName], resultSplit[idxSpecies], resultSplit[idxBreed], 
    			resultSplit[idxSex], Double.valueOf(resultSplit[idxWeight]), resultSplit[idxTattoo], resultSplit[idxCityTattoo], 
    			resultSplit[idxDob], resultSplit[idxRfid], resultSplit[idxMicrochip], resultSplit[idxStatus], 
    			resultSplit[idxColor], resultSplit[idxMoreInfo], Integer.valueOf(resultSplit[idxNameLength]), 
    			resultSplit[idxSearchKeyName]);
    	listResults.add(temp);
    }
    	
    System.out.println("\nPrepared List to send as json response to API endpoint:");
    System.out.println(listResults);

    return listResults;
    }
}