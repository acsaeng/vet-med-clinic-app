package group825.vetapp.animal.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Request to check out an animal
 *
 * @author Timothy Mok
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
@Setter
public class Request {

	/**
	 * ID number of the requester
	 */
	private UUID id;

	/**
	 * User issuing the request
	 */
	private String requester;

	/**
	 * Date the request was issued
	 */
	private LocalDate requestDate;

	/**
	 * Expected check out date of the animal
	 */
	private LocalDate checkoutDate;

	/**
	 * Expected return date of the animal
	 */
	private LocalDate returnDate;

	/**
	 * Message describing reason for check out
	 */
	private String message;

	/**
	 * Constructor for Request to check out an animal
	 * @param id id of the Request
	 * @param requester user issuing the request
	 * @param requestDate date the request was issued
	 * @param checkoutDate expected check out date of the animal
	 * @param returnDate expected return date of the animal
	 * @param message reason for check out
	 */
	public Request(@JsonProperty("id") UUID id, @JsonProperty("requester") String requester,
				   @JsonProperty("requestDate") LocalDate requestDate, @JsonProperty("checkoutDate") LocalDate checkoutDate,
				   @JsonProperty("returnDate") LocalDate returnDate, @JsonProperty("message") String message) {
		this.id = id;
		this.requester = requester;
		this.requestDate = requestDate;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
		this.message = message;
	}

	/**
	 * Checks if any of the fields have been left empty
	 * @return true if any of the fields are left empty, false otherwise
	 */
	public boolean anyNulls() {
		return requester == null || requestDate == null || checkoutDate == null || returnDate == null || message == null;
	}
}