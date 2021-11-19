package group825.vetapp.animal.treatmentRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

	private final TreatmentRepository repo;
	
	// UPDATE WITH DATABASE AS NEEDED
	/**
	 * Constructor for the TreatmentService to communicate between the Repository and Controller
	 * @param repo - the TreatmentRepository
	 */
	public TreatmentService(@Qualifier("tempTreatmentRepo") TreatmentRepository repo) {
		this.repo = repo; 
	}

	/**
	 * Service for adding a treatment request to the system
	 * @param treatment - treatment request to be added
	 * @return whether the treatment request was successfully added to the Repository
	 */
	public int addTreatment(Treatment treatment) {
		return repo.addTreatment(treatment);
	}
	
	/**
	 * List all treatment requests in the system
	 * @return a list of all treatment requests associated with an animal
	 */
	public List<Treatment> selectAllTreatment(){
		return repo.selectAllTreatment();
	}
	
	/**
	 * Search for a specific treatment request in the Repository
	 * @param id - id of the requested treatment
	 * @return the treatment request from the repository
	 */
	public Optional<Treatment> selectTreatmentById(UUID id){
		return repo.selectTreatmentById(id);
	}
	
	/**
	 * Delete a treatment request from the Repository
	 * @param id - treatment request to be deleted
	 * @return whether the treatment request was successfully deleted from the Repository
	 */
	public int deleteTreatmentById(UUID id) {
		return repo.deleteTreatmentById(id);
	}
	
	/**
	 * Update an existing treatment request in the Repository
	 * @param id - treatment request to be updated
	 * @param treatment - Treatment object containing new information
	 * @return whether the treatment request was successfully updated the Repository
	 */
	public int updateTreatmentById(UUID id, Treatment treatment) {
		return repo.updateTreatmentById(id, treatment);
	}
	
	
	
}
