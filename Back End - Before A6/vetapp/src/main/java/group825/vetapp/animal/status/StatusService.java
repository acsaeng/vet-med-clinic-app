package group825.vetapp.animal.status;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service that performs Status requests
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Service
public class StatusService {

	/**
	 * Status repository that accesses the database
	 */
	private final StatusRepository dbStatus;
	
	/**
	 * Constructor that initializes the StatusService
	 * @param dbStatus repository denoted with Qualifier annotation storing the Status objects
	 */
	@Autowired
	public StatusService(@Qualifier("tempStatusRepo") StatusRepository dbStatus) {
		this.dbStatus = dbStatus;
	}

	/**
	 * Adds a status in the database
	 * @param status new Status object to be added
	 * @return integer verifying successful code execution
	 */
	public int addStatus(Status status) {
		return dbStatus.insertStatus(status);
	}

	/**
	 * Selects all statuses in the database
	 * @return List of all Status Objects
	 */
	public List<Status> selectAllStatus() {
		return dbStatus.selectAllStatus();
	}
	
	/**
	 * Selects a status in the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return Optional<Status> either containing the Status object for particular animal or is empty
	 */
	public Optional<Status> selectStatusById(UUID id) {
		return dbStatus.selectStatusById(id);
	}
	
	/**
	 * Deletes a status from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteStatusById(UUID id) {
		return dbStatus.deleteStatusById(id);
	}
	
	/**
	 * Updates a status from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @param status Status object with updated data members
	 * @return integer verifying successful code execution
	 */
	public int updateStatusById(UUID id, Status status) {
		return dbStatus.updateStatusById(id, status);
	}
}