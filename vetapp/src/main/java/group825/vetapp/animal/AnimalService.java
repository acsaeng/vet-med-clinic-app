package group825.vetapp.animal;

import group825.vetapp.user.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public int editAnimal(UUID id, String name, String type, String species, char sex, double weight) {
        return this.repo.editAnimal(id, name, type, species, sex, weight);
    }

    public Optional<Animal> searchAnimalByName(String name) {
        return this.repo.searchAnimalByName(name);
    }

    public Optional<Animal> searchAnimalById(UUID id) {
        return this.repo.searchAnimalById(id);
    }






}
