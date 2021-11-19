package group825.vetapp.animal.treatmentRequest;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Treatment {
	
	private UUID id;
	private String requestFor;
	private String requestBy;
	private String message;

	/**
	 * Constructor for a treatment request
	 * @param id - the request UUID
	 * @param requestFor - the name of the animal being requested
	 * @param requestBy - the name of the requester
	 * @param message - describes the condition in further detail
	 */
	public Treatment(@JsonProperty("id") UUID id, @JsonProperty("requestFor") String requestFor,
		@JsonProperty("requestBy") String requestBy, @JsonProperty("message") String message) {
		this.id = id;
		this.requestFor = requestFor;
		this.requestBy = requestBy;
		this.message = message;
	}

	/**
	 * Checks if any of the fields have been left empty
	 * @return true if any of the fields are left empty
	 */
	public boolean anyNulls() {
		return requestFor == null || requestBy == null || message == null;
	}
	
	
}
