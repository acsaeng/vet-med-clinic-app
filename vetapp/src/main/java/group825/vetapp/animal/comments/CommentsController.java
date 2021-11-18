package group825.vetapp.animal.comments;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("app/comments/animal")
@RestController
public class CommentsController {
	private final  CommentsService commentsService;

	/** CommentsController Constructor 
	 * @param commentsService = CommentsService object which interacts with the "CommentsRepository" Class
	 */
	@Autowired
	public CommentsController(CommentsService commentsService) {
		this.commentsService = commentsService;
	}
	
	
	
	/** addComments function - POST MAPPING
	 * @param comment = RequestBody json object to be passed to the Comments class where the json keys are already mapped to specific data members
	 */
	@PostMapping
	public void addComment(@RequestBody Comment comment){	
		if (comment.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		commentsService.insertComment(comment);
	}


	
	/** selectAllComments function - GET MAPPING
	 * @return List<Comment> object containing the Comments of all animals by calling method from the repository
	 */
	@GetMapping
	public List<Comment> selectAllComments(){
		return commentsService.selectAllComments();
	}
	
	
	
	/** selectCommentsById function - GET MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the GetMapping annotation
	 * @return Comment object or throw exception
	 */
	@GetMapping(path="{id}") 
	public Comment selectCommentsById(@PathVariable("id") String id_str) {	
		try {
			UUID id = UUID.fromString(id_str);
			return commentsService.selectCommentsById(id)
					.orElseThrow(ApiExceptions.invalidIdException()); //throw exception if UUID is valid but does not exist in database
		}catch(java.lang.IllegalArgumentException e) { //catch if id_str is not a valid UUID
			throw new InvalidIdException();
		}
		
	}
	

	
	/** deleteCommentById function - DELETE MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{id}")
	public void deleteCommentById(@PathVariable("id") String id_str) {
		try {
			UUID id = UUID.fromString(id_str);
			commentsService.deleteCommentsById(id);
		}catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
		
	}
	
	
	
	/** updateCommentById function - PUT MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the PutMapping annotation
	 * @param commentToUpdate = response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{id}")
	public void updateCommentById(@PathVariable("id") String id_str, @RequestBody Comment commentToUpdate) {	
		try {
			UUID id = UUID.fromString(id_str);
			if (commentToUpdate.anyNulls()) {
				throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
			}
			commentsService.updateCommentById(id, commentToUpdate);
		}catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}
}
