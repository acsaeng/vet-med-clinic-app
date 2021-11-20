package group825.vetapp.treatment.protocol;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Protocol directive for animal treatment
 *
 * @author Timothy Mok
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
@Setter
public class Protocol {

	/**
	 * ID number of the animal
	 */
	private UUID id;

	/**
	 * Name of the animal
	 */
	private String animalName;

	/**
	 * Diagnosis of the animal
	 */
	private String diagnosis;

	/**
	 * Prescription of the animal
	 */
	private String prescription;

	/**
	 * Protocol for the treatment
	 */
	private String protocol;

	/**
	 * Constructor for a treatment protocol
	 * @param id the protocol UUID
	 * @param animalName the name of the animal being treated
	 * @param diagnosis the associated diagnosis
	 * @param prescription required medication (optional)
	 * @param protocol required treatment
	 */
	public Protocol(@JsonProperty("id") UUID id, @JsonProperty("animalName") String animalName,
					@JsonProperty("diagnosis") String diagnosis, @JsonProperty("prescription") String prescription,
					@JsonProperty("protocol") String protocol) {
		this.id = id;
		this.animalName = animalName;
		this.diagnosis = diagnosis;

		if (prescription.isBlank()) {
			this.prescription = "No prescription required.";
		} else {
			this.prescription = prescription;
		}

		this.protocol = protocol;
	}

	/**
	 * Checks if any of the fields have been left empty (note that prescription is optional)
	 * @return true if any null values for name, diagnosis or protocol fields
	 */
	public boolean anyNulls() {
		return animalName == null || diagnosis == null || protocol == null;
	}
}