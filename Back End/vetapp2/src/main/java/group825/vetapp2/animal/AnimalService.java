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
    public AnimalService(@Qualifier("animalRepo") AnimalRepository repo) {
        this.repo = repo;
    }

    /**
     * Searches for an animal by ID number in the database
     * @param animalID animal's ID number
     * @return animal if found, null otherwise
     */
    public Animal searchAnimalById(int animalID) {
        return this.repo.searchAnimalById(animalID);
    }

    /**
     * Adds an animal to the database
     * @param animal animal to be added
     */
    public void addAnimal(Animal animal) {
        this.repo.addAnimal(animal);
    }

    /**
     * Updates an animal's information
     * @param animalID animal's ID number
     * @param animalInfo animal's updated information
     */
    public void updateAnimal(int animalID, Animal animalInfo) {
		this.repo.updateAnimal(animalID, animalInfo);
	}

//    /**
//     * Searches for an animal by name in the database
//     * @param name = animal's name
//     * @species = species of animal
//     * @param onlyAvailableAnimals = whether to check for only available animals or for all animals
//     * @return specified animal if found, null otherwise
//     */
//    public List<Animal> searchAnimalByName(String name, String species, boolean onlyAvailableAnimals) throws Exception{
//        ArrayList<String> foundResults = this.repo.searchAnimalByName(name, species, onlyAvailableAnimals);
//        List<Animal> listResults = createListAnimal(foundResults);
//        return listResults;
//    }

//    /**
//     * Searches for an animal by ID number in the database
//     * @param animalID = animal's ID number
//     * @return specified animal if found, null otherwise
//     * @throws Exception when there is an SQL Exception
//     */
//    public List<Animal> searchAnimalById(int animalID) throws Exception {
//    	ArrayList<String> results =  this.repo.searchAnimalById(animalID);
//		List<Animal> listResults = createListAnimal(results);
//		return listResults;
//    }
}