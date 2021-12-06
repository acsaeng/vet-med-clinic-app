package group825.vetapp2.treatment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.Application_DbConnection;

/**
 * Repository that stores Treatment information
 *
 * @author Timothy Mok
 * @version 2.0
 * @since December 6, 2021
 */

@Repository("tempTreatmentRepo")
public class TreatmentRepository {
	
	/**
	 * Name of the table in the database
	 */
	private String tableName = "TREATMENT";
	
	/**
	 * Connector to the database
	 */
	private Application_DbConnection dao;
	
	/**
	 * The query run in the database
	 */
	private String query;
	
	/**
	 * The latest treatment ID to be associated with new treatments
	 */
	private int latestID;
	
	/**
	 * Database that stores all the diagnoses
	 */
	private static final List<Treatment> dbTreatment = new ArrayList<>();
	
	
	/**
	 * Constructor for the TreatmentRepository that connects to the database
	 * @throws Exception when there is an SQL Exception
	 */
	public TreatmentRepository() throws Exception {
		dao = new Application_DbConnection();
		getLatestTreatmentId();
	}
	
	/**
	 * Adds a treatment to an animal
	 * @param treatment the treatment associated with an animal
	 * @return 1 if successfully added, 0 otherwise
	 * @throws Exception when there is an SQL Exception
	 */
	public int addTreatment(Treatment treatment) throws Exception{
		String queryBegin = "INSERT INTO TREATMENT (Treatment_ID, TreatmentDate, Treatment, TreatmentDescription, Treatment_Status, User_ID, Animal_ID) VALUES";
		query = queryBegin + "('" + treatment.getTreatmentID() + "', '" + treatment.getTreatmentDate() + "', '" +  treatment.getTreatment()  
		+ "', '" + treatment.getDescription() + "', '" +treatment.getTreatmentStatus()  + "', '" + treatment.getUserID() + "', '" + treatment.getAnimalID()+"');";
		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			this.getLatestTreatmentId();
			
			query = queryBegin + "('" + (this.latestID+1) + "', '" + treatment.getTreatmentDate() + "', '" +  treatment.getTreatment()  
			+ "', '" + treatment.getDescription() + "', '" +treatment.getTreatmentStatus()  + "', '" + treatment.getUserID() + "', '" + treatment.getAnimalID()+"');";
			System.out.println(query);
			int responseCheck = dao.manipulateRows(query);
		}
		return 1;
	}
	
	/**
	 * View all treatments selected for an animal
	 * @param animalID the id of the selected animal
	 * @return the treatments for the animal with the requested id
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectTreatmentByAnimalId(int animalID) throws Exception{
		query = "SELECT * FROM TREATMENT WHERE Animal_ID='" + animalID +"';";
		System.out.println("query = "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/**
	 * Find a specific treatment selected by ID
	 * @param animalID the id of the selected animal
	 * @param treatmentID the ID of the treatment requested
	 * @return the specific treatment for an animal
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectTreatmentByTreatmentId(int animalID, int treatmentID) throws Exception {
		query = "SELECT FROM TREATMENT WHERE Animal_ID='" + animalID +" AND Treatment_ID='" + treatmentID + "';";
		ArrayList<String> result = dao.getResponseArrayList(query);
		return result;
	}
	
	/**
	 * Remove a specific treatment from the system
	 * @param id the id of the treatment to be removed
	 * @return 1 if removed successfully, 0 otherwise
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteTreatmentById(int treatmentID) throws Exception{
		String query = "DELETE FROM "+ tableName + " AS D WHERE D.Treatment_ID='"+treatmentID+"';";
		System.out.println("query for delete: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	
	/**
	 * Update an existing treatment
	 * @param id the id of the treatment to be updated
	 * @param newTreatment the Treatment object with the updated information
	 * @return 1 if successfully updated, 0 otherwise
	 * @throws Exception when there is an SQL Exception
	 */
	public int updateTreatmentById(int treatmentID, Treatment update) throws Exception{
		 String query = "UPDATE " + tableName + " AS T SET Treatment_ID='" + update.getTreatmentID() 
		 + "', TreatmentDate='" + update.getTreatmentDate() +"', Treatment='" + update.getTreatment() 
		 +"', TreatmentDescription='" + update.getDescription() +"', Treatment_Status='" + update.getTreatmentStatus() 
		 + "', User_ID='" + update.getUserID() + "', Animal_ID='" + update.getAnimalID() +
		 "' WHERE T.Treatment_ID='"+treatmentID+"';";
		 System.out.println("query = "+query);
		 int responseCheck = dao.manipulateRows(query);
		 return responseCheck;
	}
	
	/**
	 * get the latest Id for the primary key for Treatment object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestTreatmentId() throws Exception{
		String queryMaxId = "SELECT MAX(T.Treatment_ID) FROM TREATMENT AS T";
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.latestID = Integer.valueOf(latestId);
	}
	
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}
}


