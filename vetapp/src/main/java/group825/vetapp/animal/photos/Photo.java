package group825.vetapp.animal.photos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.UUID;

/**
 * Photo of an animal
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Getter
public class Photo {

	/**
	 * ID number of the animal
	 */
	private final UUID id;

	/**
	 * ID number of the photo
	 */
	private final String photoId;

	/**
	 * Date the photo was uploaded
	 */
	private final String dateUploaded;

	/**
	 * File path of the photo
	 */
	private final String filepath;


	/**
	 * Constructor that initializes the Photo
	 * @param id UUID of particular animal
	 * @param photoId ID number for specific photo belonging to a particular animal
	 * @param filepath file path for where the photo is stored
	 */
	public Photo(@JsonProperty("id") UUID id, @JsonProperty("photoId") String photoId,
				 @JsonProperty("dateUploaded") String dateUploaded, @JsonProperty("filepath") String filepath) {
		this.id = id;
		this.photoId = photoId;
		this.dateUploaded = dateUploaded;
		this.filepath = filepath;
	}
	
	/**
	 * Checks if any data members aside from the ID is null
	 * @return boolean confirming if any data members (other than id) are null
	 */
	public boolean anyNulls() {
		if (photoId == null) {
			return true;
		} else if (filepath == null) {
			return true;
		}
		
		return  false;
	}
}
