package group825.vetapp.animal.comments;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Comment {
	private final UUID id;
	private final String commentId;
	private final String authorId;
	private final String timestamp;
	private final String message;
	
	/** Constructor
	 * @param id = UUID of particular animal
	 * @param commentId = id for comment
	 * @param authorId  = id for user that made the comment
	 * @param timestamp = timestamp for when the comment was created
	 * @param message = message from the user
	 */
	public Comment(@JsonProperty("id") UUID id, @JsonProperty("commentId") String commentId, 
			@JsonProperty("authorId") String authorId, @JsonProperty("timestamp") String timestamp, 
			@JsonProperty("message") String message) {
		this.id = id;
		this.commentId = commentId;
		this.authorId = authorId;
		this.timestamp = timestamp;
		this.message = message;
	}

	/** getter for Id
	 * @return id
	 */
	public UUID getId() {
		return id;
	}

	/** getter for commentId
	 * @return commentId
	 */
	public String getCommentId() {
		return commentId;
	}
	
	/** getter for authorId
	 * @return authorId
	 */
	public String getAuthorId() {
		return authorId;
	}

	/** getter for timestamp
	 * @return timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/** getter for message
	 * @return message
	 */
	public String getMessage() {
		return message;
	}




	/** anyNulls function
	 *  checks if any data members aside from the id is null
	 * @return boolean confirming if any data members (other than id) are null
	 */
	public boolean anyNulls() {
		if (commentId == null) {return true;}
		else if (authorId == null) {return true;}
		else if (timestamp == null) {return true;}
		else if (message == null) {return true;}
		
		return  false;
	}

	
}
