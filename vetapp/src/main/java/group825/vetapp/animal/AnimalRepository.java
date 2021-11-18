package group825.vetapp.animal;

import group825.vetapp.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("tempAnimalRepo")
public class AnimalRepository {

    private static final List<Animal> database = new ArrayList<>();

    public List<Animal> selectAllAnimals() {
        return database;
    }

    public int addAnimal(Animal animal) {
        database.add(animal);
        return 1;
    }


}
