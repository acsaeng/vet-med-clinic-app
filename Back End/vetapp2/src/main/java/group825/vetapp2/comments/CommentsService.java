package group825.vetapp2.comments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


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
	private final CommentsRepository repo;
	
	/**
	 * Constructor that initializes the CommentsService
	 * @param dbComments repository denoted with Qualifier annotation storing the Comment objects
	 */
	@Autowired
	public CommentsService(@Qualifier("tempCommentsRepo") CommentsRepository dbComments) {
		this.repo = dbComments;
	}

	/**
	 * Inserts a comment in the database
	 * @param comment = new comment to be added
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertComment(Comment comment ) throws Exception {
		return repo.insertComment(comment);
	}

	/**
	 * Selects all comments in the database for all animals
	 * @return list of all Comment Objects
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Comment> selectAllComments() throws Exception{
		ArrayList<String> results = repo.selectAllComments();
		List<Comment> listResults = createListComment(results);
    	return listResults;
	}
	
	/**
	 * Selects all comments from the database for one animal ID number
	 * @param animalID = int id pertaining to a specific animal
	 * @return List<Comment> either containing the Comment objects for particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Comment> selectCommentsByID(int animalID) throws Exception{
		ArrayList<Comment> results =  repo.selectCommentsByID(animalID);
//		List<Comment> listResults = createListComment(results);
		return results;
	}
	
	/**
	 * Selects single comment from the database 
	 * @param commentID = int id pertaining to a specific comment
	 * @return List<Comment> either containing the Comment object or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Comment> selectSingleCommentByID(int commentID) throws Exception{
		ArrayList<Comment> results =  repo.selectSingleCommentByID(commentID);
//		List<Comment> listResults = createListComment(results);
		return results;
	}
	

	/**
	 * Deletes a comment from the database by comment ID number
	 * @param commentID = int id pertaining for one specific comment
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deleteCommentsByID(int commentID) throws Exception {
		return repo.deleteCommentsByID(commentID);
	}
	
//	/**
//	 * Updates a comment from the database by comment ID number
//	 * @param id = int id pertaining to specific comment
//	 * @param comment = Comment object with updated data members
//	 * @return integer verifying successful code execution
//	 * @throws Exception when there is an SQL Exception
//	 */
//	public int updateCommentByID(int commentID, Comment comment) throws Exception{
//		return repo.updateCommentByID(commentID, comment);
//	}
	
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
			int idxID=0, idxCommentID=1, idxAuthorID=3, idxTimestamp=2, idxMessage=4, idxFirstName=5, idxLastName=6, idxUserType=7;			
			for (String result: foundResults) {				
				String[] resultSplit = result.split(repo.getSplitPlaceholder());
//				System.out.println("resultSplit:");
//				for (String resultIn: resultSplit) {System.out.println(resultIn);}							
			Comment temp =  new Comment( Integer.valueOf(resultSplit[idxID]), Integer.valueOf(resultSplit[idxCommentID]),
					Integer.valueOf(resultSplit[idxAuthorID]), resultSplit[idxTimestamp], resultSplit[idxMessage], 	
					resultSplit[idxFirstName], resultSplit[idxLastName], resultSplit[idxUserType]);			
			listResults.add(temp);
			
		}
//		System.out.println("\nPrepared List to send as json response to API endpoint:");
//		System.out.println(listResults);

		return listResults;
	 }
}