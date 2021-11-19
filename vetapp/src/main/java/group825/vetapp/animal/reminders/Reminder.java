package group825.vetapp.animal.reminders;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;


public class Reminder {
	private final UUID id;
	private final String reminderId;
	private final String status;
	private final String dateDue;
	private final String datePerformed;
	private final String note;

	/** Constructor
	 * @param id = UUID of particular animal
	 * @param reminderId = id for specific reminder belong to a particular animal
	 * @param status = status recorded in this reminder
	 * @param dateDue = date by which reminder should be completed
	 * @param datePerformed = date that the reminder was created
	 * @param note = information to be included in reminder
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

	/** getter for Id
	 * @return id
	 */
	public UUID getId() {
		return id;
	}
	
	/** getter for reminderId
	 * @return reminderId
	 */
	public String getReminderId() {
		return reminderId;
	}

	/** getter for status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/** getter for dateDue
	 * @return dateDue
	 */
	public String getDateDue() {
		return dateDue;
	}

	/** getter for datePerformed
	 * @return datePerformed
	 */
	public String getDatePerformed() {
		return datePerformed;
	}

	/** getter for note
	 * @return note
	 */
	public String getNote() {
		return note;
	}


	/** anyNulls function
	 *  checks if any data members aside from the id is null
	 * @return boolean confirming if any data members (other than id) are null
	 */
	public boolean anyNulls() {
		if (reminderId == null) {return true;}
		else if (status == null) {return true;}
		else if (dateDue == null) {return true;}
		else if (datePerformed == null) {return true;}
		else if (note == null) {return true;}
		
		return  false;
	}

	
}
