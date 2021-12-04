package group825.vetapp.animal.weight_history;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository that stores WeightHistory information
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
 */
@Repository("tempWeightHistoryRepo")
public class WeightHistoryRepository {

    /**
     * Database that stores all the weight histories
     */
    private static final List<Weight> database = new ArrayList<>();

    /**
     * Retrieves all weight histories from the database
     * @return a list of weight entries of all animals
     */
    public List<Weight> selectWeightHistory() {
        return database;
    }

    /**
     * Adds a weight entry to the database
     * @param weight animal's weight entry
     * @return 1 if addition was successful, 0 otherwise
     */
    public int addWeight(Weight weight) {
        database.add(weight);
        return 1;
    }

    /**
     * Updates an animal's weight entry
     * @param id animal's ID number
     * @param date date that weight was logged
     * @param weight animal's weight
     * @return 1 if the update was successful, 0 otherwise
     */
    public int editWeight(UUID id, LocalDate date, double weight) {
        return selectWeightById(id).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.get(accountIdx).setDate(date);
                database.get(accountIdx).setWeight(weight);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    /**
     * Searches for an animal's weight entry by ID number in the database
     * @param id animal's ID number
     * @return weight entry if found, null otherwise
     */
    private Optional<Weight> selectWeightById(UUID id) {
        return database.stream().filter(weightHistory -> weightHistory.getId().equals(id)).findFirst();
    }

    /**
     * Deletes an animal's weight entry
     * @param id animal's ID number
     * @param date date that weight was logged
     * @return 1 if the deletion was successful, 0 otherwise
     */
    public int deleteWeight(UUID id, LocalDate date) {
        return selectWeightByIdAndDate(id, date).map(user -> {
            int accountIdx = database.indexOf(user);

            if (accountIdx >= 0) {
                database.remove(accountIdx);
                return 1;
            }

            return 0;
        }).orElse(0);
    }

    /**
     * Searches for an animal's weight entry by ID number and date in the database
     * @param id animal's ID number
     * @param date date that weight was logged
     * @return weight entry if found, null otherwise
     */
    private Optional<Weight> selectWeightByIdAndDate(UUID id, LocalDate date) {
        return database.stream().filter(weightHistory -> weightHistory.getId().equals(id) && weightHistory.getDate().equals(date)).findFirst();
    }
}