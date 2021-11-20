package group825.vetapp.animal.comments;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles Comment requests
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@RequestMapping("app/comments/animal")
@RestController
public class CommentsController {

	/**
	 * Comments service that performs the request
	 */
	private final  CommentsService commentsService;

	/**
	 * Constructor that initializes the CommentsService
	 * @param commentsService CommentsService object which interacts with the "CommentsRepository" Class
	 */
	@Autowired
	public CommentsController(CommentsService commentsService) {
		this.commentsService = commentsService;
	}

	/**
	 * 'POST' mapping that adds a comment to an animal profile
	 * @param comment RequestBody JSON object to be passed to the Comments class where the JSON keys are already mapped to specific data members
	 */
	@PostMapping
	public void addComment(@RequestBody Comment comment) {
		if (comment.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}

		commentsService.insertComment(comment);
	}

	/**
	 * 'GET' mapping that retrieves all comments from the database
	 * @return List<Comment> object containing the Comments of all animals by calling method from the repository
	 */
	@GetMapping
	public List<Comment> selectAllComments() {
		return commentsService.selectAllComments();
	}

	/**
	 * 'GET' mapping that searches for a comment by ID number in the database
	 * @param idStr UUID path variable obtained by path denoted inside the GetMapping annotation
	 * @return comment object or throw exception
	 */
	@GetMapping(path="{id}") 
	public Comment selectCommentsById(@PathVariable("id") String idStr) {
		try {
			UUID id = UUID.fromString(idStr);

			// Throws exception if UUID is valid but does not exist in database
			return commentsService.selectCommentsById(id).orElseThrow(ApiExceptions.invalidIdException());
		} catch(java.lang.IllegalArgumentException e) {
			// Catch if idStr is not a valid UUID
			throw new InvalidIdException();
		}
	}

	/**
	 * 'DELETE' mapping that deletes a comment by ID number
	 * @param idStr UUID path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{id}")
	public void deleteCommentById(@PathVariable("id") String idStr) {
		try {
			UUID id = UUID.fromString(idStr);
			commentsService.deleteCommentsById(id);
		} catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}

	/**
	 * 'PUT' mapping that updates a comment by ID number
	 * @param idStr UUID path variable obtained by path denoted inside the PutMapping annotation
	 * @param commentToUpdate response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{id}")
	public void updateCommentById(@PathVariable("id") String idStr, @RequestBody Comment commentToUpdate) {
		try {
			UUID id = UUID.fromString(idStr);

			if (commentToUpdate.anyNulls()) {
				throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
			}

			commentsService.updateCommentById(id, commentToUpdate);
		} catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}
}