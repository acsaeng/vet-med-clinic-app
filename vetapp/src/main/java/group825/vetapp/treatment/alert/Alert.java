package group825.vetapp.treatment.alert;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alert {
	
	private UUID id;
	private String animalName;
	private String message;

	/**
	 * Constructor for a Alert
	 * @param id - the alert UUID
	 * @param animalName - the name of the animal being treated
	 * @param message - the message of the alert
	 */
	public Alert(@JsonProperty("id") UUID id, @JsonProperty("animalName") String animalName,
		@JsonProperty("message") String message) {
		this.id = id;
		this.animalName = animalName;
		this.message = message;
	}

	/**
	 * Checks if any of the fields have been left empty
	 * @return true if any of the fields are left empty
	 */
	public boolean anyNulls() {
		return animalName == null || message == null;
	}
	
	
}
