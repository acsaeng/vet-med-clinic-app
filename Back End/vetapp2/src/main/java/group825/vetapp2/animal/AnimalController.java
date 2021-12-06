package group825.vetapp2.animal;

import org.springframework.web.bind.annotation.*;

import group825.vetapp2.exceptions.ApiRequestException;
import group825.vetapp2.exceptions.InvalidIdException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller that handles Animal requests
 *
 * @author Aron Saengchan, Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/app/animal")
public class AnimalController {

    /**
     * Animal service that performs the request
     */
    private final AnimalService animalService;

    /**
     * Constructor that initializes the AnimalController
     * @param animalService service that performs the request
     */
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }
    
    /**
     * 'GET' request that retrieves all animals from the database
     * @return a list of all stored animals
     */
    @GetMapping
    public List<Animal> selectAllAnimals() throws Exception{
        return this.animalService.selectAllAnimals();
    }
    
    /**
	 * 'GET' mapping that retrieves one animal in the database
	 * @param idStr String path variable obtained by path denoted inside the GetMapping annotation
	 * @return comment object or throw exception
	 */
	@GetMapping(path="{animalID}") 
	public List<Animal> selectAnimalById(@PathVariable("animalID") String animalID) {
		try {
			//id of animal
			int id = Integer.valueOf(animalID);
			return animalService.searchAnimalById(id);
		} catch(Exception e) {
			// Catch if id is not a valid Animal ID from Database
			throw new InvalidIdException();
		}
	}
    
	
    /**
     * 'GET' request that searches for animals from the database based on the name and/or species
     * @param name = animal's name
     * @param animalID = animal's ID number
     * @return List of Animal objects which have names similar to the name parameter
     */
    @GetMapping(path = "/searchAvailable")
    public List<Animal> searchAnimalAvailable(@RequestParam(required = true) String name, @RequestParam(name = "species", required = false) String species) throws Exception{
        if (name != null) {
        	boolean onlyAvailableAnimals = true;
            return this.animalService.searchAnimalByName(name, species, onlyAvailableAnimals);
        } 
        return null;
    }
 
    
    /**
     * 'GET' request that searches for animals from the database based on animalID or based on the name with/without species
     * @param name = animal's name
     * @param species = animal's species
     * @param animalID = animal's ID number
     * @return List<Animal> where the Animal match by animalID, similarity by name, or similarity by name and species
     */
    @GetMapping(path = "/search")
    public List<Animal> searchAnimal(@RequestParam(required = false) String name, @RequestParam(name = "species", required = false) String species, 
    		@RequestParam(name = "animalID", required = false) String animalID) throws Exception{
        //return list of single animal or none identified by unique animal id
    	if (animalID != null) {
        	int id = Integer.valueOf(animalID);
        	return this.animalService.searchAnimalById(id);
        }
    	//return list of multiple animals based on similarity by name or by name and species
        else if (name != null) {
            return this.animalService.searchAnimalByName(name, species, false);
        } 
        return null;
    }
    

    /**
     * 'POST' request that adds an animal to the database
     * @param animal = Animal to be added
     */
    @PostMapping
    public void addAnimal(@RequestBody Animal animal) throws Exception{
        int status;
        
        // Checks if animal fields are 'null'
        if (animal.anyNulls()) {
            throw new ApiRequestException("Fields cannot be blank");
        } 
        
        status = this.animalService.addAnimal(animal);
        
        // Check if ID already exists
        if (status == 0) {
            throw new InvalidIdException();
        }
    }

    /**
     * 'PUT' request that updates an animal's information
     * @param animalID = animal's ID number
     * @param animalToUpdate = Animal object with the updated information
     */
    @PutMapping(path = "/{id}")
    public void editAnimal(@PathVariable("id") String animalID, @RequestBody Animal animalToUpdate) throws Exception{
		int id = Integer.valueOf(animalID);
		if (animalToUpdate.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		int numRowsAffected = animalService.updateAnimalById(id, animalToUpdate);	
		if (numRowsAffected == 0) {throw new InvalidIdException();}
    }



}