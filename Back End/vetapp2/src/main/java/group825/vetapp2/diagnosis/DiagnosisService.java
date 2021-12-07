package group825.vetapp2.diagnosis;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * Service that performs Diagnosis requests
 *
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since December 6, 2021
 */
@Service
public class DiagnosisService {

	/**
	 * Diagnosis repository that accesses the database
	 */
	private final DiagnosisRepository repo;

	/**
	 * Constructor for the DiagnosisService to communicate between the Repository and Controller
	 * @param repo the DiagnosisRepository
	 */
	public DiagnosisService(@Qualifier("tempDiagnosisRepo") DiagnosisRepository repo) {
		this.repo = repo;
	}

	/**
	 * Service for adding a diagnosis to the system
	 * @param diagnosis diagnosis to be added
	 * @return whether the diagnosis was successfully added to the Repository
	 * @throws Exception when there is an SQL Exception
	 */
	public int addDiagnosis(Diagnosis diagnosis) throws Exception{
		return repo.addDiagnosis(diagnosis);
	}
	
	/**
	 * Search for diagnoses for a specific animal in the Repository
	 * @param animalID id of the animal
	 * @return the diagnoses from the repository
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Diagnosis> selectDiagnosisByAnimalID(int animalID) throws Exception {
		ArrayList<String> results =  repo.selectDiagnosisByAnimalID(animalID);
		List<Diagnosis> listResults = createListDiagnosis(results);
		return listResults;
	}
	
	/**
	 * Search for a specific for a specific animal in the Repository
	 * @param animalID id of the animal
	 * @param diagnosisID the diagnosis ID requested
	 * @return the single diagnosis from the repository
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Diagnosis> selectDiagnosisByDiagnosisID(int diagnosisID) throws Exception {
		ArrayList<String> results =  repo.selectDiagnosisByDiagnosisID(diagnosisID);
		List<Diagnosis> listResults = createListDiagnosis(results);
		return listResults;
	}
	
	/**
	 * Delete a diagnosis from the Repository
	 * @param id diagnosis to be deleted
	 * @return whether the diagnosis was successfully deleted from the Repository
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteDiagnosisById(int id) throws Exception{
		return repo.deleteDiagnosisById(id);
	}
	
	/**
	 * Update an existing diagnosis in the Repository
	 * @param id diagnosis to be updated
	 * @param diagnosis diagnosis object containing new information
	 * @return whether the diagnosis was successfully updated the Repository
	 * @throws Exception when there is an SQL Exception
	 */
	public int updateDiagnosisById(int id, Diagnosis diagnosis) throws Exception{
		return repo.updateDiagnosisById(id, diagnosis);
	}
	
	 /**
	  * Create a list of Diagnosis objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Diagnosis> where each object was created from the data in each String from the ArrayList input
	 */
	public List<Diagnosis> createListDiagnosis(ArrayList<String> foundResults){
		List<Diagnosis> listResults = new ArrayList<Diagnosis>(); 
		int idxAnimalID=6, idxDiagnosisID=0, idxDiagnosis=2, idxDescription=3, idxDiagnosisDate=1, idxDiagnosisStatus=4, idxUserID=5;
		for (String result: foundResults) {
			String[] resultSplit = result.split(repo.getSplitPlaceholder());
			Diagnosis temp =  new Diagnosis( Integer.valueOf(resultSplit[idxDiagnosisID]), Integer.valueOf(resultSplit[idxAnimalID]), resultSplit[idxDiagnosis], 
				resultSplit[idxDescription], resultSplit[idxDiagnosisDate], resultSplit[idxDiagnosisStatus], Integer.valueOf(resultSplit[idxUserID]));
			listResults.add(temp);
		}
		System.out.println("\nPrepared List to send as json response to API endpoint:");
		System.out.println(listResults);
	
		return listResults;
	}
}