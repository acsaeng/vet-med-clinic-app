package group825.vetapp2.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group825.vetapp2.animal.Animal;

/**
 * Service that performs Comment requests
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 28, 2021
 */
@Service
public class CommentsService {

	/**
	 * Comment repository that accesses the database
	 */
	private final CommentsRepository dB_Comments;
	
	/**
	 * Constructor that initializes the CommentsService
	 * @param dbComments repository denoted with Qualifier annotation storing the Comment objects
	 */
	@Autowired
	public CommentsService(@Qualifier("tempCommentsRepo") CommentsRepository dbComments) {
		this.dB_Comments = dbComments;
	}

	/**
	 * Inserts a comment in the database
	 * @param comment new comment to be added
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertComment(Comment comment ) throws Exception {
		return dB_Comments.insertComment(comment);
	}

	/**
	 * Selects all comment in the database
	 * @return list of all Comment Objects
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Comment> selectAllComments() throws Exception{
		ArrayList<String> results = dB_Comments.selectAllComments();
		List<Comment> listResults = createListComment(results);
    	return listResults;
	}
	
	/**
	 * Selects a comment from the database by ID number
	 * @param id UID pertaining to a specific animal
	 * @return List<Comment> either containing the Comment object for particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Comment> selectCommentsById(int id) throws Exception{
		ArrayList<String> results =  dB_Comments.selectCommentsById(id);
		List<Comment> listResults = createListComment(results);
		return listResults;
	}
	

	/**
	 * Deletes a comment from the database by ID number
	 * @param id int pertaining to a specific animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteCommentsById(int id) throws Exception {
		return dB_Comments.deleteCommentsById(id);
	}
	
	/**
	 * Updates a comment from the database by ID number
	 * @param id int pertaining to specific animal
	 * @param comment Comment object with updated data members
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int updateCommentById(int id, Comment comment) throws Exception{
		return dB_Comments.updateCommentById(id, comment);
	}
	
	 /**
	  * Create a list of Comment objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Comment> where each object was created from the data in each String from the ArrayList input
	 */
	public List<Comment> createListComment(ArrayList<String> foundResults){
//		System.out.println("foundResults: ");
//		System.out.println(foundResults);
			List<Comment> listResults = new ArrayList<Comment>(); 
			//review against Database setup
			int idx_id=0, idx_commentId=1, idx_authorId=3, idx_timestamp=2, idx_message=4, idx_firstName=5, idx_lastName=6, idx_userType=7;
			for (String result: foundResults) {
				String[] resultSplit = result.split(dB_Comments.getSplitPlaceholder());
//				System.out.println("resultSplit:");
//				for (String resultIn: resultSplit) {System.out.println(resultIn);}
				
			Comment temp =  new Comment( Integer.valueOf(resultSplit[idx_id]), Integer.valueOf(resultSplit[idx_commentId]), 
					Integer.valueOf(resultSplit[idx_authorId]), resultSplit[idx_timestamp], resultSplit[idx_message], 
					resultSplit[idx_firstName], resultSplit[idx_lastName], resultSplit[idx_userType]);
			listResults.add(temp);
		}
//		System.out.println("\nPrepared List to send as json response to API endpoint:");
//		System.out.println(listResults);

		return listResults;
	 }
}