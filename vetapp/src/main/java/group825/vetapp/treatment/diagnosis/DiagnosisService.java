package group825.vetapp.treatment.diagnosis;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DiagnosisService {

	private final DiagnosisRepository repo;
	
	// UPDATE WITH DATABASE AS NEEDED
	/**
	 * Constructor for the DiagnosisService to communicate between the Repository and Controller
	 * @param repo - the DiagnosisRepository
	 */
	public DiagnosisService(@Qualifier("tempDiagnosisRepo") DiagnosisRepository repo) {
		this.repo = repo; 
	}

	/**
	 * Service for adding a diagnosis to the system
	 * @param diagnosis - diagnosis to be added
	 * @return whether the diagnosis was successfully added to the Repository
	 */
	public int addDiagnosis(Diagnosis diagnosis) {
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
	 * Search for a specific diagnosis in the Repository
	 * @param id - id of the requested diagnosis
	 * @return the diagnosis from the repository
	 */
	public Optional<Diagnosis> selectDiagnosisById(UUID id){
		return repo.selectDiagnosisById(id);
	}
	
	/**
	 * Delete a diagnosis from the Repository
	 * @param id - diagnosis to be deleted
	 * @return whether the diagnosis was successfully deleted from the Repository
	 */
	public int deleteDiagnosisById(UUID id) {
		return repo.deleteDiagnosisById(id);
	}
	
	/**
	 * Update an existing diagnosis in the Repository
	 * @param id - diagnosis to be updated
	 * @param diagnosis - diagnosis object containing new information
	 * @return whether the diagnosis was successfully updated the Repository
	 */
	public int updateDiagnosisById(UUID id, Diagnosis diagnosis) {
		return repo.updateDiagnosisById(id, diagnosis);
	}
	
	
	
}
