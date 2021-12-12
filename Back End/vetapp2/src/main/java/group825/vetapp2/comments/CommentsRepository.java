package group825.vetapp2.comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.DatabaseConnection;
import group825.vetapp2.database.OldDatabaseConnection;

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
	String tableName = "COMMENTS";

	/**
	 * Database connection boundary class
	 */
	OldDatabaseConnection dao;
	
	/**
	 * Connection to the Database 
	 */	
	Connection dao2;
	
	/**
	 * Any query that is sent to the database
	 */
	String query;
	
	/**
	 * Max Comment ID currently recorded on the database
	 */
	int latestID;
	
	/**
     * Results of a query to the database
     */
    private ResultSet results;
	
	/**
     * Constructor that initializes the CommentsRepository
     * Update the latestID data member holding the max Comment ID from the database
     */
	public CommentsRepository() throws Exception {
		dao = new OldDatabaseConnection();
		dao2 = DatabaseConnection.getConnection();
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
//		String queryBegin = "INSERT INTO COMMENT (Animal_ID, Comment_ID, Upload_Time, User_ID, Message) VALUES";
//		query = queryBegin + "( '"+ comment.getAnimalID() +"', '" + comment.getCommentID()+"', '" +  comment.getTimestamp()
//		+"', '" + comment.getAuthorID() +"', '" +comment.getMessage()+"');";
//		System.out.println(query);
//		try {
//			responseCheck = dao.manipulateRows(query);
//		}catch(Exception e) {
//			getLatestCommentID();
//			query = queryBegin + "( '"+comment.getAnimalID()+"', '" + (this.latestID+1)+"', '" +  comment.getTimestamp()
//			+"', '" + comment.getAuthorID() +"', '" +comment.getMessage() +"');";
//			responseCheck = dao.manipulateRows(query);
//		}
		
		
		getLatestCommentID();
		
		PreparedStatement statement = this.dao2.prepareStatement("INSERT INTO COMMENTS (Animal_ID, Comment_ID, Upload_Time, User_ID, Message) VALUES "
				+ "(?, ?, ?, ?, ?)");
		statement.setInt(1, comment.getAnimalID());
		statement.setInt(2, (this.latestID+1));
		statement.setString(3, comment.getTimestamp());
		statement.setInt(4, comment.getAuthorID());
		statement.setString(5, comment.getMessage() );

		responseCheck = statement.executeUpdate();
		statement.close();
		
		return responseCheck;
	}
	
	/**
	 * Retrieves all comments in the database
	 * @return ArrayList of strings for all comments for all animals
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectAllComments() throws Exception {
		query = "SELECT C.Animal_ID, C.Comment_ID, C.Upload_Time, C.User_ID, C.Message, U.First_Name, U.Last_Name, U.User_Type "
		+ "FROM COMMENTS AS C, USERS AS U WHERE C.User_ID = U.User_ID";
		System.out.println("query for select: "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/**
	 * Selects comments by animal ID number in the database
	 * @param id = int id pertaining to specific animal
	 * @return ArrayList<String> which contains the comments of particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<Comment> selectCommentsByID(int animalID) throws Exception{	
		ArrayList<Comment> animalComments = new ArrayList<Comment>();
		
		PreparedStatement statement = this.dao2.prepareStatement("SELECT C.Animal_ID, C.Comment_ID, C.Upload_Time, C.User_ID, C.Message, U.First_Name, U.Last_Name, U.User_Type "
				+ "FROM COMMENTS AS C, USERS AS U WHERE C.User_ID = U.User_ID AND C.Animal_ID=?");
		statement.setInt(1, animalID);
		results = statement.executeQuery();
		
		while (results.next()) {
			animalComments.add(new Comment(results.getInt("Animal_ID"), results.getInt("Comment_ID"),results.getInt("User_ID"), results.getString("Upload_Time"), 
					results.getString("Message"), results.getString("First_Name"),results.getString("Last_Name"), results.getString("User_Type") ));
        }
		statement.close();
		
		return animalComments;
	}
	
	/**
	 * Selects a comment by comment ID number in the database
	 * @param id = int id pertaining to specific comment
	 * @return ArrayList<String> which contains the comment or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<Comment> selectSingleCommentByID(int commentID) throws Exception{	
		ArrayList<Comment> animalComments = new ArrayList<Comment>();
		
		PreparedStatement statement = this.dao2.prepareStatement("SELECT C.Animal_ID, C.Comment_ID, C.Upload_Time, C.User_ID, C.Message, U.First_Name, U.Last_Name, U.User_Type "
				+ "FROM COMMENTS AS C, USERS AS U WHERE C.User_ID = U.User_ID AND C.Comment_ID=?");
		statement.setInt(1, commentID);
		results = statement.executeQuery();
		
		while (results.next()) {
			animalComments.add(new Comment(results.getInt("Animal_ID"), results.getInt("Comment_ID"),results.getInt("User_ID"), results.getString("Upload_Time"), 
					results.getString("Message"), results.getString("First_Name"),results.getString("Last_Name"), results.getString("User_Type") ));
        }
		statement.close();
		
		return animalComments;
	}


	/**
	 * Deletes a comment by comment ID number in the database
	 * @param id = int pertaining to specific animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteCommentsByID(int commentID) throws Exception{
//		String query = "DELETE FROM "+ tableName + " AS C WHERE C.Comment_ID='"+commentID+"';";
//		System.out.println("query for update: "+query);
//		int responseCheck = dao.manipulateRows(query);
		
		PreparedStatement statement = this.dao2.prepareStatement("DELETE FROM "+ tableName + " AS C WHERE C.Comment_ID=?;");
		statement.setInt(1, commentID);
		int responseCheck = statement.executeUpdate();
		statement.close();
		return responseCheck;
	}

//	/**
//	 * Updates a comment by comment ID number in the database
//	 * @param id = int id pertaining to specific animal
//	 * @param update = Comment object containing new data members
//	 * @return integer verifying successful code execution
//	 * @throws Exception when there is an SQL Exception
//	 */
//	public int updateCommentByID(int commentID, Comment update) throws Exception{
////		String query = "UPDATE "+ tableName +" AS C SET Animal_ID='"+update.getAnimalID() + "', Comment_ID='"+commentID 
////					+ "', Upload_Time= '"+update.getTimestamp() + "', User_ID='"+update.getAuthorID()
////					+ "', Message ='"+ update.getMessage() +"'"
////					+ " WHERE C.Comment_ID='"+commentID+"';";
////		System.out.println("query for update: "+query);
////		int responseCheck = dao.manipulateRows(query);
//		
//		PreparedStatement statement = this.dao2.prepareStatement("UPDATE "+ tableName + " SET Message=? WHERE Photo_ID=? ;");
//		statement.setString(1, update.getFilepath());
//		statement.setInt(2, update.getUserID());
//		statement.setString(3, update.getDateUploaded());
//		statement.setString(4, update.getDescription());
//		statement.setInt(5, photoID);
//		
//		int responseCheck = statement.executeUpdate();
//		statement.close();
//			
//		
//		
//		return responseCheck;
//	}
	
	/**
	 * get the latest ID for the primary key for Comment object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestCommentID() throws Exception{
//		String queryMaxID = "SELECT MAX(C.Comment_ID) FROM COMMENT AS C ";
////		System.out.println(queryMaxID);
//		String latestID = dao.getRows(queryMaxID).replaceAll("\\s+","");
//		System.out.println("latestID ='"+latestID+"'");
//		this.latestID = Integer.valueOf(latestID);
		
		PreparedStatement statement = this.dao2.prepareStatement("SELECT MAX(Comment_ID) FROM COMMENTS ");
		results = statement.executeQuery();
		results.next();
		
		this.latestID = results.getInt("Max(Comment_ID)");
		statement.close();
	}
	
	/**
     * @return the Split placeholder saved in the "DatabaseConnection.java"
     */
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}

	
}



