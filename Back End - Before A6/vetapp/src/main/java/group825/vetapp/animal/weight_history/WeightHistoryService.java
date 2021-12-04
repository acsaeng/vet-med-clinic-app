package group825.vetapp.animal.weight_history;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Service that performs WeightHistory requests
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
@Service
public class WeightHistoryService {

    /**
     * Weight history repository that accesses the database
     */
    private final WeightHistoryRepository repo;

    /**
     * Constructor that initializes the WeightHistoryService
     * @param repo repository that accesses the database
     */
    public WeightHistoryService(WeightHistoryRepository repo) {
        this.repo = repo;
    }

    /**
     * Retrieves all weight histories from the database
     * @return a list of weight entries of all animals
     */
    public List<Weight> selectWeightHistory() {
        return this.repo.selectWeightHistory();
    }

    /**
     * Adds a weight entry to the database
     * @param weight animal's weight entry
     * @return 1 if addition was successful, 0 otherwise
     */
    public int addWeight(Weight weight) {
        return this.repo.addWeight(weight);
    }

    /**
     * Updates an animal's weight entry
     * @param id animal's ID number
     * @param date date that weight was logged
     * @param weight animal's weight
     * @return 1 if the update was successful, 0 otherwise
     */
    public int editWeight(UUID id, LocalDate date, double weight) {
        return this.repo.editWeight(id, date, weight);
    }

    /**
     * Deletes an animal's weight entry
     * @param id animal's ID number
     * @param date date that weight was logged
     * @return 1 if the deletion was successful, 0 otherwise
     */
    public int deleteWeight(UUID id, LocalDate date) {
        return this.repo.deleteWeight(id, date);
    }
}