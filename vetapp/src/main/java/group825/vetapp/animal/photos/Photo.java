package group825.vetapp.animal.photos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;


public class Photo {
	private final UUID id;
	private final String photoId;
	private final String dateUploaded;
	private final String filepath;


	/** Constructor
	 * @param id = UUID of particular animal
	 * @param photoId = id for specific photo belonging to a particular animal
	 * @param filepath = filepath for where the photo is stored
	 */
	public Photo(@JsonProperty("id") UUID id, @JsonProperty("photoId") String photoId, 
			@JsonProperty("dateUploaded") String dateUploaded, @JsonProperty("filepath") String filepath) {
		this.id = id;
		this.photoId = photoId;
		this.dateUploaded = dateUploaded;
		this.filepath = filepath;
	}



	/** getter for Id
	 * @return id
	 */
	public UUID getId() {
		return id;
	}
	
	/** getter for dateUploaded
	 * @return dateUploaded
	 */
	public String getDateUploaded() {
		return dateUploaded;
	}


	/** getter for photoId
	 * @return photoId
	 */
	public String getPhotoId() {
		return photoId;
	}


	/** getter for filepath
	 * @return filepath
	 */
	public String getFilepath() {
		return filepath;
	}



	/** anyNulls function
	 *  checks if any data members aside from the id is null
	 * @return boolean confirming if any data members (other than id) are null
	 */
	public boolean anyNulls() {
		if (photoId == null) {return true;}
		else if (filepath == null) {return true;}
		
		return  false;
	}

	
}
