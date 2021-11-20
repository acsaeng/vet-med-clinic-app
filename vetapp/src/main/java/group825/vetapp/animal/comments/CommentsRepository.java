package group825.vetapp.animal.comments;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

/**
 * Repository that stores Comment information
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Repository("tempCommentsRepo")
public class CommentsRepository {

	/**
	 * Database that stores all the comments
	 */
	private static final List<Comment> dbComments = new ArrayList<>();
	
	/**
	 * Inserts a comment to the database
	 * @param comment new Comment object for particular animal
	 * @return integer verifying successful code execution
	 */
	public int insertComment(Comment comment) {
		UUID id = UUID.randomUUID();
		dbComments.add(new Comment(id, comment.getCommentId(), comment.getAuthorId(), comment.getTimestamp(),
				comment.getMessage()));

		return 1;
	}
	
	/**
	 * Retrieves all comment in the database
	 * @return database of comments for all animals
	 */
	public List<Comment> selectAllComments() {
		return dbComments;
	}
	
	/**
	 * Selects a comment by ID number in the database
	 * @param id UUID pertaining to specific animal
	 * @return Optional<Comment> object which contains the comment of particular animal or is empty
	 */
	public Optional<Comment> selectCommentsById(UUID id) {
		return dbComments.stream().filter(comment -> comment.getId().equals(id)).findFirst();
	}

	/**
	 * Deletes a comment by ID number in the database
	 * @param id UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deleteCommentsById(UUID id) {
		Optional<Comment> animalMaybe = selectCommentsById(id);

		if (animalMaybe.isEmpty()) {
			return 0;
		}

		dbComments.remove(animalMaybe.get());
		return 1;
	}

	/**
	 * Updates a comment by ID number in the database
	 * @param id UUID pertaining to specific animal
	 * @param update Comment object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updateCommentById(UUID id, Comment update) {
		return selectCommentsById(id).map(animalFound -> {
			int indexOfAnimalToUpdate = dbComments.indexOf(animalFound);

			// Index was found
			if (indexOfAnimalToUpdate >= 0) {
				dbComments.set(indexOfAnimalToUpdate, new Comment(id, update.getCommentId(),
						update.getAuthorId(), update.getTimestamp(),
						update.getMessage()));
				return 1;
			}
			return 0;
		}).orElse(0); // If no animal found by ID, return 0
	}
}