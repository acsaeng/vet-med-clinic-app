package group825.vetapp2.weighthistory;

import group825.vetapp2.database.DatabaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Repository that stores WeightHistory information
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since December 6, 2021
 */
@Repository("weightHistoryRepo")
public class WeightHistoryRepository {

    /**
     * Connector to the database
     */
    private final Connection dao;

    /**
     * Database table name
     */
    private final String tableName;

    /**
     * Results of a query to the database
     */
    private ResultSet results;

    /**
     * Class constructor that initializes the WeightHistoryRepository
     */
    public WeightHistoryRepository() {
        this.dao = DatabaseConnection.getConnection();
        this.tableName = "WEIGHT_HISTORY";
    }

    /**
     * Searches for an animal's weight history by ID number in the database
     * @param animalID animal's ID number
     * @return list containing the response of the query
     * @throws SQLException error when accessing the database
     */
    public ArrayList<Weight> selectWeightHistoryByID(int animalID) {
        ArrayList<Weight> weightHistory = new ArrayList<Weight>();

        try {
            // Execute SQL query
            PreparedStatement statement = this.dao.prepareStatement("SELECT * FROM WEIGHT_HISTORY WHERE Animal_ID = ?");
            statement.setInt(1, animalID);
            results = statement.executeQuery();

            // Process the results set and add entries into weight history
            while (results.next()) {
                weightHistory.add(new Weight(results.getInt("Animal_ID"), results.getString("Date_Recorded"), results.getDouble("Weight")));
            }

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return weightHistory;
    }











//    public ArrayList<Weight> selectWeightHistoryByID(int animalID) throws SQLException {
//        String query = "SELECT * FROM WEIGHT_HISTORY WHERE Animal_ID = '" + animalID + "';";
//        return dao.getResponseArrayList(query);
//    }
//
//    /**
//     * Adds a weight entry to the database
//     * @param weight animal's weight entry
//     * @throws Exception error when accessing the database
//     */
//    public void addWeight(Weight weight) throws Exception {
//        String query = "INSERT INTO WEIGHT_HISTORY VALUES (" + weight.getAnimalId() + ", '" + weight.getDate() + "', " +
//                weight.getWeight() + ");";
//        dao.manipulateRows(query);
//    }

//    /**
//     * Deletes an animal's weight entry
//     * @param weight animal's weight entry
//     * @throws Exception error when accessing the database
//     */
//    public void deleteWeight(Weight weight) throws Exception {
//        String query = "DELETE FROM WEIGHT_HISTORY WHERE Animal_ID = '" + weight.getAnimalId() +
//                "' AND Date_Recorded = '" + weight.getDate() + "';";
//
//        dao.manipulateRows(query);
//    }
//
//    /**
//     * Stores the placeholder to split an incoming string
//     * @return a split placeholder
//     */
//    public String getSplitPlaceholder() {
//        return dao.getSplitPlaceholder();
//    }
}