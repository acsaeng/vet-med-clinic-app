package group825.vetapp2.request;

import java.sql.*;
import java.util.ArrayList;
<<<<<<< HEAD
=======

>>>>>>> 6ce1f0c7a56ff15d792616be24d2bcf3fa5f2342
import java.util.List;


import org.springframework.stereotype.Repository;

import group825.vetapp2.database.OldDatabaseConnection;

/**
 * Repository that stores Request animal information
 *
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since Dec 2, 2021
 */

@Repository("tempRequestRepo")
public class RequestRepository {
	/**
	 * Table name from database
	 */
	String tableName = "REQUEST";
	
	/**
	 * Database connection boundary class
	 */
	OldDatabaseConnection dao;
	
	/**
	 * Any query that is sent to the database
	 */
	String query;
	
	/**
	 * Max Request ID currently recorded on the database
	 */
	int latestID;
	
	
	Connection con;
	
	
	/**
     * Constructor that initializes the RequestsRepository
     * Update the latestID data member holding the max Request ID from the database
     */
	public RequestRepository() throws Exception {
		dao = new OldDatabaseConnection();

		getLatestRequestId();
	}
	
	
	/**
	 * Adding a request to the database
	 * @param request = the new request associated with an animal
	 * @return 1 if successfully added, 0 otherwise
	 */
	public int addRequest(Request request) throws Exception{
		int responseCheck = 0;
		
		String queryBegin = "INSERT INTO REQUEST (Animal_ID, Request_ID, Requester_ID, Request_Date, Checkout_Date, Return_Date, Reason, Request_Status) VALUES";
//		query = queryBegin + "( '"+request.getAnimalID() +"', '" + request.getRequestID()+"', '" +  request.getRequesterID()
//			+"', '" + request.getRequestDate() +"', '" +request.getCheckoutDate()+"', '" + request.getReturnDate() +"', '" +request.getReason()
//			+"', '" + request.getRequestStatus() 
//			+"');";
//		System.out.println(query);
//		try {
//			responseCheck = dao.manipulateRows(query);
//		}catch(Exception e) {
//			getLatestRequestId();
//			query = queryBegin + "( '"+request.getAnimalID() +"', '" + (this.latestID+1) +"', '" +  request.getRequesterID()
//				+"', '" + request.getRequestDate() +"', '" +request.getCheckoutDate()+"', '" + request.getReturnDate() +"', '" +request.getReason()
//				+"', '" + request.getRequestStatus() +"');";
//			responseCheck = dao.manipulateRows(query);	
//		}
//		return responseCheck;
		
		
		try {
			query = "INSERT INTO " + tableName + " (Animal_ID, Request_ID, Requester_ID, Request_Date, Checkout_Date, Return_Date, Reason, Request_Status) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setInt(1, request.getAnimalID());
			statement.setInt(2, request.getRequestID());
        	statement.setInt(3, request.getRequesterID());
        	statement.setString(4, request.getRequestDate());
        	statement.setString(5, request.getCheckoutDate());
        	statement.setString(5, request.getReturnDate());
        	statement.setString(5, request.getReason());
        	statement.setString(5, request.getRequestStatus());
 	
        	responseCheck = statement.executeUpdate();
        	statement.close();
//			responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			getLatestRequestId();
			query = queryBegin + "( '"+request.getAnimalID() +"', '" + (this.latestID+1) +"', '" +  request.getRequesterID()
				+"', '" + request.getRequestDate() +"', '" +request.getCheckoutDate()+"', '" + request.getReturnDate() +"', '" +request.getReason()
				+"', '" + request.getRequestStatus() +"');";
			responseCheck = dao.manipulateRows(query);	
		}
		return responseCheck;
		
		
		
		
		
		
	}
	
	
	/**
	 * Returns all requests in the system
	 * @return all requests
	 */
	public ArrayList<String> selectAllRequest() throws Exception{
		query = "SELECT R2.Animal_ID, R2.Request_ID, R2.Requester_ID, R2.Request_Date, R2.Checkout_Date, R2.Return_Date, "
				+ "R2.Reason, R2.Request_Status, U.First_Name, U.Last_Name,"
				+ "A.Animal_Name, A.Species  FROM REQUEST AS R2, Users as U, ANIMAL AS A "
				+ " WHERE R2.Requester_ID = U.User_ID AND R2.Animal_ID=A.Animal_ID "
				+" ORDER BY R2.Request_ID ASC"+" ;";
				
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	
	/**
	 * View requests made by one specific user
	 * @param id = the id of the user ID whose requests 
	 * @return ArrayList<String> which contains the requests from a particular user or is empty
	 */
	public ArrayList<String> selectRequestsById(int userID) throws Exception {
		query = "SELECT R2.Animal_ID, R2.Request_ID, R2.Requester_ID, R2.Request_Date, R2.Checkout_Date, R2.Return_Date, "
		+ "R2.Reason, R2.Request_Status, U.First_Name, U.Last_Name, "
		+ "A.Animal_Name, A.Species  FROM REQUEST AS R2, Users as U, ANIMAL AS A  WHERE R2.Requester_ID = U.User_ID AND R2.Animal_ID=A.Animal_ID AND R2.Requester_ID = '"
		+ userID + "' ORDER BY R2.Request_ID ASC;";
		System.out.println("query = "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	
	/**
	 * Remove a specific request from the system
	 * @param id  = id of the request to be removed
	 * @return 1 if removed successfully, 0 otherwise
	 */
	public int deleteRequestById(int requestID) throws Exception{
		String query = "DELETE FROM "+ tableName + " AS C WHERE C.Request_ID='"+requestID+"';";
		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	
	/**
	 * Update an existing request
	 * @param id = the id of the request to be updated
	 * @param update = the Request object with the updated information
	 * @return 1 if successfully updated, 0 otherwise
	 */
	public int updateRequestById(int requestID, Request update) throws Exception{
		 String query = "UPDATE " + tableName + " AS R SET Animal_ID='" + update.getAnimalID() + "', Request_ID='" + requestID 
		 + "', Requester_ID='" + update.getRequesterID() + "', Request_Date='" + update.getRequestDate() + "', Checkout_Date='" + update.getCheckoutDate() 
		 + "', Return_Date='" + update.getReturnDate() + "', Reason='" + update.getReason() +"', Request_Status='" + update.getRequestStatus() 
		 +  "' WHERE R.Request_ID='" + requestID +"';";
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
		this.latestID = Integer.valueOf(latestId);
	}
	
	/**
     * @return the Split placeholder saved in the "DatabaseConnection.java"
     */
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}
	
	
	
}