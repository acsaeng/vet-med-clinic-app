package group825.vetapp.animal.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;



@Repository("tempStatusRepo")
public class StatusRepository { //Dummy repo, Need to update to pull status from DataBase
	
	private static List<Status> DB_status = new ArrayList<>();
	
	public int insertStatus(Status status) {
		UUID id = UUID.randomUUID(); 
		DB_status.add(new Status(id, status.getStatus()));
		return 1;
	}
	
	public List<Status> selectAllStatus(){
		return DB_status;
	}
	
	public Optional<Status> selectStatusById(UUID id) {
		
		return DB_status.stream()
				.filter(status -> status.getId().equals(id))
				.findFirst();
	}
	
	public int deleteStatusById(UUID id) {
		Optional<Status> animalMaybe = selectStatusById(id);
		if (animalMaybe.isEmpty()) { return 0; }
		DB_status.remove(animalMaybe.get());
		return 1;
	}
	
	public int updateStatusById(UUID id, Status update) {//Person update only contains the RequestBody from the HTTP request, so the Postman request has a json input with just a name
		return selectStatusById(id)
				.map(animalFound -> {
					int indexOfPersonToUpdate = DB_status.indexOf(animalFound);
					if (indexOfPersonToUpdate >= 0) { //index was found, ie animal object exists
						DB_status.set(indexOfPersonToUpdate, new Status(id, update.getStatus())); //update that status object at index found to the new status object
						return 1;   
					}
					return 0; 
				})
				.orElse(0); //if no animal found by the id then return 0
	}
	
	
	
}
