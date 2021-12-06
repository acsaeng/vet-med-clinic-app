package group825.vetapp2.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import group825.vetapp2.animal.Search;
import group825.vetapp2.database.DatabaseConnection;

/**
 * Repository that stores Comment information
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 28, 2021
 */
@Repository("tempCommentsRepo")
public class CommentsRepository {
	/**
	 * Table name from database
	 */
	String tableName = "COMMENT";

	/**
	 * Database connection boundary class
	 */
	DatabaseConnection dao;
	
	/**
	 * Any query that is sent to the database
	 */
	String query;
	
	/**
	 * Max Comment ID currently recorded on the database
	 */
	int latestID;
	
	/**
     * Constructor that initializes the CommentsRepository
     * Update the latestID data member holding the max Comment ID from the database
     */
	public CommentsRepository() throws Exception {
		dao = new DatabaseConnection();
		getLatestCommentID();
	}
	

	/**
	 * Inserts a comment to the database
	 * @param comment = new Comment object for particular animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertComment(Comment comment) throws Exception{
		int responseCheck =0;
		String queryBegin = "INSERT INTO COMMENT (Animal_ID, Comment_ID, Upload_Time, User_ID, Message) VALUES";
		query = queryBegin + "( '"+ comment.getAnimalID() +"', '" + comment.getCommentID()+"', '" +  comment.getTimestamp()
		+"', '" + comment.getAuthorID() +"', '" +comment.getMessage()+"');";
		System.out.println(query);
		try {
			responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			getLatestCommentID();
			query = queryBegin + "( '"+comment.getAnimalID()+"', '" + (this.latestID+1)+"', '" +  comment.getTimestamp()
			+"', '" + comment.getAuthorID() +"', '" +comment.getMessage() +"');";
			responseCheck = dao.manipulateRows(query);
		}
		return responseCheck;
	}
	
	/**
	 * Retrieves all comments in the database
	 * @return ArrayList of strings for all comments for all animals
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectAllComments() throws Exception {
		query = "SELECT C.Animal_ID, C.Comment_ID, C.Upload_Time, C.User_ID, C.Message, U.First_Name, U.Last_Name, U.User_Type "
		+ "FROM COMMENT AS C, USERS AS U WHERE C.User_ID = U.User_ID";
		System.out.println("query for select: "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/**
	 * Selects a comment by animal ID number in the database
	 * @param id = int id pertaining to specific animal
	 * @return ArrayList<String> which contains the comments of particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectCommentsByID(int id) throws Exception{
		query = "SELECT C.Animal_ID, C.Comment_ID, C.Upload_Time, C.User_ID, C.Message, U.First_Name, U.Last_Name, U.User_Type "
				+ "FROM COMMENT AS C, USERS AS U WHERE C.User_ID = U.User_ID AND C.Animal_ID='"+ id +"'";
		System.out.println("query for update: "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}


	/**
	 * Deletes a comment by comment ID number in the database
	 * @param id = int pertaining to specific animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteCommentsByID(int commentID) throws Exception{
		String query = "DELETE FROM "+ tableName + " AS C WHERE C.Comment_ID='"+commentID+"';";
		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}

	/**
	 * Updates a comment by comment ID number in the database
	 * @param id = int id pertaining to specific animal
	 * @param update = Comment object containing new data members
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int updateCommentByID(int commentID, Comment update) throws Exception{
		String query = "UPDATE "+ tableName +" AS C SET Animal_ID='"+update.getAnimalID() + "', Comment_ID='"+commentID 
					+ "', Upload_Time= '"+update.getTimestamp() + "', User_ID='"+update.getAuthorID()
					+ "', Message ='"+ update.getMessage() +"'"
					+ " WHERE C.Comment_ID='"+commentID+"';";
		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * get the latest ID for the primary key for Comment object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestCommentID() throws Exception{
		String queryMaxID = "SELECT MAX(C.Comment_ID) FROM COMMENT AS C ";
//		System.out.println(queryMaxID);
		String latestID = dao.getRows(queryMaxID).replaceAll("\\s+","");
		System.out.println("latestID ='"+latestID+"'");
		this.latestID = Integer.valueOf(latestID);
	}
	
	/**
     * @return the Split placeholder saved in the "DatabaseConnection.java"
     */
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}

	
}



