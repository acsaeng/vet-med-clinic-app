package group825.vetapp2.treatment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * Service that performs Treatment requests
 *
 * @author Timothy Mok
 * @version 2.0
 * @since December 6, 2021
 */
@Service
public class TreatmentService {

	/**
	 * Treatment repository that accesses the database
	 */
	private final TreatmentRepository repo;

	/**
	 * Constructor for the TreatmentService to communicate between the Repository and Controller
	 * @param repo the TreatmentRepository
	 */
	public TreatmentService(@Qualifier("tempTreatmentRepo") TreatmentRepository repo) {
		this.repo = repo;
	}

	/**
	 * Service for adding a treatment to the system
	 * @param treatment treatment to be added
	 * @return whether the treatment was successfully added to the Repository
	 * @throws Exception when there is an SQL Exception
	 */
	public int addTreatment(Treatment treatment) throws Exception{
		return repo.addTreatment(treatment);
	}
	
//	/**
//	 * List all diagnoses in the system
//	 * @return a list of all diagnoses associated with an animal
//	 */
//	public List<Treatment> selectAllTreatment(){
//		return repo.selectAllTreatment();
//	}
	
	// TREATMENT BY TREATMENT ID?
	
	/**
	 * Search for treatments for a specific animal in the Repository
	 * @param animalID id of the animal
	 * @return the treatments from the repository
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Treatment> selectTreatmentByAnimalId(int animalID) throws Exception {
		ArrayList<String> results =  repo.selectTreatmentByAnimalId(animalID);
		List<Treatment> listResults = createListTreatment(results);
		return listResults;
	}
	
	/**
	 * Delete a treatment from the Repository
	 * @param treatmentID treatment to be deleted
	 * @return whether the treatment was successfully deleted from the Repository
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteTreatmentById(int treatmentID) throws Exception{
		return repo.deleteTreatmentById(treatmentID);
	}
	
	/**
	 * Update an existing treatment in the Repository
	 * @param treatmentID treatment to be updated
	 * @param treatment treatment object containing new information
	 * @return whether the treatment was successfully updated the Repository
	 * @throws Exception when there is an SQL Exception
	 */
	public int updateTreatmentById(int treatmentID, Treatment treatment) throws Exception{
		return repo.updateTreatmentById(treatmentID, treatment);
	}
	
	
	
	 /**
	  * Create a list of Treatment objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Treatment> where each object was created from the data in each String from the ArrayList input
	 */
	public List<Treatment> createListTreatment(ArrayList<String> foundResults){
		List<Treatment> listResults = new ArrayList<Treatment>(); 
		//review against Database setup
		int idxTreatmentID=0, idxTreatmentDate=1, idxTreatment=2, idxDescription=3, idxTreatmentStatus=4, idxUserID=5, idxAnimalID=6;
		for (String result: foundResults) {
			String[] resultSplit = result.split(repo.getSplitPlaceholder());
		Treatment temp =  new Treatment(Integer.valueOf(resultSplit[idxTreatmentID]), resultSplit[idxTreatmentDate], resultSplit[idxTreatment], 
				resultSplit[idxDescription], resultSplit[idxTreatmentStatus], Integer.valueOf(resultSplit[idxUserID]), Integer.valueOf(resultSplit[idxAnimalID]));
		listResults.add(temp);
	}
	
	System.out.println("\nPrepared List to send as json response to API endpoint:");
	System.out.println(listResults);

	return listResults;
	}
	
	
	
}