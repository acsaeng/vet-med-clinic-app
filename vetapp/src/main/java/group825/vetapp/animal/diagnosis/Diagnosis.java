package group825.vetapp.animal.diagnosis;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Diagnosis {
	private final UUID id;
	private final String diagnosis;

public Diagnosis(@JsonProperty("id") UUID id, @JsonProperty("diagnosis") String diagnosis) {
		this.id = id;
		this.diagnosis = diagnosis;
	}

	public UUID getId() {
		return id;
	}

	public String getDiagnosis() {
		return diagnosis;
	}
	
	
}
