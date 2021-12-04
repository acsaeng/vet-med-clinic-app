package group825.vetapp.animal.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

/**
 * Repository that stores Status information
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Repository("tempStatusRepo")
public class StatusRepository {

	/**
	 * Database that stores all the statuses
	 */
	private static List<Status> DB_status = new ArrayList<>();
	
	/**
	 * Inserts a status in the database
	 * @param status new Status object for particular animal
	 * @return integer verifying successful code execution
	 */
	public int insertStatus(Status status) {
		UUID id = UUID.randomUUID(); 
		DB_status.add(new Status(id, status.getStatus()));
		return 1;
	}
	
	/**
	 * Selects all statuses in the database
	 * @return database of statuses for all animals
	 */
	public List<Status> selectAllStatus() {
		return DB_status;
	}
	
	/**
	 * Selects a status in the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return Optional<Status> object which contains the status of particular animal or is empty
	 */
	public Optional<Status> selectStatusById(UUID id) {
		return DB_status.stream().filter(status -> status.getId().equals(id)).findFirst();
	}
	
	/**
	 * Deletes a status from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteStatusById(UUID id) {
		Optional<Status> animalMaybe = selectStatusById(id);

		if (animalMaybe.isEmpty()) {
			return 0;
		}

		DB_status.remove(animalMaybe.get());
		return 1;
	}
	
	/**
	 * Updates a status from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @param update Status object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updateStatusById(UUID id, Status update) {
		return selectStatusById(id).map(animalFound -> {
			int indexOfAnimalToUpdate = DB_status.indexOf(animalFound);

			// Index was found
			if (indexOfAnimalToUpdate >= 0) {
				DB_status.set(indexOfAnimalToUpdate, new Status(id, update.getStatus()));
				return 1;
			}

			return 0;
		}).orElse(0); // If no animal found by the id then return 0
	}
}