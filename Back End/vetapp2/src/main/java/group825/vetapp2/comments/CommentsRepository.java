package group825.vetapp2.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import group825.vetapp2.animal.Search;
import group825.vetapp2.database.Application_DbConnection;

/**
 * Repository that stores Comment information
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 28, 2021
 */
@Repository("tempCommentsRepo")
public class CommentsRepository {
	String table_name = "COMMENT";
	Application_DbConnection dao;
	String query;
	int Latest_comment_id;
	
	public CommentsRepository() throws Exception {
		dao = new Application_DbConnection();
		getLatestCommentId();
	}
	
	
	
	
	/**
	 * Inserts a comment to the database
	 * @param comment new Comment object for particular animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertComment(Comment comment) throws Exception{
		String query_begin = "INSERT INTO COMMENT (Animal_ID, Comment_ID, Upload_Time, User_ID, Message) VALUES";
		query = query_begin + "( '"+comment.getId()+"', '" + comment.getCommentId()+"', '" +  comment.getTimestamp()
		+"', '" + comment.getAuthorId() +"', '" +comment.getMessage()+"');";
		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			getLatestCommentId();
			query = query_begin + "( '"+comment.getId()+"', '" + (this.Latest_comment_id+1)+"', '" +  comment.getTimestamp()
			+"', '" + comment.getAuthorId() +"', '" +comment.getMessage() +"');";
			int responseCheck = dao.manipulateRows(query);
		}
		return 1;
	}
	
	/**
	 * Retrieves all comment in the database
	 * @return ArrayList of strings for comments for all animals
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectAllComments() throws Exception {
//		query = "SELECT * FROM "+this.table_name;
		
		query = "SELECT C.Animal_ID, C.Comment_ID, C.Upload_Time, C.User_ID, C.Message, U.First_Name, U.Last_Name, U.User_Type "
		+ "FROM COMMENT AS C, USERS AS U WHERE C.User_ID = U.User_ID";
		
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/**
	 * Selects a comment by ID number in the database
	 * @param id int pertaining to specific animal
	 * @return ArrayList<String> which contains the comments of particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectCommentsById(int id) throws Exception{
//		query = "SELECT * FROM "+this.table_name +" AS C WHERE C.Animal_ID='"+id+"';";
		query = "SELECT C.Animal_ID, C.Comment_ID, C.Upload_Time, C.User_ID, C.Message, U.First_Name, U.Last_Name, U.User_Type "
				+ "FROM COMMENT AS C, USERS AS U WHERE C.User_ID = U.User_ID AND C.Animal_ID='"+ id +"'";
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}


	/**
	 * Deletes a comment by ID number in the database
	 * @param id int pertaining to specific animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteCommentsById(int id) throws Exception{
		String query = "DELETE FROM "+ table_name + " AS C WHERE C.Comment_ID='"+id+"';";
//		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}

	/**
	 * Updates a comment by ID number in the database
	 * @param id int pertaining to specific animal
	 * @param update Comment object containing new data members
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int updateCommentById(int id, Comment update) throws Exception{
		String query = "UPDATE "+ table_name +" AS C SET Animal_ID='"+update.getId() + "', Comment_ID='"+update.getCommentId() 
					+ "', Upload_Time= '"+update.getTimestamp() + "', User_ID='"+update.getAuthorId()
					+ "', Message ='"+ update.getMessage() +"'"
					+ " WHERE C.Comment_ID='"+update.getCommentId()+"';";
		
//		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * get the latest Id for the primary key for Comment object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestCommentId() throws Exception{
		String queryMaxId = "SELECT MAX(C.Comment_ID) FROM COMMENT AS C ";
//		System.out.println(queryMaxId);
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.Latest_comment_id = Integer.valueOf(latestId);
	}
	
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}

	
}



