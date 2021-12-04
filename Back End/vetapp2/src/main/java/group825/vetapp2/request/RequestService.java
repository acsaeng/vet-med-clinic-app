package group825.vetapp2.request;

import java.util.ArrayList;
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
	public int addRequest(Request request) throws Exception {
		return repo.addRequest(request);
	}
	
	/**
	 * List all animal requests in the system
	 * @return a list of all requests associated with an animal
	 */
	public List<Request> selectAllRequest() throws Exception{
		ArrayList<String> results = repo.selectAllRequest();
		List<Request> listResults = createListRequest(results);
    	return listResults;
//		return repo.selectAllRequest();
	}
	
	/**
	 * Search for a specific request in the Repository
	 * @param id ID number of the requested request
	 * @return the request from the repository
	 */
	public List<Request> selectRequestsById(int id) throws Exception{
		ArrayList<String> results =  repo.selectRequestsById(id);
		List<Request> listResults = createListRequest(results);
		return listResults;
//		return repo.selectRequestById(id);
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
	public int updateRequestById(int id, Request request) throws Exception{
		return repo.updateRequestById(id, request);
	}
	
	 /**
	  * Create a list of Request objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Comment> where each object was created from the data in each String from the ArrayList input
	 */
	public List<Request> createListRequest(ArrayList<String> foundResults){
		List<Request> listResults = new ArrayList<Request>(); 
		//review against Database setup
		int idx_animalID=0, idx_requestID=1, idx_requesterID=2, idx_requestDate=3, idx_checkoutDate=4, idx_returnDate=5, 
				idx_reason=6, idx_requestStatus=7, idx_requesterFirstName=8, idx_requesterLastName=9, idx_animalName=10, idx_animalSpecies=11;
		for (String result: foundResults) {
			String[] resultSplit = result.split(repo.getSplitPlaceholder());
		Request temp =  new Request( Integer.valueOf(resultSplit[idx_animalID]), Integer.valueOf(resultSplit[idx_requestID]), Integer.valueOf(resultSplit[idx_requesterID]), 
				resultSplit[idx_requestDate], resultSplit[idx_checkoutDate], resultSplit[idx_returnDate], resultSplit[idx_reason], 
				resultSplit[idx_requestStatus], resultSplit[idx_requesterFirstName], resultSplit[idx_requesterLastName], 
				resultSplit[idx_animalName], resultSplit[idx_animalSpecies]);
		listResults.add(temp);
	}
	System.out.println("\nPrepared List to send as json response to API endpoint:");
	System.out.println(listResults);

	return listResults;
	}
	
	
}