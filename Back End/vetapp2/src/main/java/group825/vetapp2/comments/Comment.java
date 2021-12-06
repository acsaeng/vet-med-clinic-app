package group825.vetapp2.comments;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Comment that displays a message for an animal
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 28, 2021
 */
@Getter
public class Comment {

	/**
	 * ID number of the associated animal
	 */
	private final int id;

	/**
	 * ID number of the comment
	 */
	private final int commentId;

	/**
	 * ID number of the comment author
	 */
	private final int authorId;

	/**
	 * Timestamp of the comment
	 */
	private final String timestamp;

	/**
	 * Message of the comment
	 */
	private final String message;
	
	private final String firstName;
	private final String lastName;
	private final String userType;
	
	

	/**
	 * Constructor that initializes the Comment
	 * @param id int id of a particular animal
	 * @param commentId ID number for comment
	 * @param authorId  ID number for user that made the comment
	 * @param timestamp timestamp for when the comment was created
	 * @param message message from the user
	 */
	public Comment(@JsonProperty("id") int id, @JsonProperty("commentId") int commentId,
				   @JsonProperty("authorId") int authorId, @JsonProperty("timestamp") String timestamp,
				   @JsonProperty("message") String message, @JsonProperty("firstName") String firstName,
				   @JsonProperty("lastName") String lastName, @JsonProperty("userType") String userType) {
		this.id = id;
		this.commentId = commentId;
		this.authorId = authorId;
		this.timestamp = timestamp;
		this.message = message;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}

	/**
	 * Checks if any data members is null
	 * @return boolean confirming if any data members are null
	 */
	public boolean anyNulls() {
		if (commentId==0 || authorId == 0 || timestamp == null || message == null || firstName ==null || lastName==null ||userType==null) {
			return true;
		}
		
		return  false;
	}
	
	 @Override
	    public String toString() {
		 String newString = "{ id: " + id + ", commentId: " + commentId + ", authorId: " + authorId + 
				 ", timestamp: " + timestamp + ", message: " + message + ", firstName: " + firstName 
				 + ", lastName: " + lastName + ", userType: " + userType + "}";	    	
		 return newString;
	    }
	 
	
}