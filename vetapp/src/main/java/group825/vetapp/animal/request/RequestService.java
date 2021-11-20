package group825.vetapp.animal.request;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service that performs Request animal requests
 *
 * @author Timothy Mok
 * @version 1.0
 * @since November 15, 2021
 */
@Service
public class RequestService {

	/**
	 * Request animal repository that accesses the database
	 */
	private final RequestRepository repo;

	/**
	 * Constructor for the RequestService to communicate between the Repository and Controller
	 * @param repo the RequestRepository
	 */
	public RequestService(@Qualifier("tempRequestRepo") RequestRepository repo) {
		// UPDATE WITH DATABASE AS NEEDED
		this.repo = repo;
	}

	/**
	 * Service for adding a request to the system
	 * @param request request to be added
	 * @return whether the request was successfully added to the Repository
	 */
	public int addRequest(Request request) {
		return repo.addRequest(request);
	}
	
	/**
	 * List all animal requests in the system
	 * @return a list of all requests associated with an animal
	 */
	public List<Request> selectAllRequest(){
		return repo.selectAllRequest();
	}
	
	/**
	 * Search for a specific request in the Repository
	 * @param id ID number of the requested request
	 * @return the request from the repository
	 */
	public Optional<Request> selectRequestById(UUID id){
		return repo.selectRequestById(id);
	}
	
	/**
	 * Delete a request from the Repository
	 * @param id request to be deleted
	 * @return whether the request was successfully deleted from the Repository
	 */
	public int deleteRequestById(UUID id) {
		return repo.deleteRequestById(id);
	}
	
	/**
	 * Update an existing request in the Repository
	 * @param id request to be updated
	 * @param request request object containing new information
	 * @return whether the request was successfully updated the Repository
	 */
	public int updateRequestById(UUID id, Request request) {
		return repo.updateRequestById(id, request);
	}
}