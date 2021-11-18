package group825.vetapp.animal;

import group825.vetapp.exceptions.ApiRequestException;
import group825.vetapp.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            throw new ApiRequestException("ID, name, type, species, and sex cannot be blank");
        } else {
            this.animalService.addAnimal(animal);
        }
    }



    // Update animal PUT
    // Search animal by name
    // Search animal by ID
}
