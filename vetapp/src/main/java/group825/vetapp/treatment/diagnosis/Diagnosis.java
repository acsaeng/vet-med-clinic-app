package group825.vetapp.treatment.diagnosis;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Diagnosis {
	
	private UUID id;
	private String animalName;
	private String diagnosis;
	private String description;

	/**
	 * Constructor for a treatment protocol
	 * @param id - the diagnosis id
	 * @param animalName - the name of the animal being treated
	 * @param diagnosis - the ailment associated with the animal
	 * @param description - describes the condition in further detail
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
	 * @return true if any of the fields are left empty
	 */
	public boolean anyNulls() {
		return animalName == null || diagnosis == null || description == null;
	}
	
	
}
