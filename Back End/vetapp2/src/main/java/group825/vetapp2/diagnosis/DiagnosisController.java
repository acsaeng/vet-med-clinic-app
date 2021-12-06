package group825.vetapp2.diagnosis;

import java.util.List;
import group825.vetapp2.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Service that performs Diagnosis requests
 *
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since December 6, 2021
 */
@CrossOrigin
@RequestMapping("app/diagnosis/diagnosis")
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
	public void addDiagnosis(@RequestBody Diagnosis diagnosis) throws Exception {
		if (diagnosis.anyNulls()) {
			throw new ApiRequestException("At least one diagnosis field is null");
		}
		diagnosisService.addDiagnosis(diagnosis);
	}
	
	/**
	 * Requests a specific diagnosis and checks that the ID is valid
	 * @param diagnosisID the ID of the requested diagnosis
	 * @return the requested diagnosis
	 */
	@GetMapping(path = "/animalID={animalID}") 
	public List<Diagnosis> selectDiagnosisByAnimalId(@PathVariable("animalID") String animalID) throws Exception {
		try {
			//id of animal
			int id = Integer.valueOf(animalID);
			System.out.println(diagnosisService.selectDiagnosisByAnimalID(id));
			return diagnosisService.selectDiagnosisByAnimalID(id);
        } catch (java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Gets a specific diagnosis associated with an animal
	 * @param animalID the ID of the requested animal
	 * @param diagnosisID the ID of the requested diagnosis
	 * @return the requested diagnosis
	 * @throws Exception when there is an SQL Exception
	 */
	@GetMapping(path = "/diagnosisID={diagnosisID}") 
	public Diagnosis selectDiagnosisByDiagnosisID(@PathVariable("diagnosisID") String diagnosisID) throws Exception {
		try {
			int tID = Integer.valueOf(diagnosisID);
			System.out.println(diagnosisService.selectDiagnosisByDiagnosisID(tID));
			return diagnosisService.selectDiagnosisByDiagnosisID(tID);
        } catch (java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Delete an existing diagnosis in the database and checks that the diagnosis ID is valid
	 * @param diagnosisID the ID of the diagnosis to be deleted
	 */
	@DeleteMapping(path = "/diagnosisID={diagnosisID}")
	public void deleteDiagnosisByID(@PathVariable("diagnosisID") String diagnosisID) throws Exception{
		try {
			int id = Integer.valueOf(diagnosisID);
            diagnosisService.deleteDiagnosisById(id);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Update an existing diagnosis in the database and checks that the diagnosis ID is valid
	 * @param diagnosisId the ID of the diagnosis to be updated
	 * @param diagnosisToUpdate diagnosis object with updated information
	 */
	@PutMapping(path = "/diagnosisID={diagnosisID}")
	public void updateDiagnosisById(@PathVariable("diagnosisID") String diagnosisID, @RequestBody Diagnosis diagnosisToUpdate) throws Exception{	
		try {
			int id = Integer.valueOf(diagnosisID);
            if (diagnosisToUpdate.anyNulls()) {
    			throw new ApiRequestException("At least one diagnosis field is null");
    		}
            diagnosisService.updateDiagnosisById(id, diagnosisToUpdate);
        } catch (java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
}