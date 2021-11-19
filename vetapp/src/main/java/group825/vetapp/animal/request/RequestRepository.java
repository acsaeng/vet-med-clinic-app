package group825.vetapp.animal.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;


// UPDATE DATABASE AS NEEDED
@Repository("tempRequestRepo")
public class RequestRepository { 
	
	private static final List<Request> DB_request = new ArrayList<>();
	
	/**
	 * Adding a request to an animal
	 * @param request- the request associated with an animal
	 * @return 1 if successfully added
	 */
	public int addRequest(Request request) {
		UUID id = UUID.randomUUID(); 
		DB_request.add(new Request(id, request.getRequester(),
		 		   					   request.getRequestDate(),
		 		   					   request.getCheckoutDate(),
		 		   					   request.getReturnDate(),
		 		   					   request.getMessage())); 
		return 1;
	}
	
	/**
	 * Returns all requests in the system associated with this animal
	 * @return all associated requests
	 */
	public List<Request> selectAllRequest(){
		return DB_request;
	}
	
	/**
	 * View a specific request selected by id
	 * @param id - the id of the selected request
	 * @return the request with the requested id
	 */
	public Optional<Request> selectRequestById(UUID id) {
		return DB_request.stream()
						   .filter(request -> request.getId().equals(id))
						   .findFirst();
	}
	
	/**
	 * Remove a specific request from the system
	 * @param id - the id of the request to be removed
	 * @return 1 if removed successfully, otherwise 0
	 */
	public int deleteRequestById(UUID id) {
		Optional<Request> requestMaybe = selectRequestById(id);
		if(requestMaybe.isEmpty()) { 
			return 0;
		}
		DB_request.remove(requestMaybe.get());
		return 1;
	}
	
	/**
	 * Update an existing request
	 * @param id - the id of the request to be updated
	 * @param newRequest - the Request object with the updated information
	 * @return 1 if successfully updated, otherwise 0
	 */
	public int updateRequestById(UUID id, Request newRequest) {
		return selectRequestById(id)
				.map(requestFound -> {
					int indexOfAnimalToUpdate = DB_request.indexOf(requestFound);
					if (indexOfAnimalToUpdate >= 0) { 
						DB_request.set(indexOfAnimalToUpdate, 
										 new Request(id, newRequest.getRequester(),
					 		   					   		 newRequest.getRequestDate(),
					 		   					   		 newRequest.getCheckoutDate(),
					 		   					   		 newRequest.getReturnDate(),
					 		   					   		 newRequest.getMessage())); 
						return 1;   
					}
					return 0; 
				}).orElse(0); 
	}
	
	
	
}
