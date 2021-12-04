package group825.vetapp.animal.photos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

/**
 * Photo of an animal
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Getter
public class Photo {

	/**
	 * ID number of the animal
	 */
	private final int id;

	/**
	 * ID number of the photo
	 */
	private final int photoId;
	
	/**
	 * ID number of the user who uploaded photo
	 */
	private final int userId;

	/**
	 * Date the photo was uploaded
	 */
	private final String dateUploaded;

	/**
	 * File path of the photo
	 */
	private final String filepath;
	
	
	/**
	 * Description of the photo 
	 */
	private final String description;


	/**
	 * Constructor that initializes the Photo
	 * @param id int of particular animal
	 * @param dateUploaded string holding the information of the date when the photo was uploaded
	 * @param photoId ID number for specific photo belonging to a particular animal
	 * @param filepath file path for where the photo is stored
	 * @param description is a short description of the photo
	 */
	public Photo(@JsonProperty("id") int id, @JsonProperty("photoId") int photoId,
			@JsonProperty("filepath") String filepath, @JsonProperty("userId") int userId,
			@JsonProperty("dateUploaded") String dateUploaded, @JsonProperty("description") String description) {
		this.id = id;
		this.photoId = photoId;
		this.dateUploaded = dateUploaded;
		this.filepath = filepath;
		this.description = description;
		this.userId = userId;
	}
	
	/**
	 * Checks if any data members is null
	 * @return boolean confirming if any data members are null
	 */
	public boolean anyNulls() {
		if (photoId == 0 || photoId ==0 || dateUploaded == null || filepath == null || description ==null ) {
			return true;
		} 
		return  false;
	}
	
	@Override 
	public String toString() { 
		String newString = "{ id: " + id + ", photoId: " + photoId + ", dateUploaded: " + dateUploaded 
				+ ", filepath: " + filepath + ", description: " + description + ", userId: " + userId + "}";
		return newString;
	}
	
}
