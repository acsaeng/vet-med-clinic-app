package group825.vetapp.animal;

import group825.vetapp.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public int editAnimal(UUID id, String name, String type, String species, char sex, double weight) {
        return searchAnimalById(id).map(animal -> {
            int accountIdx = database.indexOf(animal);

            if (accountIdx >= 0) {
                database.get(accountIdx).setName(name);
                database.get(accountIdx).setType(type);
                database.get(accountIdx).setSpecies(species);
                database.get(accountIdx).setSex(sex);
                database.get(accountIdx).setWeight(weight);

                return 1;
            }

            return 0;
        }).orElse(0);
    }

    public Optional<Animal> searchAnimalByName(String name) {
        return database.stream().filter(animal -> animal.getName().equals(name)).findFirst();
    }

    public Optional<Animal> searchAnimalById(UUID id) {
        return database.stream().filter(animal -> animal.getId().equals(id)).findFirst();
    }


}
