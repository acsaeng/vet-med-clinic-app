package group825.vetapp.animal.treatmentRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


// UPDATE DATABASE AS NEEDED
@Repository("tempTreatmentRepo")
public class TreatmentRepository { 
	
	private static final List<Treatment> DB_treatment = new ArrayList<>();
	
	/**
	 * Adding a treatment request to an animal
	 * @param treatment- the treatment request associated with an animal
	 * @return 1 if successfully added
	 */
	public int addTreatment(Treatment treatment) {
		UUID id = UUID.randomUUID(); 
		DB_treatment.add(new Treatment(id, treatment.getRequestFor(),
		 		   						   treatment.getRequestBy(),
		 		   						   treatment.getMessage())); 
		return 1;
	}
	
	/**
	 * Returns all treatment requests in the system associated with this animal
	 * @return all associated treatment requests
	 */
	public List<Treatment> selectAllTreatment(){
		return DB_treatment;
	}
	
	/**
	 * View a specific treatment request selected by id
	 * @param id - the id of the selected treatment request
	 * @return the treatment with the requested id
	 */
	public Optional<Treatment> selectTreatmentById(UUID id) {
		return DB_treatment.stream()
						   .filter(treatment -> treatment.getId().equals(id))
						   .findFirst();
	}
	
	/**
	 * Remove a specific treatment request from the system
	 * @param id - the id of the treatment request to be removed
	 * @return 1 if removed successfully, otherwise 0
	 */
	public int deleteTreatmentById(UUID id) {
		Optional<Treatment> treatmentMaybe = selectTreatmentById(id);
		if(treatmentMaybe.isEmpty()) { 
			return 0;
		}
		DB_treatment.remove(treatmentMaybe.get());
		return 1;
	}
	
	/**
	 * Update an existing treatment request
	 * @param id - the id of the treatment request to be updated
	 * @param newTreatment - the Treatment object with the updated information
	 * @return 1 if successfully updated, otherwise 0
	 */
	public int updateTreatmentById(UUID id, Treatment newTreatment) {
		return selectTreatmentById(id)
				.map(treatmentFound -> {
					int indexOfAnimalToUpdate = DB_treatment.indexOf(treatmentFound);
					if (indexOfAnimalToUpdate >= 0) { 
						DB_treatment.set(indexOfAnimalToUpdate, 
										 new Treatment(id, newTreatment.getRequestFor(),
												 		   newTreatment.getRequestBy(),
												 		   newTreatment.getMessage())); 
						return 1;   
					}
					return 0; 
				}).orElse(0); 
	}
	
	
	
}
