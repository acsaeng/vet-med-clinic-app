package group825.vetapp.animal;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository that stores Animal information
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
@Repository("tempAnimalRepo")
public class AnimalRepository {

    /**
     * Database that stores all the animals
     */
    private static final List<Animal> database = new ArrayList<>();

    /**
     * Retrieves all animals from the database
     * @return list of all animals
     */
    public List<Animal> selectAllAnimals() {
        return database;
    }

    /**
     * Adds an animal to the database
     * @param animal animal to be added
     * @return 1 if addition was successful, 0 otherwise
     */
    public int addAnimal(Animal animal) {
        database.add(animal);
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
    public int editAnimal(UUID id, String name, String type, String species, char sex, double weight) {
        return searchAnimalById(id).map(animal -> {
            // Finds the index of the selected animal in the database
            int accountIdx = database.indexOf(animal);

            // Updates the animal's information
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

    /**
     * Searches for an animal by name in the database
     * @param name animal's name
     * @return specified animal if found, null otherwise
     */
    public Optional<Animal> searchAnimalByName(String name) {
        return database.stream().filter(animal -> animal.getName().equals(name)).findFirst();
    }

    /**
     * Searches for an animal by ID number in the database
     * @param id animal's ID number
     * @return specified animal if found, null otherwise
     */
    public Optional<Animal> searchAnimalById(UUID id) {
        return database.stream().filter(animal -> animal.getId().equals(id)).findFirst();
    }
}