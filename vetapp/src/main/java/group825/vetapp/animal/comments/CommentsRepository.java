package group825.vetapp.animal.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;



@Repository("tempCommentsRepo")
public class CommentsRepository { 
	
	private static List<Comment> DB_Comments = new ArrayList<>();
	
	/** insertComment function
	 * @param comment = new Comment object for particular animal
	 * @return integer verifying successful code execution
	 */
	public int insertComment(Comment comment) {
		UUID id = UUID.randomUUID(); 
		DB_Comments.add(new Comment(id, comment.getCommentId(), 
				comment.getAuthorId(), comment.getTimestamp(),
				comment.getMessage()));
		return 1;
	}	
	
	
	/** selectAllComments function
	 * @return DB of Comments for all animals
	 */
	public List<Comment> selectAllComments(){
		return DB_Comments;
	}
	
	/** selectCommentsById function
	 * @param id = UUID pertaining to specific animal
	 * @return Optional<Comment> object which contains the Comment of particular animal or is empty
	 */
	public Optional<Comment> selectCommentsById(UUID id) {
		
		return DB_Comments.stream()
				.filter(comment -> comment.getId().equals(id))
				.findFirst();
	}
	
	/** deleteCommentsById function
	 * @param id = UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteCommentsById(UUID id) {
		Optional<Comment> animalMaybe = selectCommentsById(id);
		if (animalMaybe.isEmpty()) { return 0; }
		DB_Comments.remove(animalMaybe.get());
		return 1;
	}
	
	/**updateCommentById function
	 * @param id = UUID pertaining to specific animal 
	 * @param update = Comment object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updateCommentById(UUID id, Comment update) {
		return selectCommentsById(id)
				.map(animalFound -> {
					int indexOfAnimalToUpdate = DB_Comments.indexOf(animalFound);
					if (indexOfAnimalToUpdate >= 0) { //index was found, 
						DB_Comments.set(indexOfAnimalToUpdate, new Comment(id, update.getCommentId(), 
								update.getAuthorId(), update.getTimestamp(),
								update.getMessage()));
						return 1;   
					}
					return 0; 
				})
				.orElse(0); //if no animal found by the id then return 0
	}
	
	
	
}
