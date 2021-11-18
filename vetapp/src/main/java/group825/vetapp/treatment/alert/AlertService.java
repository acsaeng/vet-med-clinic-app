package group825.vetapp.treatment.alert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

	private final AlertRepository repo;
	
	// UPDATE WITH DATABASE AS NEEDED
	/**
	 * Constructor for the AlertService to communicate between the Repository and Controller
	 * @param repo - the AlertRepository
	 */
	public AlertService(@Qualifier("tempAlertRepo") AlertRepository repo) {
		this.repo = repo; 
	}

	/**
	 * Service for adding a alert to the system
	 * @param alert - alert to be added
	 * @return whether the alert was successfully added to the Repository
	 */
	public int addAlert(Alert alert) {
		return repo.addAlert(alert);
	}
	
	// CURRENTLY UNNECESSARY CODE, COMMENTED OUT IN CASE IT IS NEEDED
//	/**
//	 * List all alerts in the system
//	 * @return a list of all alerts associated with an animal
//	 */
//	public List<Alert> selectAllAlert(){
//		return repo.selectAllAlert();
//	}
//	
//	/**
//	 * Search for a specific alert in the Repository
//	 * @param id - id of the requested alert
//	 * @return the alert from the repository
//	 */
//	public Optional<Alert> selectAlertById(UUID id){
//		return repo.selectAlertById(id);
//	}
//	
//	/**
//	 * Delete a alert from the Repository
//	 * @param id - alert to be deleted
//	 * @return whether the alert was successfully deleted from the Repository
//	 */
//	public int deleteAlertById(UUID id) {
//		return repo.deleteAlertById(id);
//	}
//	
//	/**
//	 * Update an existing alert in the Repository
//	 * @param id - alert to be updated
//	 * @param alert - alert object containing new information
//	 * @return whether the alert was successfully updated the Repository
//	 */
//	public int updateAlertById(UUID id, Alert alert) {
//		return repo.updateAlertById(id, alert);
//	}
	
	
	
}
