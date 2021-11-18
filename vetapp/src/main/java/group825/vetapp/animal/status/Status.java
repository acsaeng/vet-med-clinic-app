package group825.vetapp.animal.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

import javax.validation.constraints.NotNull;

public class Status {
	private final UUID id;
	private final String status;

	/** Constructor
	 * @param id = UUID for particular animal
	 * @param status = String explaining status of animal
	 */
	public Status(@JsonProperty("id") UUID id, 
		@JsonProperty("status") String status) {
		this.id = id;
		this.status = status;
	}

	/** getter for Id
	 * @return id
	 */
	public UUID getId() {
		return id;
	}

	/** getter for status
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	
	/** anyNulls function
	 *  checks if any data members aside from the id is null
	 * @return boolean confirming if any data members (other than id) are null
	 */
	public boolean anyNulls() {
		if (status == null) {return true;}
		
		return  false;
	}
	
}
