package group825.vetapp.treatment.alert;

import java.util.List;
import java.util.UUID;


import group825.vetapp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("app/treatment/alert")
@RestController
public class AlertController {
	
	private final AlertService alertService;

	/**
	 * Constructor for AlertController
	 * @param alertService - service to be used to send updates to Repository
	 */
	@Autowired
	public AlertController(AlertService alertService) {
		this.alertService = alertService;
	}
	
	/**
	 * Send request to add a alert for an animal.
	 * Checks that all necessary fields are filled out.
	 * @param alert - the alert
	 */
	@PostMapping
	public void addAlert(@RequestBody Alert alert) {
		if(alert.anyNulls()) {
			throw new ApiRequestException("At least one alert field is null");
		}
		alertService.addAlert(alert);
	}

	// CURRENTLY UNNECESSARY CODE, COMMENTED OUT IN CASE IT IS NEEDED
//	/**
//	 * Displays all the alerts associated with an animal
//	 * @return a list of all the alerts
//	 */
//	@GetMapping
//	public List<Alert> selectAllAlert(){
//		return alertService.selectAllAlert();
//	}
//	
//	/**
//	 * Requests a specific alert.
//	 * Checks that the ID is valid. 
//	 * @param strId - the ID of the requested alert
//	 * @return the requested alert
//	 */
//	@GetMapping(path = "{id}") 
//	public Alert selectAlertById(@PathVariable("id") String strId) {
//		try {
//            UUID id = UUID.fromString(strId);
//            return alertService.selectAlertById(id)
//					.orElseThrow(ApiExceptions.invalidIdException());
//        } catch(java.lang.IllegalArgumentException e) {
//            throw new InvalidIdException();
//        }
//	}
//	
//	/**
//	 * Delete an existing alert in the database.
//	 * Checks that the alert ID is valid.
//	 * @param strId - the ID of the alert to be deleted
//	 */
//	@DeleteMapping(path = "{id}")
//	public void deleteAlertById(@PathVariable("id") String strId) {
//		try {
//            UUID id = UUID.fromString(strId);
//            alertService.deleteAlertById(id);
//        } catch(java.lang.IllegalArgumentException e) {
//            throw new InvalidIdException();
//        }
//	}
//	
//	/**
//	 * Update an existing alert in the database.
//	 * Checks that the alert ID is valid.
//	 * @param strId - the ID of the alert to be updated
//	 * @param alertToUpdate - alert object with updated information
//	 */
//	@PutMapping(path = "{id}")
//	public void updateAlertById(@PathVariable("id") String strId, @RequestBody Alert alertToUpdate) {	
//		try {
//            UUID id = UUID.fromString(strId);
//            if(alertToUpdate.anyNulls()) {
//    			throw new ApiRequestException("At least one alert field is null");
//    		}
//            alertService.updateAlertById(id, alertToUpdate);
//        } catch(java.lang.IllegalArgumentException e) {
//            throw new InvalidIdException();
//        }
//	}
}
