package group825.vetapp2.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

import org.springframework.web.bind.annotation.CrossOrigin;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Request to check out an animal
 *
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since Dec 2, 2021
 */

@Getter
@Setter
public class Request {

	/**
	 * ID number of the requester
	 */
	private int animalID;

	private int requestID;
	
	private int requesterID;
	
	private String requestDate;
	
	private String checkoutDate;
	
	private String returnDate;
	
	private String reason;
	
	private String requestStatus;
	
	private String requesterFirstName;
	
	private String requesterLastName;
	
	private String animalName;
	
	private String animalSpecies;
	


//	/**
//	 * Constructor for Request to check out an animal
//	 * @param id id of the Request
//	 * @param requester user issuing the request
//	 * @param requestDate date the request was issued
//	 * @param checkoutDate expected check out date of the animal
//	 * @param returnDate expected return date of the animal
//	 * @param message reason for check out
//	 */
	public Request(@JsonProperty("id") int id, @JsonProperty("requestID") int requestID,
				   @JsonProperty("requesterID") int requesterID, @JsonProperty("requestDate") String requestDate,
				   @JsonProperty("checkoutDate") String checkoutDate, @JsonProperty("returnDate") String returnDate, 
				   @JsonProperty("reason") String reason, @JsonProperty("requestStatus") String requestStatus, 
				   @JsonProperty("requesterFirstName") String requesterFirstName, @JsonProperty("requesterLastName") String requesterLastName,
				   @JsonProperty("animalName") String animalName, @JsonProperty("animalSpecies") String animalSpecies ) {
		this.animalID = id;
		this.requestID = requestID;
		this.requesterID = requesterID;
		this.requestDate = requestDate;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
		this.reason = reason;
		this.requestStatus = requestStatus;
		this.requesterFirstName = requesterFirstName;
		this.requesterLastName = requesterLastName;
		this.animalName = animalName;
		this.animalSpecies = animalSpecies;
	}

	/**
	 * Checks if any of the fields have been left empty
	 * @return true if any of the fields are left empty, false otherwise
	 */
	public boolean anyNulls() {
		return animalID == 0 || requestID == 0 || requesterID == 0 || requestDate == null ||  requestStatus == null ;
	}
}