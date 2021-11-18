package group825.vetapp.treatment.diagnosis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


// UPDATE DATABASE AS NEEDED
@Repository("tempDiagnosisRepo")
public class DiagnosisRepository { 
	
	private static final List<Diagnosis> DB_diagnosis = new ArrayList<>();
	
	/**
	 * Adding a diagnosis to an animal
	 * @param diagnosis- the diagnosis associated with an animal
	 * @return 1 if successfully added
	 */
	public int addDiagnosis(Diagnosis diagnosis) {
		UUID id = UUID.randomUUID(); 
		DB_diagnosis.add(new Diagnosis(id, diagnosis.getAnimalName(),
		 		   						   diagnosis.getDiagnosis(),
		 		   						   diagnosis.getDescription())); 
		return 1;
	}
	
	/**
	 * Returns all diagnoses in the system associated with this animal
	 * @return all associated diagnoses
	 */
	public List<Diagnosis> selectAllDiagnosis(){
		return DB_diagnosis;
	}
	
	/**
	 * View a specific diagnosis selected by id
	 * @param id - the id of the selected diagnosis
	 * @return the diagnosis with the requested id
	 */
	public Optional<Diagnosis> selectDiagnosisById(UUID id) {
		return DB_diagnosis.stream()
						   .filter(diagnosis -> diagnosis.getId().equals(id))
						   .findFirst();
	}
	
	/**
	 * Remove a specific diagnosis from the system
	 * @param id - the id of the diagnosis to be removed
	 * @return 1 if removed successfully, otherwise 0
	 */
	public int deleteDiagnosisById(UUID id) {
		Optional<Diagnosis> diagnosisMaybe = selectDiagnosisById(id);
		if(diagnosisMaybe.isEmpty()) { 
			return 0;
		}
		DB_diagnosis.remove(diagnosisMaybe.get());
		return 1;
	}
	
	/**
	 * Update an existing diagnosis
	 * @param id - the id of the diagnosis to be updated
	 * @param newDiagnosis - the Diagnosis object with the updated information
	 * @return 1 if successfully updated, otherwise 0
	 */
	public int updateDiagnosisById(UUID id, Diagnosis newDiagnosis) {
		return selectDiagnosisById(id)
				.map(diagnosisFound -> {
					int indexOfAnimalToUpdate = DB_diagnosis.indexOf(diagnosisFound);
					if (indexOfAnimalToUpdate >= 0) { 
						DB_diagnosis.set(indexOfAnimalToUpdate, 
										 new Diagnosis(id, newDiagnosis.getAnimalName(),
												 		   newDiagnosis.getDiagnosis(),
												 		   newDiagnosis.getDescription())); 
						return 1;   
					}
					return 0; 
				}).orElse(0); 
	}
	
	
	
}
