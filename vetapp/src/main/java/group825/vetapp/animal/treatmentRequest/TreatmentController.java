package group825.vetapp.animal.treatmentRequest;

import java.util.List;
import java.util.UUID;


import group825.vetapp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app/treatment-request/animal")
@RestController
public class TreatmentController {
	
	private final TreatmentService treatmentService;

	/**
	 * Constructor for TreatmentController
	 * @param treatmentService - service to be used to send updates to Repository
	 */
	@Autowired
	public TreatmentController(TreatmentService treatmentService) {
		this.treatmentService = treatmentService;
	}
	
	/**
	 * Send request to add a treatment requests for an animal.
	 * Checks that all necessary fields are filled out.
	 * @param treatment - the treatment requests
	 */
	@PostMapping
	public void addTreatment(@RequestBody Treatment treatment) {
		if(treatment.anyNulls()) {
			throw new ApiRequestException("At least one treatment field is null");
		}
		treatmentService.addTreatment(treatment);
	}

	/**
	 * Displays all the treatment requests associated with an animal
	 * @return a list of all the treatment requests
	 */
	@GetMapping
	public List<Treatment> selectAllTreatment(){
		return treatmentService.selectAllTreatment();
	}
	
	/**
	 * Requests a specific treatment.
	 * Checks that the ID is valid. 
	 * @param strId - the ID of the requested treatment
	 * @return the requested treatment
	 */
	@GetMapping(path = "{id}") 
	public Treatment selectTreatmentById(@PathVariable("id") String strId) {
		try {
            UUID id = UUID.fromString(strId);
            return treatmentService.selectTreatmentById(id)
					.orElseThrow(ApiExceptions.invalidIdException());
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Delete an existing treatment request in the database.
	 * Checks that the treatment request ID is valid.
	 * @param strId - the ID of the treatment request to be deleted
	 */
	@DeleteMapping(path = "{id}")
	public void deleteTreatmentById(@PathVariable("id") String strId) {
		try {
            UUID id = UUID.fromString(strId);
            treatmentService.deleteTreatmentById(id);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Update an existing treatment in the database.
	 * Checks that the treatment ID is valid.
	 * @param strId - the ID of the treatment request to be updated
	 * @param treatmentToUpdate - treatment request object with updated information
	 */
	@PutMapping(path = "{id}")
	public void updateTreatmentById(@PathVariable("id") String strId, @RequestBody Treatment treatmentToUpdate) {	
		try {
            UUID id = UUID.fromString(strId);
            if(treatmentToUpdate.anyNulls()) {
    			throw new ApiRequestException("At least one treatment field is null");
    		}
            treatmentService.updateTreatmentById(id, treatmentToUpdate);
        } catch(java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
}
