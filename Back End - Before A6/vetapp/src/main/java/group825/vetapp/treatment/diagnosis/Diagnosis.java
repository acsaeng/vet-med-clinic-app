package group825.vetapp.treatment.diagnosis;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

/**
 * Diagnosis of an animal
 *
 * @author Timothy Mok
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
@Setter
public class Diagnosis {

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
	 * Description of the animal's condition
	 */
	private String description;

	/**
	 * Constructor for a Diagnosis
	 * @param id the diagnosis UUID
	 * @param animalName the name of the animal being treated
	 * @param diagnosis the ailment associated with the animal
	 * @param description describes the condition in further detail
	 */
	public Diagnosis(@JsonProperty("id") UUID id, @JsonProperty("animalName") String animalName,
					 @JsonProperty("diagnosis") String diagnosis, @JsonProperty("description") String description) {
		this.id = id;
		this.animalName = animalName;
		this.diagnosis = diagnosis;
		this.description = description;
	}

	/**
	 * Checks if any of the fields have been left empty
	 * @return true if any of the fields are left empty, false otherwise
	 */
	public boolean anyNulls() {
		return animalName == null || diagnosis == null || description == null;
	}
}