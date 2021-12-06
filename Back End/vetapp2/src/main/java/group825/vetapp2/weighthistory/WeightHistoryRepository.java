package group825.vetapp2.weighthistory;

import group825.vetapp2.database.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * Repository that stores WeightHistory information
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since December 6, 2021
 */
@Repository("tempWeightHistoryRepo")
public class WeightHistoryRepository {

    /**
     * Database table name
     */
    private final String tableName = "WEIGHT_HISTORY";

    /**
     * Connector to the database
     */
    private DatabaseConnection dao;


    public WeightHistoryRepository() {
        dao = new DatabaseConnection();
    }

    /**
     * Searches for an animal's weight history by ID number in the database
     * @param animalID animal's ID number
     * @return list containing the response of the query
     * @throws Exception error when accessing the database
     */
    public ArrayList<String> selectWeightHistoryByID(int animalID) throws Exception {
        String query = "SELECT * FROM WEIGHT_HISTORY WHERE Animal_ID = '" + animalID + "';";
        return dao.getResponseArrayList(query);
    }

    /**
     * Adds a weight entry to the database
     * @param weight animal's weight entry
     * @throws Exception error when accessing the database
     */
    public void addWeight(Weight weight) throws Exception {
        String query = "INSERT INTO WEIGHT_HISTORY VALUES (" + weight.getAnimalId() + ", '" + weight.getDate() + "', " +
                weight.getWeight() + ");";
        dao.manipulateRows(query);
    }

    /**
     * Deletes an animal's weight entry
     * @param weight animal's weight entry
     * @throws Exception error when accessing the database
     */
    public void deleteWeight(Weight weight) throws Exception {
        String query = "DELETE FROM WEIGHT_HISTORY WHERE Animal_ID = '" + weight.getAnimalId() +
                "' AND Date_Recorded = '" + weight.getDate() + "';";

        dao.manipulateRows(query);
    }

    /**
     * Stores the placeholder to split an incoming string
     * @return a split placeholder
     */
    public String getSplitPlaceholder() {
        return dao.getSplitPlaceholder();
    }
}