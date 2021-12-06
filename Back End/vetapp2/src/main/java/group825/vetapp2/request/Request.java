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
	 * ID number of the animal this request pertains to
	 */
	private int animalID;

	/**
	 * ID number for the request
	 */
	private int requestID;
	
	/**
	 * ID number of the user who made the request
	 */
	private int requesterID;
	
	/**
	 * Date the request was made
	 */
	private String requestDate;
	
	/**
	 * Date the animal was checked out
	 */
	private String checkoutDate;
	
	/**
	 * Date the animal was returned
	 */
	private String returnDate;
	
	/**
	 * Reason for the animal request
	 */
	private String reason;
	
	/**
	 * Status of the request
	 */
	private String requestStatus;
	
	/**
	 * First name of the user who made the request
	 */
	private String requesterFirstName;
	
	/**
	 * Last name of the user who made the request
	 */
	private String requesterLastName;
	
	/**
	 * Name of the animal being requested
	 */
	private String animalName;
	
	/**
	 * Species of the animal being requested
	 */
	private String animalSpecies;
	


	/**
	 * Constructor for Request to check out an animal
	 * @param animalID = ID number of the animal this request pertains to
	 * @param requestID = ID number for the request
	 * @param requesterID = ID number of the user who made the request
	 * @param requestDate = Date the request was made
	 * @param checkoutDate = Date the animal was checked out
	 * @param returnDate = Date the animal was returned
	 * @param reason = Reason for the animal request
	 * @param requestStatus = Status of the request
	 * @param requesterFirstName = First name of the user who made the request
	 * @param requesterLastName = Last name of the user who made the request
	 * @param animalName = Name of the animal being requested
	 * @param animalSpecies = Species of the animal being requested
	 */
	public Request(@JsonProperty("animalID") int animalID, @JsonProperty("requestID") int requestID,
				   @JsonProperty("requesterID") int requesterID, @JsonProperty("requestDate") String requestDate,
				   @JsonProperty("checkoutDate") String checkoutDate, @JsonProperty("returnDate") String returnDate, 
				   @JsonProperty("reason") String reason, @JsonProperty("requestStatus") String requestStatus, 
				   @JsonProperty("requesterFirstName") String requesterFirstName, @JsonProperty("requesterLastName") String requesterLastName,
				   @JsonProperty("animalName") String animalName, @JsonProperty("animalSpecies") String animalSpecies ) {
		this.animalID = animalID;
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
	 * Checks if any of the necessary fields have been left empty
	 * @return true if any of the fields are left empty, false otherwise
	 */
	public boolean anyNulls() {
		return animalID == 0 || requestID == 0 || requesterID == 0 || requestDate == null ||  requestStatus == null ;
	}
}