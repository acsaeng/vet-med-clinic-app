package group825.vetapp.animal.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class Request {
	
	private UUID id;
	private String requester;
	private LocalDate requestDate;
	private LocalDate checkoutDate;
	private LocalDate returnDate;
	private String message;

	/**
	 * Constructor for Request to check out an animal. All fields are mandatory
	 * @param id - the id of the Request
	 * @param requester - the user issuing the request
	 * @param requestDate - the date the request was issued
	 * @param checkoutDate - the expected checkout date of the animal
	 * @param returnDate - the expected return date of the animal
	 * @param message - reason for checkout
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
	 * @return true if any of the fields are left empty
	 */
	public boolean anyNulls() {
		return requester == null || requestDate == null || checkoutDate == null ||
				returnDate == null || message == null;
	}
	
	
}
