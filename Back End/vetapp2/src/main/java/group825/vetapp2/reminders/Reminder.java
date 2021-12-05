package group825.vetapp2.reminders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

/**
 * Reminder associated with an animal
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Getter
public class Reminder {

	/**
	 * ID number of an animal
	 */
	private final int id;

	/**
	 * ID number of the reminder
	 */
	private final int reminderId;

	/**
	 * Status of the reminder
	 */
	private final String status;

	/**
	 * Due date of the reminder
	 */
	private final String dateDue;

	/**
	 * Date the reminder was created
	 */
	private final String datePerformed;
	
	/**
	 * ID number of the author
	 */
	private final int authorID;

	/**
	 * Information included in the reminder
	 */
	private final String note;
	
	private final String firstName;
	private final String lastName;
	private final String userType;

	/**
	 * Constructor that initializes the Reminder
	 * @param id UUID of particular animal
	 * @param reminderId ID number for specific reminder belong to a particular animal
	 * @param status status recorded in the reminder
	 * @param dateDue date by which reminder should be completed
	 * @param datePerformed date that the reminder was created
	 * @param note information to be included in reminder
	 */
	public Reminder(@JsonProperty("id") int id, @JsonProperty("reminderId") int reminderId, 
			@JsonProperty("status") String status, @JsonProperty("dateDue") String dateDue, 
			@JsonProperty("datePerformed") String datePerformed, @JsonProperty("authorID") int authorID, 
			@JsonProperty("note") String note, @JsonProperty("firstName") String firstName, 
			@JsonProperty("lastName") String lastName, @JsonProperty("userType") String userType) {
		this.id = id;
		this.reminderId = reminderId;
		this.status = status;
		this.dateDue = dateDue;
		this.datePerformed = datePerformed;
		this.authorID = authorID;
		this.note = note;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}

	/**
	 * Checks if any data members is null
	 * @return boolean confirming if any data members are null
	 */
	public boolean anyNulls() {
		if (id == 0 || reminderId == 0 || status == null || dateDue == null || datePerformed == null 
				|| authorID ==0 || note == null) {
			return true;
		}
		
		return false;
	}
	
	@Override 
	public String toString() {
		String newString = "{ id: " + id + ", reminderId: " + reminderId + ", status: " + status + ", dateDue: " 
				+ dateDue + ", datePerformed: " + datePerformed + ", authorID: " + authorID + ", note: " + note + "}";
	 return newString;
	}
	
}