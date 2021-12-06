package group825.vetapp2.diagnosis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Diagnosis of an animal
 *
 * @author Timothy Mok, Jimmy Zhu
 * @version 2.0
 * @since December 5, 2021
 */
@Getter
@Setter
public class Diagnosis {
	
	/**
	 * ID number of the diagnosis
	 */
	private int diagnosisID;
	
	/**
	 * ID number of the animal
	 */
	private int animalID;
	
	/**
	 * Diagnosis of the animal
	 */
	private String diagnosis;
	
	/**
	 * Description of the animal's condition
	 */
	private String description;
	
	/**
	 * Date of the diagnosis
	 */
	private String diagnosisDate;
	
	/**
	 * Status of the diagnosis
	 */
	private String diagnosisStatus;
	
	/**
	 * ID number of the user who made the diagnosis
	 */
	private int userID;
	



	/**
	 * Constructor for a Diagnosis
	 * @param id the diagnosis UUID
	 * @param animalName the name of the animal being treated
	 * @param diagnosis the ailment associated with the animal
	 * @param description describes the condition in further detail
	 */
	public Diagnosis(@JsonProperty("diagnosisID") int diagnosisID, @JsonProperty("animalID") int animalID, @JsonProperty("diagnosis") String diagnosis, 
			@JsonProperty("description") String description, @JsonProperty("diagnosisDate") String diagnosisDate, 
			@JsonProperty("diagnosisStatus") String diagnosisStatus, @JsonProperty("userID") int userID	) {
		this.animalID = animalID;
		this.diagnosisID = diagnosisID;
		this.diagnosis = diagnosis;
		this.description = description;
		this.diagnosisDate = diagnosisDate;
		this.diagnosisStatus = diagnosisStatus;
		this.userID = userID;
	}

	/**
	 * Checks if any of the fields have been left empty
	 * @return true if any of the fields are left empty, false otherwise
	 */
	public boolean anyNulls() {
		return diagnosis == null || description == null || diagnosisID==0 || diagnosisDate==null || userID ==0 || animalID==0 || diagnosisStatus==null;
	}
	
	
	
}