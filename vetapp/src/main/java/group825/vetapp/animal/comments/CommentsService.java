package group825.vetapp.animal.comments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service that performs Comment requests
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
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
	 */
	public int insertComment(Comment comment ) {
		return dB_Comments.insertComment(comment);
	}

	/**
	 * Selects all comment in the database
	 * @return list of all Comment Objects
	 */
	public List<Comment> selectAllComments() {
		return dB_Comments.selectAllComments();
	}
	
	/**
	 * Selects a comment from the database by ID number
	 * @param id UID pertaining to a specific animal
	 * @return Optional<Comment> either containing the Comment object for particular animal or is empty
	 */
	public Optional<Comment> selectCommentsById(UUID id) {
		return dB_Comments.selectCommentsById(id);
	}

	/**
	 * Deletes a comment from the database by ID number
	 * @param id UUID pertaining to a specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteCommentsById(UUID id) {
		return dB_Comments.deleteCommentsById(id);
	}
	
	/**
	 * Updates a comment from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @param comment Comment object with updated data members
	 * @return integer verifying successful code execution
	 */
	public int updateCommentById(UUID id, Comment comment) {
		return dB_Comments.updateCommentById(id, comment);
	}
}