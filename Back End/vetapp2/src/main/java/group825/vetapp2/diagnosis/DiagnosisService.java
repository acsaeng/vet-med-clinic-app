package group825.vetapp2.diagnosis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * Service that performs Diagnosis requests
 *
 * @author Timothy Mok
 * @version 1.0
 * @since November 15, 2021
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
		// UPDATE WITH DATABASE AS NEEDED
		this.repo = repo;
	}

	/**
	 * Service for adding a diagnosis to the system
	 * @param diagnosis diagnosis to be added
	 * @return whether the diagnosis was successfully added to the Repository
	 */
	public int addDiagnosis(Diagnosis diagnosis) throws Exception{
		return repo.addDiagnosis(diagnosis);
	}
	
	/**
	 * List all diagnoses in the system
	 * @return a list of all diagnoses associated with an animal
	 */
	public List<Diagnosis> selectAllDiagnosis(){
		return repo.selectAllDiagnosis();
	}
	
	/**
	 * Search for diagnoses for a specific animal in the Repository
	 * @param id id of the animal
	 * @return the diagnoses from the repository
	 */
	public List<Diagnosis> selectDiagnosisById(int id) throws Exception {
		ArrayList<String> results =  repo.selectDiagnosisById(id);
		List<Diagnosis> listResults = createListDiagnosis(results);
		return listResults;
//		return repo.selectDiagnosisById(id);
	}
	
	/**
	 * Delete a diagnosis from the Repository
	 * @param id diagnosis to be deleted
	 * @return whether the diagnosis was successfully deleted from the Repository
	 */
	public int deleteDiagnosisById(int id) throws Exception{
		return repo.deleteDiagnosisById(id);
	}
	
	/**
	 * Update an existing diagnosis in the Repository
	 * @param id diagnosis to be updated
	 * @param diagnosis diagnosis object containing new information
	 * @return whether the diagnosis was successfully updated the Repository
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
		//review against Database setup
		int idxAnimalID=6, idxDiagnosisID=0, idxDiagnosis=2, idxDescription=3, idxDiagnosisDate=1, idxDiangosisStatus=4, idxUserID=5;
		for (String result: foundResults) {
			String[] resultSplit = result.split(repo.getSplitPlaceholder());
		Diagnosis temp =  new Diagnosis( Integer.valueOf(resultSplit[idxDiagnosisID]), Integer.valueOf(resultSplit[idxAnimalID]), resultSplit[idxDiagnosis], 
				resultSplit[idxDescription], resultSplit[idxDiagnosisDate], resultSplit[idxDiangosisStatus], Integer.valueOf(resultSplit[idxUserID]));
		listResults.add(temp);
	}
	System.out.println("\nPrepared List to send as json response to API endpoint:");
	System.out.println(listResults);

	return listResults;
	}
	
	
	
}