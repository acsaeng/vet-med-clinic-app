package group825.vetapp.animal.reminders;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

/**
 * Reminder associated with an animal
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
public class Reminder {

	/**
	 * ID number of an animal
	 */
	private final UUID id;

	/**
	 * ID number of the reminder
	 */
	private final String reminderId;

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
	 * Information included in the remidner
	 */
	private final String note;

	/**
	 * Constructor that initializes the Reminder
	 * @param id UUID of particular animal
	 * @param reminderId ID number for specific reminder belong to a particular animal
	 * @param status status recorded in the reminder
	 * @param dateDue date by which reminder should be completed
	 * @param datePerformed date that the reminder was created
	 * @param note information to be included in reminder
	 */
	public Reminder(@JsonProperty("id") UUID id, @JsonProperty("reminderId") String reminderId, 
			@JsonProperty("status") String status, @JsonProperty("dateDue") String dateDue, 
			@JsonProperty("datePerformed") String datePerformed, @JsonProperty("note") String note) {
		this.id = id;
		this.reminderId = reminderId;
		this.status = status;
		this.dateDue = dateDue;
		this.datePerformed = datePerformed;
		this.note = note;
	}

	/**
	 * Checks if any data members aside from the ID is null
	 * @return boolean confirming if any data members (other than id) are null
	 */
	public boolean anyNulls() {
		if (reminderId == null) {
			return true;
		} else if (status == null) {
			return true;
		} else if (dateDue == null) {
			return true;
		} else if (datePerformed == null) {
			return true;
		} else if (note == null) {
			return true;
		}
		
		return false;
	}
}