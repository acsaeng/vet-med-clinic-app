package group825.vetapp.animal.comments;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {
	private final CommentsRepository dB_Comments;
	
	/** Constructor 
	 * @param dB_Comments = Repository denoted with Qualifier annotation storing the Comment objects
	 */
	@Autowired
	public CommentsService(@Qualifier("tempCommentsRepo") CommentsRepository dB_Comments) {
		this.dB_Comments = dB_Comments; 
	}


	/** insertComment function 
	 * @param comment = new Comment object to be added
	 * @return integer verifying successful code execution
	 */
	public int insertComment(Comment comment ) {
		return dB_Comments.insertComment(comment);
	}
	

	/** selectAllComment function 
	 * @return List of all Comment Objects
	 */
	public List<Comment> selectAllComments(){
		return dB_Comments.selectAllComments();
	}
	
	/** selectCommentsById function
	 * @param id = UUID pertaining to specific animal
	 * @return Optional<Comment> either containing the Comment object for particular animal or is empty
	 */
	public Optional<Comment> selectCommentsById(UUID id){
		return dB_Comments.selectCommentsById(id);
	}
	
	/** deleteCommentsById function
	 * @param id = UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteCommentsById(UUID id) {
		return dB_Comments.deleteCommentsById(id);
	}
	
	/** updateCommentById function
	 * @param id = UUID pertaining to specific animal
	 * @param comment = Comment object with updated data members
	 * @return integer verifying successful code execution
	 */
	public int updateCommentById(UUID id, Comment comment) {
		return dB_Comments.updateCommentById(id, comment);
	}
	
	
	
}
