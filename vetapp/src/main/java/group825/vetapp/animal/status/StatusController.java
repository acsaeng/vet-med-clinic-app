package group825.vetapp.animal.status;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("app/status/animal")
@RestController
public class StatusController {
	private final StatusService statusService;

	/** StatusController Constructor 
	 * @param statusService = StatusService object which interacts with the "StatusRepository" Class
	 */
	@Autowired
	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}
	
	
	
	/** addStatus function - POST MAPPING
	 * @param status = RequestBody json object to be passed to the Status class where the json keys are already mapped to specific data members
	 */
	@PostMapping
	public void addStatus(@RequestBody Status status){	
		if (status.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		statusService.addStatus(status);
	}


	
	/** selectAllStatus function - GET MAPPING
	 * @return List<Status> object containing the statuses of all animals by calling method from the repository
	 */
	@GetMapping
	public List<Status> selectAllStatus(){
		return statusService.selectAllStatus();
	}
	
	
	
	/** selectStatusById function - GET MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the GetMapping annotation
	 * @return Status object or throw exception
	 */
	@GetMapping(path="{id}") 
	public Status selectStatusById(@PathVariable("id") String id_str) {	
		try {
			UUID id = UUID.fromString(id_str);
			return statusService.selectStatusById(id)
					.orElseThrow(ApiExceptions.invalidIdException()); //throw exception if UUID is valid but does not exist in database
		}catch(java.lang.IllegalArgumentException e) { //catch if id_str is not a valid UUID
			throw new InvalidIdException();
		}
		
	}
	

	
	/** deleteStatusById function - DELETE MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{id}")
	public void deleteStatusById(@PathVariable("id") String id_str) {
		try {
			UUID id = UUID.fromString(id_str);
			statusService.deleteStatusById(id);
		}catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}
	
	
	
	/** updateStatusById function - PUT MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the PutMapping annotation
	 * @param statusToUpdate = response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{id}")
	public void updateStatusById(@PathVariable("id") String id_str, @RequestBody Status statusToUpdate) {	
		try {
			UUID id = UUID.fromString(id_str);
			if (statusToUpdate.anyNulls()) {
				throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
			}
			statusService.updateStatusById(id, statusToUpdate);
		}catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}
}
