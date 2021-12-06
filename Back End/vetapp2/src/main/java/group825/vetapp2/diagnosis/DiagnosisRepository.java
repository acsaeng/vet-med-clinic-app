package group825.vetapp2.diagnosis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.DatabaseConnection;

/**
 * Repository that stores Diagnosis information
 *
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since December 5, 2021
 */

@Repository("tempDiagnosisRepo")
public class DiagnosisRepository {
	String table_name = "DIAGNOSIS";
	DatabaseConnection dao;
	String query;
	int latestID;
	
	public DiagnosisRepository() throws Exception {
		dao = new DatabaseConnection();
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
	 */
	public int addDiagnosis(Diagnosis diagnosis) throws Exception{
		String queryBegin = "INSERT INTO DIAGNOSIS (Diagnosis_ID, DiagnosisDate, Diagnosis, DiagnosisDescription, Diagnosis_Status, User_ID, Animal_ID) VALUES";
		query = queryBegin + "('" + diagnosis.getDiagnosisID() + "', '" + diagnosis.getDiagnosisDate() + "', '" +  diagnosis.getDiagnosis()  
		+ "', '" + diagnosis.getDescription() + "', '" +diagnosis.getDiangosisStatus()  + "', '" + diagnosis.getUserID() + "', '" + diagnosis.getAnimalID()+"');";
		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			this.getLatestDiagnosisId();
			
			query = queryBegin + "('" + (this.latestID+1) + "', '" + diagnosis.getDiagnosisDate() + "', '" +  diagnosis.getDiagnosis()  
			+ "', '" + diagnosis.getDescription() + "', '" +diagnosis.getDiangosisStatus()  + "', '" + diagnosis.getUserID() + "', '" + diagnosis.getAnimalID()+"');";
			System.out.println(query);
			int responseCheck = dao.manipulateRows(query);
		}
		return 1;
		
	
//		UUID id = UUID.randomUUID();
//		dbDiagnosis.add(new Diagnosis(id, diagnosis.getAnimalName(), diagnosis.getDiagnosis(), diagnosis.getDescription()));
//		return 1;
	}
	
	/**
	 * Returns all diagnoses in the system associated with this animal
	 * @return all associated diagnoses
	 */
	public List<Diagnosis> selectAllDiagnosis(){
		return dbDiagnosis;
	}
	
	/**
	 * View a specific diagnosis selected by id
	 * @param id the id of the selected diagnosis
	 * @return the diagnosis with the requested id
	 */
	public ArrayList<String> selectDiagnosisById(int diagnosisID) throws Exception{
		query = "SELECT * FROM DIAGNOSIS WHERE Animal_ID='" + diagnosisID +"';";
		System.out.println("query = "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
//	public Optional<Diagnosis> selectDiagnosisById(UUID id) {
//		return dbDiagnosis.stream().filter(diagnosis -> diagnosis.getId().equals(id)).findFirst();
	}
	
	/**
	 * Remove a specific diagnosis from the system
	 * @param id the id of the diagnosis to be removed
	 * @return 1 if removed successfully, 0 otherwise
	 */
	public int deleteDiagnosisById(int diagnosisID) throws Exception{
		String query = "DELETE FROM "+ table_name + " AS D WHERE D.Diagnosis_ID='"+diagnosisID+"';";
		System.out.println("query for delete: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
		
		
//		Optional<Diagnosis> diagnosisMaybe = selectDiagnosisById(id);
//
//		if(diagnosisMaybe.isEmpty()) { 
//			return 0;
//		}
//
//		dbDiagnosis.remove(diagnosisMaybe.get());
	}
	
	/**
	 * Update an existing diagnosis
	 * @param id the id of the diagnosis to be updated
	 * @param newDiagnosis the Diagnosis object with the updated information
	 * @return 1 if successfully updated, 0 otherwise
	 */
	public int updateDiagnosisById(int diagnosisID, Diagnosis update) throws Exception{
		 String query = "UPDATE " + table_name + " AS D SET Diagnosis_ID='" + update.getDiagnosisID() 
		 + "', DiagnosisDate='" + update.getDiagnosisDate() +"', Diagnosis='" + update.getDiagnosis() 
		 +"', DiagnosisDescription='" + update.getDescription() +"', Diagnosis_Status='" + update.getDiangosisStatus() 
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
//		System.out.println(queryMaxId);
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.latestID = Integer.valueOf(latestId);
	}
	
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}
	
	
	
}


