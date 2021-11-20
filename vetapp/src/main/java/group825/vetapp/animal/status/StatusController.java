package group825.vetapp.animal.status;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles Shoto requests
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@RequestMapping("app/status/animal")
@RestController
public class StatusController {

	/**
	 * Status service that performs the request
	 */
	private final StatusService statusService;

	/**
	 * Constructor that initializes the StatusController
	 * @param statusService StatusService object which interacts with the "StatusRepository" Class
	 */
	@Autowired
	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}

	/**
	 * 'POST' mapping that adds a status to the database
	 * @param status RequestBody JSON object to be passed to the Status class where the JSON keys are already mapped to specific data members
	 */
	@PostMapping
	public void addStatus(@RequestBody Status status) {
		if (status.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		statusService.addStatus(status);
	}

	/**
	 * 'GET' mapping that retrieves all statuses from the database
	 * @return List<Status> object containing the statuses of all animals by calling method from the repository
	 */
	@GetMapping
	public List<Status> selectAllStatus() {
		return statusService.selectAllStatus();
	}

	/**
	 * 'GET' mapping that selects a status from the database by ID number
	 * @param idStr UUID path variable obtained by path denoted inside the GetMapping annotation
	 * @return Status object or throw exception
	 */
	@GetMapping(path="{id}") 
	public Status selectStatusById(@PathVariable("id") String idStr) {
		try {
			UUID id = UUID.fromString(idStr);

			// Throw exception if UUID is valid but does not exist in database
			return statusService.selectStatusById(id).orElseThrow(ApiExceptions.invalidIdException());
		} catch (java.lang.IllegalArgumentException e) {
			// Catch if idStr is not a valid UUID
			throw new InvalidIdException();
		}
	}

	/**
	 * 'DELETE' mapping that deletes a status from the database by ID number
	 * @param idStr UUID path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{id}")
	public void deleteStatusById(@PathVariable("id") String idStr) {
		try {
			UUID id = UUID.fromString(idStr);
			statusService.deleteStatusById(id);
		} catch (java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}

	/**
	 * 'PUT' mapping that updates the status information
	 * @param idStr UUID path variable obtained by path denoted inside the PutMapping annotation
	 * @param statusToUpdate response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{id}")
	public void updateStatusById(@PathVariable("id") String idStr, @RequestBody Status statusToUpdate) {
		try {
			UUID id = UUID.fromString(idStr);

			if (statusToUpdate.anyNulls()) {
				throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
			}

			statusService.updateStatusById(id, statusToUpdate);
		} catch (java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}
}