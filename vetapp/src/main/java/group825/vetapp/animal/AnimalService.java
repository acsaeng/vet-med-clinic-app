package group825.vetapp.animal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service that performs Animal requests
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
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
    public List<Animal> selectAllAnimals() {
        return this.repo.selectAllAnimals();
    }

    /**
     * Adds an animal to the database
     * @param animal animal to be added
     * @return 1 if addition was successful, 0 otherwise
     */
    public int addAnimal(Animal animal) {
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
    public int editAnimal(UUID id, String name, String type, String species, char sex, double weight) {
        return this.repo.editAnimal(id, name, type, species, sex, weight);
    }

    /**
     * Searches for an animal by name in the database
     * @param name animal's name
     * @return specified animal if found, null otherwise
     */
    public Optional<Animal> searchAnimalByName(String name) {
        return this.repo.searchAnimalByName(name);
    }

    /**
     * Searches for an animal by ID number in the database
     * @param id animal's ID number
     * @return specified animal if found, null otherwise
     */
    public Optional<Animal> searchAnimalById(UUID id) {
        return this.repo.searchAnimalById(id);
    }
}