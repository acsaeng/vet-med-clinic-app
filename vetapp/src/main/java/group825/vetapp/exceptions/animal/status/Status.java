package group825.vetapp.animal.status;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Status {
	private final UUID id;
	private final String status;

public Status(@JsonProperty("id") UUID id, @JsonProperty("status") String status) {
		this.id = id;
		this.status = status;
	}

	public UUID getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}
	
	
}
