package group825.vetapp.treatment.alert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


// UPDATE DATABASE AS NEEDED
@Repository("tempAlertRepo")
public class AlertRepository { 
	
	private static final List<Alert> DB_alert = new ArrayList<>();
	
	/**
	 * Adding a alert to an animal
	 * @param alert- the alert associated with an animal
	 * @return 1 if successfully added
	 */
	public int addAlert(Alert alert) {
		UUID id = UUID.randomUUID(); 
		DB_alert.add(new Alert(id, alert.getAnimalName(),
		 		   				   alert.getMessage())); 
		return 1;
	}
	
	// CURRENTLY UNNECESSARY CODE, COMMENTED OUT IN CASE IT IS NEEDED
//	
//	/**
//	 * Returns all alerts in the system associated with this animal
//	 * @return all associated alerts
//	 */
//	public List<Alert> selectAllAlert(){
//		return DB_alert;
//	}
//	
//	/**
//	 * View a specific alert selected by id
//	 * @param id - the id of the selected alert
//	 * @return the alert with the requested id
//	 */
//	public Optional<Alert> selectAlertById(UUID id) {
//		return DB_alert.stream()
//						   .filter(alert -> alert.getId().equals(id))
//						   .findFirst();
//	}
//	
//	/**
//	 * Remove a specific alert from the system
//	 * @param id - the id of the alert to be removed
//	 * @return 1 if removed successfully, otherwise 0
//	 */
//	public int deleteAlertById(UUID id) {
//		Optional<Alert> alertMaybe = selectAlertById(id);
//		if(alertMaybe.isEmpty()) { 
//			return 0;
//		}
//		DB_alert.remove(alertMaybe.get());
//		return 1;
//	}
//	
//	/**
//	 * Update an existing alert
//	 * @param id - the id of the alert to be updated
//	 * @param newAlert - the Alert object with the updated information
//	 * @return 1 if successfully updated, otherwise 0
//	 */
//	public int updateAlertById(UUID id, Alert newAlert) {
//		return selectAlertById(id)
//				.map(alertFound -> {
//					int indexOfAnimalToUpdate = DB_alert.indexOf(alertFound);
//					if (indexOfAnimalToUpdate >= 0) { 
//						DB_alert.set(indexOfAnimalToUpdate, 
//										 new Alert(id, newAlert.getAnimalName(),
//												 	   newAlert.getMessage())); 
//						return 1;   
//					}
//					return 0; 
//				}).orElse(0); 
//	}
	
	
	
}
