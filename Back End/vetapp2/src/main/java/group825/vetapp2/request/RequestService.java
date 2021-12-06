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
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since Dec 5, 2021
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
	 * @param request = request to be added
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
	 * @param userID = ID number of user whose requests are being returned
	 * @return the list of requests from the repository
	 */
	public List<Request> selectRequestsById(int userID) throws Exception{
		ArrayList<String> results =  repo.selectRequestsById(userID);
		List<Request> listResults = createListRequest(results);
		return listResults;
//		return repo.selectRequestById(id);
	}
	
	/**
	 * Delete a request from the Repository
	 * @param requestID = ID of request to be deleted
	 * @return whether the request was successfully deleted from the Repository
	 */
	public int deleteRequestById(int requestID) throws Exception{
		return repo.deleteRequestById(requestID);
	}
	
	/**
	 * Update an existing request in the Repository
	 * @param requestID = id of request to be updated
	 * @param request = request object containing new information
	 * @return whether the request was successfully updated the Repository
	 */
	public int updateRequestById(int requestID, Request request) throws Exception{
		return repo.updateRequestById(requestID, request);
	}
	
	 /**
	  * Create a list of Request objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Request> where each object was created from the data in each String from the ArrayList input
	 */
	public List<Request> createListRequest(ArrayList<String> foundResults){
		List<Request> listResults = new ArrayList<Request>(); 
		//review against Database setup
		int idxAnimalID=0, idxRequestID=1, idxRequesterID=2, idxRequestDate=3, idxCheckoutDate=4, idxReturnDate=5, 
				idxReason=6, idxRequestStatus=7, idxRequesterFirstName=8, idxRequesterLastName=9, idxAnimalName=10, idxAnimalSpecies=11;
		for (String result: foundResults) {
			String[] resultSplit = result.split(repo.getSplitPlaceholder());
		Request temp =  new Request( Integer.valueOf(resultSplit[idxAnimalID]), Integer.valueOf(resultSplit[idxRequestID]), Integer.valueOf(resultSplit[idxRequesterID]), 
				resultSplit[idxRequestDate], resultSplit[idxCheckoutDate], resultSplit[idxReturnDate], resultSplit[idxReason], 
				resultSplit[idxRequestStatus], resultSplit[idxRequesterFirstName], resultSplit[idxRequesterLastName], 
				resultSplit[idxAnimalName], resultSplit[idxAnimalSpecies]);
		listResults.add(temp);

	}
	System.out.println("\nPrepared List to send as json response to API endpoint:");
	System.out.println(listResults);

	return listResults;
	}
	
	
}