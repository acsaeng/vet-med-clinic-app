package group825.vetapp2.request;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.Application_DbConnection;

/**
 * Repository that stores Request animal information
 *
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since Dec 2, 2021
 */
// UPDATE DATABASE AS NEEDED
@Repository("tempRequestRepo")
public class RequestRepository {
	String table_name = "REQUEST";
	Application_DbConnection dao;
	String query;
	int Latest_request_id;
	
	public RequestRepository() throws Exception {
		dao = new Application_DbConnection();
		getLatestRequestId();
	}
	
	

	/**
	 * Database that stores all the animal requests
	 */
	private static final List<Request> dbRequest = new ArrayList<>();
	
	/**
	 * Adding a request to an animal
	 * @param request the request associated with an animal
	 * @return 1 if successfully added, 0 otherwise
	 */
	public int addRequest(Request request) throws Exception{
		String query_begin = "INSERT INTO REQUEST (Animal_ID, Request_ID, Requester_ID, Request_Date, Checkout_Date, Return_Date, Reason, Request_Status) VALUES";
		query = query_begin + "( '"+request.getAnimalID() +"', '" + request.getRequestID()+"', '" +  request.getRequesterID()
			+"', '" + request.getRequestDate() +"', '" +request.getCheckoutDate()+"', '" + request.getReturnDate() +"', '" +request.getReason()
			+"', '" + request.getRequestStatus() 
			+"');";
		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			getLatestRequestId();
			query = query_begin + "( '"+request.getAnimalID() +"', '" + (this.Latest_request_id+1) +"', '" +  request.getRequesterID()
				+"', '" + request.getRequestDate() +"', '" +request.getCheckoutDate()+"', '" + request.getReturnDate() +"', '" +request.getReason()
				+"', '" + request.getRequestStatus() +"');";
			int responseCheck = dao.manipulateRows(query);	
		}
		return 1;
	}
	
	/**
	 * Returns all requests in the system associated with this animal
	 * @return all associated requests
	 */
	public ArrayList<String> selectAllRequest() throws Exception{
		query = "SELECT R2.Animal_ID, R2.Request_ID, R2.Requester_ID, R2.Request_Date, R2.Checkout_Date, R2.Return_Date, "
				+ "R2.Reason, R2.Request_Status, U.First_Name, U.Last_Name,"
				+ "A.Animal_Name, A.Species  FROM REQUEST AS R2, Users as U, ANIMAL AS A "
				+ " WHERE R2.Requester_ID = U.User_ID AND R2.Animal_ID=A.Animal_ID "
				+" ORDER BY R2.Request_ID ASC"+" ;";
				
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
		
		
//		return dbRequest;
	}
	
	/**
	 * View a specific request selected by ID
	 * @param id the id of the selected request
	 * @return the request with the requested id
	 */
	public ArrayList<String> selectRequestsById(int id) throws Exception {
//		query = "SELECT * FROM REQUEST AS R2 WHERE R2.Requester_ID ='"+ id +"'";
		query = "SELECT R2.Animal_ID, R2.Request_ID, R2.Requester_ID, R2.Request_Date, R2.Checkout_Date, R2.Return_Date, "
		+ "R2.Reason, R2.Request_Status, U.First_Name, U.Last_Name, "
		+ "A.Animal_Name, A.Species  FROM REQUEST AS R2, Users as U, ANIMAL AS A  WHERE R2.Requester_ID = U.User_ID AND R2.Animal_ID=A.Animal_ID AND R2.Requester_ID = '"
		+ id + "' ORDER BY R2.Request_ID ASC;";
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
		
//		return Optional.empty();
//		return dbRequest.stream().filter(request -> request.getId().equals(id)).findFirst();
	}
	
	/**
	 * Remove a specific request from the system
	 * @param id the id of the request to be removed
	 * @return 1 if removed successfully, 0 otherwise
	 */
	public int deleteRequestById(UUID id) {
//		Optional<Request> requestMaybe = selectRequestById(id);
//
//		if (requestMaybe.isEmpty()) {
//			return 0;
//		}
//
//		dbRequest.remove(requestMaybe.get());
		return 1;
	}
	
	/**
	 * Update an existing request
	 * @param id the id of the request to be updated
	 * @param newRequest the Request object with the updated information
	 * @return 1 if successfully updated, 0 otherwise
	 */
	public int updateRequestById(int id, Request update) throws Exception{
		 String query = "UPDATE " + table_name + " AS R SET Animal_ID='" + update.getAnimalID() + "', Request_ID='" + update.getRequestID() 
		 + "', Requester_ID='" + update.getRequesterID() + "', Request_Date='" + update.getRequestDate() + "', Checkout_Date='" + update.getCheckoutDate() 
		 + "', Return_Date='" + update.getReturnDate() + "', Reason='" + update.getReason() +"', Request_Status='" + update.getRequestStatus() 
		 +  "' WHERE R.Request_ID='" + update.getRequestID()+"';";
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	
	
	/**
	 * get the latest Id for the primary key for Request object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestRequestId() throws Exception{
		String queryMaxId = "SELECT MAX(R.Request_ID) FROM REQUEST AS R ";
//		System.out.println(queryMaxId);
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.Latest_request_id = Integer.valueOf(latestId);
	}
	
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}
	
	
	
}