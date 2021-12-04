package group825.vetapp.animal.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

/**
 * Health status of an animal
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
public class Status {

	/**
	 * ID number of the animal
	 */
	private final UUID id;

	/**
	 * Health status of the animal
	 */
	private final String status;

	/**
	 * Constructor that initializes the Status
	 * @param id UUID for particular animal
	 * @param status String explaining status of animal
	 */
	public Status(@JsonProperty("id") UUID id, @JsonProperty("status") String status) {
		this.id = id;
		this.status = status;
	}

	/**
	 * Checks if any data members aside from the ID is null
	 * @return boolean confirming if any data members (other than id) are null
	 */
	public boolean anyNulls() {
		if (status == null) {
			return true;
		}
		
		return  false;
	}
}