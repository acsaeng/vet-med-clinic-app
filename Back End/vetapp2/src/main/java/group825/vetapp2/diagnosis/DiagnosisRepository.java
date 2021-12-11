package group825.vetapp2.diagnosis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.OldDatabaseConnection;

/**
 * Repository that stores Diagnosis information
 *
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since December 5, 2021
 */

@Repository("tempDiagnosisRepo")
public class DiagnosisRepository {
	
	/**
	 * Name of table in SQL database
	 */
	private String tableName = "DIAGNOSIS";
	
	/**
	 * Connection to SQL database
	 */
	private OldDatabaseConnection dao;
	
	/**
	 * SQL query to be submitted for requests
	 */
	private String query;
	
	/**
	 * Latest diagnosis ID that will be unique for each diagnosis
	 */
	int latestID;
	
	/**
	 * Constructor for DiagnosisRepository
	 * @throws Exception
	 */
	public DiagnosisRepository() throws Exception {
		dao = new OldDatabaseConnection();
		getLatestDiagnosisId();
	}
	
	/**
	 * Database that stores all the diagnoses
	 */
	private static final List<Diagnosis> dbDiagnosis = new ArrayList<>();
	
	/**
	 * Adds a diagnosis to an animal
	 * @param diagnosis the diagnosis associated with an animal
	 * @return 1 if successfully added, 0 otherwise
	 * @throws Exception when there is an SQL Exception
	 */
	public int addDiagnosis(Diagnosis diagnosis) throws Exception{
		String queryBegin = "INSERT INTO DIAGNOSIS (Diagnosis_ID, DiagnosisDate, Diagnosis, DiagnosisDescription, Diagnosis_Status, User_ID, Animal_ID) VALUES";
		query = queryBegin + "('" + diagnosis.getDiagnosisID() + "', '" + diagnosis.getDiagnosisDate() + "', '" +  diagnosis.getDiagnosis()  
		+ "', '" + diagnosis.getDescription() + "', '" +diagnosis.getDiagnosisStatus()  + "', '" + diagnosis.getUserID() + "', '" + diagnosis.getAnimalID()+"');";
		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			this.getLatestDiagnosisId();
			
			query = queryBegin + "('" + (this.latestID+1) + "', '" + diagnosis.getDiagnosisDate() + "', '" +  diagnosis.getDiagnosis()  
			+ "', '" + diagnosis.getDescription() + "', '" +diagnosis.getDiagnosisStatus()  + "', '" + diagnosis.getUserID() + "', '" + diagnosis.getAnimalID()+"');";
			System.out.println(query);
			int responseCheck = dao.manipulateRows(query);
		}
		return 1;
	}
	
	/**
	 * View all specific diagnosis selected by animal id
	 * @param animalID the id of the selected animal
	 * @return the diagnosis of the requested animal
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectDiagnosisByAnimalID(int animalID) throws Exception{
		query = "SELECT * FROM DIAGNOSIS WHERE Animal_ID='" + animalID +"';";
		System.out.println("query = "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/**
	 * Find a specific diagnosis selected by ID
	 * @param animalID the id of the selected animal
	 * @param diagnosisID the ID of the diagnosis requested
	 * @return the specific diagnosis for an animal
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectDiagnosisByDiagnosisID(int diagnosisID) throws Exception {
		query = "SELECT * FROM DIAGNOSIS WHERE Diagnosis_ID='" + diagnosisID + "';";
		ArrayList<String> result = dao.getResponseArrayList(query);
		return result;
	}
	
	/**
	 * Remove a specific diagnosis from the system
	 * @param id the id of the diagnosis to be removed
	 * @return 1 if removed successfully, 0 otherwise
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteDiagnosisById(int diagnosisID) throws Exception{
		String query = "DELETE FROM "+ tableName + " AS D WHERE D.Diagnosis_ID='"+diagnosisID+"';";
		System.out.println("query for delete: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * Update an existing diagnosis
	 * @param id the id of the diagnosis to be updated
	 * @param newDiagnosis the Diagnosis object with the updated information
	 * @return 1 if successfully updated, 0 otherwise
	 * @throws Exception when there is an SQL Exception
	 */
	public int updateDiagnosisById(int diagnosisID, Diagnosis update) throws Exception{
		 String query = "UPDATE " + tableName + " AS D SET Diagnosis_ID='" + update.getDiagnosisID() 
		 + "', DiagnosisDate='" + update.getDiagnosisDate() +"', Diagnosis='" + update.getDiagnosis() 
		 +"', DiagnosisDescription='" + update.getDescription() +"', Diagnosis_Status='" + update.getDiagnosisStatus() 
		 + "', User_ID='" + update.getUserID() + "', Animal_ID='" + update.getAnimalID() +
		 "' WHERE D.Diagnosis_ID='"+diagnosisID+"';";
		 System.out.println("query = "+query);
		 int responseCheck = dao.manipulateRows(query);
		 return responseCheck;
	}
	
	/**
	 * get the latest Id for the primary key for Diagnosis object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestDiagnosisId() throws Exception{
		String queryMaxId = "SELECT MAX(D.Diagnosis_ID) FROM DIAGNOSIS AS D";
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.latestID = Integer.valueOf(latestId);
	}
	
	/**
	 * Splits the dao placeholder
	 * @return the split string
	 */
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}
}


