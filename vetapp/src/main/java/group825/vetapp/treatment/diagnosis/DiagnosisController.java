package group825.vetapp.treatment.diagnosis;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app/treatment/diagnosis")
@RestController
public class DiagnosisController {
	
	private final DiagnosisService diagnosisService;

	/**
	 * Constructor for DiagnosisController
	 * @param diagnosisService - service to be used to send updates to Repository
	 */
	@Autowired
	public DiagnosisController(DiagnosisService diagnosisService) {
		this.diagnosisService = diagnosisService;
	}
	
	/**
	 * Send request to add a diagnosis for an animal and checks that all necessary fields are filled out
	 * @param diagnosis the diagnosis
	 */
	@PostMapping
	public void addDiagnosis(@RequestBody Diagnosis diagnosis) {
		if (diagnosis.anyNulls()) {
			throw new ApiRequestException("At least one diagnosis field is null");
		}
		diagnosisService.addDiagnosis(diagnosis);
	}

	/**
	 * Displays all the diagnosis associated with an animal
	 * @return a list of all the diagnosis
	 */
	@GetMapping
	public List<Diagnosis> selectAllDiagnosis() {
		return diagnosisService.selectAllDiagnosis();
	}
	
	/**
	 * Requests a specific diagnosis and checks that the ID is valid
	 * @param strId the ID of the requested diagnosis
	 * @return the requested diagnosis
	 */
	@GetMapping(path = "{id}") 
	public Diagnosis selectDiagnosisById(@PathVariable("id") String strId) {
		try {
            UUID id = UUID.fromString(strId);
            return diagnosisService.selectDiagnosisById(id).orElseThrow(ApiExceptions.invalidIdException());
        } catch (java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Delete an existing diagnosis in the database and checks that the diagnosis ID is valid
	 * @param strId the ID of the diagnosis to be deleted
	 */
	@DeleteMapping(path = "{id}")
	public void deleteDiagnosisById(@PathVariable("id") String strId) {
		try {
            UUID id = UUID.fromString(strId);
            diagnosisService.deleteDiagnosisById(id);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Update an existing diagnosis in the database and checks that the diagnosis ID is valid
	 * @param strId the ID of the diagnosis to be updated
	 * @param diagnosisToUpdate diagnosis object with updated information
	 */
	@PutMapping(path = "{id}")
	public void updateDiagnosisById(@PathVariable("id") String strId, @RequestBody Diagnosis diagnosisToUpdate) {	
		try {
            UUID id = UUID.fromString(strId);
            if (diagnosisToUpdate.anyNulls()) {
    			throw new ApiRequestException("At least one diagnosis field is null");
    		}
            diagnosisService.updateDiagnosisById(id, diagnosisToUpdate);
        } catch (java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
}