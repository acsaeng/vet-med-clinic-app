package group825.vetapp.animal;

import group825.vetapp.exceptions.ApiRequestException;
import group825.vetapp.exceptions.InvalidIdException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/app/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> selectAllAnimals() {
        return this.animalService.selectAllAnimals();
    }

    @PostMapping
    public void addAnimal(@RequestBody Animal animal) {
        // Checks if animal fields are 'null'
        if (animal.anyNulls()) {
            throw new ApiRequestException("Fields cannot be blank");
        } else {
            this.animalService.addAnimal(animal);
        }
    }

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

        // Check if ID exists
        if (this.animalService.editAnimal(id, newName, newType, newSpecies, newSex, newWeight) == 0) {
            throw new InvalidIdException();
        }
    }

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