package group825.vetapp.animal;

import group825.vetapp.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalRepository repo;

    public AnimalService(@Qualifier("tempAnimalRepo") AnimalRepository repo) {
        this.repo = repo;
    }

    public List<Animal> selectAllAnimals() {
        return this.repo.selectAllAnimals();
    }

    public int addAnimal(Animal animal) {
        return this.repo.addAnimal(animal);
    }






}
