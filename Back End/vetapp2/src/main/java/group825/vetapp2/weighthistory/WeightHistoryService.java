package group825.vetapp2.weighthistory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
/**
 * Service that performs WeightHistory requests
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since December 6, 2021
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
    public WeightHistoryService(@Qualifier("tempWeightHistoryRepo") WeightHistoryRepository repo) {
        this.repo = repo;
    }

    /**
     * Retrieves all weight histories from the database
     * @return a list of weight entries of all animals
     * @throws Exception error when accessing the database
     */
    public ArrayList<Weight> selectWeightHistoryByID(int animalID) throws Exception {
        ArrayList<String> results = this.repo.selectWeightHistoryByID(animalID);
        ArrayList<Weight> weightHistory = new ArrayList<Weight>();

        for(String result: results) {
            if(result.startsWith(String.valueOf(animalID))) {
                String[] entry = result.split(repo.getSplitPlaceholder());
                weightHistory.add(new Weight(Integer.parseInt(entry[0]), entry[1], Double.parseDouble(entry[2])));
            }
        }

        return weightHistory;
    }

    /**
     * Adds a weight entry to the database
     * @param weight animal's weight entry
     * @return 1 if addition was successful, 0 otherwise
     * @throws Exception error when accessing the database
     */
    public void addWeight(Weight weight) throws Exception {
        this.repo.addWeight(weight);
    }

    /**
     * Deletes an animal's weight entry
     * @param weight animal's weight entry
     * @throws Exception error when accessing the database
     */
    public void deleteWeight(Weight weight) throws Exception {
        this.repo.deleteWeight(weight);
    }
}