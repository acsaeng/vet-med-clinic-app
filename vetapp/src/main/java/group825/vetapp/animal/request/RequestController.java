package group825.vetapp.animal.request;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller that handles Request animal requests
 *
 * @author Timothy Mok
 * @version 1.0
 * @since November 15, 2021
 */
@RequestMapping("app/request/animal")
@RestController
public class RequestController {

	/**
	 * Animal request service that performs the request
	 */
	private final RequestService requestService;

	/**
	 * Constructor for RequestController
	 * @param requestService service to be used to send updates to Repository
	 */
	@Autowired
	public RequestController(RequestService requestService) {
		this.requestService = requestService;
	}
	
	/**
	 * Send request to add a request for an animal and checks that all necessary fields are filled out
	 * @param request the request
	 */
	@PostMapping
	public void addRequest(@RequestBody Request request) {
		if (request.anyNulls()) {
			throw new ApiRequestException("At least one request field is null");
		}
		requestService.addRequest(request);
	}

	/**
	 * Displays all the requests associated with an animal
	 * @return a list of all the requests
	 */
	@GetMapping
	public List<Request> selectAllRequest(){
		return requestService.selectAllRequest();
	}
	
	/**
	 * Requests a specific request and checks that the ID is valid
	 * @param strId the ID number of the requested request
	 * @return the requested request
	 */
	@GetMapping(path = "{id}") 
	public Request selectRequestById(@PathVariable("id") String strId) {
		try {
            UUID id = UUID.fromString(strId);

            return requestService.selectRequestById(id).orElseThrow(ApiExceptions.invalidIdException());
        } catch (java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Delete an existing request in the database and checks that the request ID is valid
	 * @param strId the ID of the request to be deleted
	 */
	@DeleteMapping(path = "{id}")
	public void deleteRequestById(@PathVariable("id") String strId) {
		try {
            UUID id = UUID.fromString(strId);
            requestService.deleteRequestById(id);
        } catch (java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
	
	/**
	 * Update an existing request in the database and checks that the request ID is valid
	 * @param strId the ID of the request to be updated
	 * @param requestToUpdate request object with updated information
	 */
	@PutMapping(path = "{id}")
	public void updateRequestById(@PathVariable("id") String strId, @RequestBody Request requestToUpdate) {	
		try {
            UUID id = UUID.fromString(strId);

            if (requestToUpdate.anyNulls()) {
    			throw new ApiRequestException("At least one request field is null");
    		}

            requestService.updateRequestById(id, requestToUpdate);
        } catch (java.lang.IllegalArgumentException e) {
            throw new InvalidIdException();
        }
	}
}