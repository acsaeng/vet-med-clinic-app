package group825.vetapp.animal;

import group825.vetapp.exceptions.ApiRequestException;
import group825.vetapp.exceptions.InvalidIdException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controller that handles Animal requests
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
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
    public List<Animal> selectAllAnimals() {
        return this.animalService.selectAllAnimals();
    }

    /**
     * 'POST' request that adds an animal to the database
     * @param animal animal to be added
     */
    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        int status;

        // Checks if animal fields are 'null'
        if (animal.anyNulls()) {
            throw new ApiRequestException("Fields cannot be blank");
        } else {
            status = this.animalService.addAnimal(animal);
        }

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
    public void editAnimal(@PathVariable("id") String strId) {
        // User inputs this information
        String newName = "Richard Parker";
        String newType = "Tiger";
        String newSpecies = "Bengal";
        char newSex = 'M';
        double newWeight = 12.4;

        UUID id;

        // Check if ID is a valid UUID
        try {
            id = UUID.fromString(strId);
            animalService.editAnimal(id, newName, newType, newSpecies, newSex, newWeight);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
    }

    /**
     * 'GET' request that searches for an animal in the database
     * @param name animal's name
     * @param strId animal's ID number
     * @return specified animal if found, null otherwise
     */
    @GetMapping(path = "/search")
    public Optional<Animal> searchAnimal(@RequestParam(required = false) String name, @RequestParam(name = "id", required = false) String strId) {
        if (name != null) {
            return this.animalService.searchAnimalByName(name);
        } else if (strId != null) {
            UUID id;

            // Check if ID is a valid UUID
            try {
                id = UUID.fromString(strId);
                return animalService.searchAnimalById(id);
            } catch(java.lang.IllegalArgumentException e) {
                throw new InvalidIdException();
            }
        } else {
            return Optional.empty();
        }
    }
}