package group825.vetapp.animal.comments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

/**
 * Comment that displays a message for an animal
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
public class Comment {

	/**
	 * ID number of the associated animal
	 */
	private final UUID id;

	/**
	 * ID number of the comment
	 */
	private final String commentId;

	/**
	 * ID number of the comment author
	 */
	private final String authorId;

	/**
	 * Timestamp of the comment
	 */
	private final String timestamp;

	/**
	 * Message of the comment
	 */
	private final String message;

	/**
	 * Constructor that initializes the Comment
	 * @param id UUID of a particular animal
	 * @param commentId ID number for comment
	 * @param authorId  ID number for user that made the comment
	 * @param timestamp timestamp for when the comment was created
	 * @param message message from the user
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

	/**
	 * Checks if any data members aside from the ID is null
	 * @return boolean confirming if any data members (other than id) are null
	 */
	public boolean anyNulls() {
		if (commentId == null) {
			return true;
		} else if (authorId == null) {
			return true;
		} else if (timestamp == null) {
			return true;
		} else if (message == null) {
			return true;
		}
		
		return  false;
	}
}