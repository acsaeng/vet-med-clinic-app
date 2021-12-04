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
     * 'GET' request that searches for an animal in the database
     * @param name animal's name
     * @param strId animal's ID number
     * @return specified animal if found, null otherwise
     */
    @GetMapping(path = "/searchAvailable")
    public List<Animal> searchAnimalAvailable(@RequestParam(required = false) String name,@RequestParam(name = "species", required = false) String species, @RequestParam(name = "id", required = false) String strId) throws Exception{
        if (name != null) {
        	boolean onlyAvailableAnimals = true;
            return this.animalService.searchAnimalByName(name, species, onlyAvailableAnimals);
        } 
        return null;
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
	 * 'GET' mapping that searches for one animal in the database
	 * @param idStr String path variable obtained by path denoted inside the GetMapping annotation
	 * @return comment object or throw exception
	 */
	@GetMapping(path="{id}") 
	public List<Animal> selectAnimalById(@PathVariable("id") String idStr) {
		try {
			//id of animal
			int id = Integer.valueOf(idStr);
			return animalService.searchAnimalById(id);
		} catch(Exception e) {
			// Catch if id is not a valid Animal ID from Database
			throw new InvalidIdException();
		}
	}

    /**
     * 'POST' request that adds an animal to the database
     * @param animal animal to be added
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
     * @param strId animal's ID number
     */
    @PutMapping(path = "/{id}")
    public void editAnimal(@PathVariable("id") String animalID, @RequestBody Animal animalToUpdate) throws Exception{
    	//id of a animal
		int id = Integer.valueOf(animalID);
		if (animalToUpdate.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		int numRowsAffected = animalService.updateAnimalById(id, animalToUpdate);	
		if (numRowsAffected == 0) {throw new InvalidIdException();}
    	
    	
//        // User inputs this information
//        String newName = "Richard Parker";
//        String newType = "Tiger";
//        String newSpecies = "Bengal";
//        char newSex = 'M';
//        double newWeight = 12.4;
//
//        UUID id;
//
//        // Check if ID is a valid UUID
//        try {
//            id = UUID.fromString(strId);
//            animalService.editAnimal(id, newName, newType, newSpecies, newSex, newWeight);
//        } catch(java.lang.IllegalArgumentException e) {
//            throw new InvalidIdException();
//        }
    }

    /**
     * 'GET' request that searches for an animal in the database
     * @param name animal's name
     * @param strId animal's ID number
     * @return specified animal if found, null otherwise
     */
    @GetMapping(path = "/search")
    public List<Animal> searchAnimal(@RequestParam(required = false) String name,@RequestParam(name = "species", required = false) String species, @RequestParam(name = "id", required = false) String strId) throws Exception{
        if (name != null) {
            return this.animalService.searchAnimalByName(name, species, false);
        } 
        return null;
    }

}