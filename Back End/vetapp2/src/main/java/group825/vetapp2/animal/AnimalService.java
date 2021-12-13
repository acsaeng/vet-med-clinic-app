package group825.vetapp2.animal;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

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
     * Selects all animals in the database
     */
    public ArrayList<Animal> selectAllAnimals() {
        return this.repo.selectAllAnimals();
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

    /**
     * Searches for an animal by name in the database
     * @param name = animal's name
     * @species = species of animal
     * @param onlyAvailableAnimals = whether to check for only available animals or for all animals
     * @return specified animal if found, null otherwise
     */
    public List<Animal> searchAnimalByName(String name, String species, boolean onlyAvailableAnimals) throws Exception{
        ArrayList<String> foundResults = this.repo.searchAnimalByName(name, species, onlyAvailableAnimals);
        List<Animal> listResults = createListAnimal(foundResults);
        return listResults;
    }

    public List<Animal> createListAnimal(ArrayList<String> foundResults){
        List<Animal> listResults = new ArrayList<Animal>();
        //review against Database setup

        int idxAnimalID=0, idxName=1, idxBreed=3, idxSpecies=2, idxSex=7, idxAvailability=10, idxTattoo=4, idxCityTattoo=5,
                idxDob=6, idxRfid=8, idxMicrochip=9, idxStatus=11, idxColor=12, idxMoreInfo=13,
                idxNameLength=14, idxSearchKeyName=15;

        for (String result: foundResults) {
//            String[] resultSplit = result.split( repo.getSplitPlaceholder());
//    		for (int count=0; count <resultSplit.length; count++) {
//    			System.out.println(count + " - "+ resultSplit[count]);
//    		}
//    		System.out.println(Double.valueOf(resultSplit[idxWeight]));
//            Animal temp =  new Animal( Integer.valueOf(resultSplit[idxAnimalID]), resultSplit[idxName], resultSplit[idxSpecies], resultSplit[idxBreed],
//                    Integer.parseInt(resultSplit[idxSex]), resultSplit[idxTattoo], resultSplit[idxCityTattoo],
//                    resultSplit[idxDob], resultSplit[idxRfid], resultSplit[idxMicrochip], resultSplit[idxAvailability], resultSplit[idxStatus],
//                    resultSplit[idxColor], resultSplit[idxMoreInfo], Integer.valueOf(resultSplit[idxNameLength]),
//                    resultSplit[idxSearchKeyName]);
//            listResults.add(temp);
        }

        System.out.println("\nPrepared List to send as json response to API endpoint:");
        System.out.println(listResults);
        return listResults;
    }
}
