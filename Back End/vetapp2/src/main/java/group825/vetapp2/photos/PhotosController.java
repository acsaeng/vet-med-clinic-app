package group825.vetapp2.photos;

import java.util.List;

import group825.vetapp2.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that handles Photo requests
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since Dec 6, 2021
 */
@RequestMapping("app/photos/animal")
@RestController
public class PhotosController {

	/**
	 * Photo service that performs the request
	 */
	private final PhotosService photosService;

	/**
	 * Constructor that initializes the PhotoController
	 * @param photosService PhotosService object which interacts with the "PhotosRepository" Class
	 */
	@Autowired
	public PhotosController(PhotosService photosService) {
		this.photosService = photosService;
	}

	/**
	 * 'POST' mapping that adds a photo to the database
	 * @param photo RequestBody JSON object to be passed to the Photo class where the JSON keys are already mapped to specific data members
	 * @throws Exception when there is an SQL Exception
	 */
	@PostMapping
	public int addPhoto(@RequestBody Photo photo) throws Exception{	
		if (photo.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		return photosService.insertPhoto(photo);
	}

	/**
	 * 'GET' mapping that retrieves all photos from the database
	 * @return List<Photo> object containing the Photos of all animals by calling method from the repository
	 * @throws Exception when there is an SQL Exception
	 */
	@GetMapping
	public List<Photo> selectAllPhotos() throws Exception{
		return photosService.selectAllPhotos();
	}

	/**
	 * 'GET' mapping that selects a photo from the database by animal ID number
	 * @param animalID = string path variable obtained by path denoted inside the GetMapping annotation
	 * @return Photo object or throw exception
	 * @throws Exception when there is an SQL Exception
	 */
	@GetMapping(path="{animalID}") 
	public List<Photo> selectPhotosByID(@PathVariable("animalID") String animalID){
		try {
			//id of animal
			int id = Integer.valueOf(animalID);
			return photosService.selectPhotosByID(id);
		} catch(Exception e) {
			// Catch if id is not a valid Animal ID from Database
			throw new InvalidIdException();
		}
	}

	/**
	 * 'DELETE' mapping that deletes a photo from the database by ID number
	 * @param photoID id path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{photoID}")
	public void deletePhotoByID(@PathVariable("photoID") String photoID)  throws Exception {
			//id of a photo
			int id = Integer.valueOf(photoID);
			int numRowsAffected = photosService.deletePhotoByID(id);
			if (numRowsAffected == 0) {throw new InvalidIdException();}
	}
	
	/**
	 * 'PUT' mapping that updates a photo's information
	 * @param photoID = id path variable obtained by path denoted inside the PutMapping annotation
	 * @param photoToUpdate response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{photoID}")
	public void updatePhotoByID(@PathVariable("photoID") String photoID, @RequestBody Photo photoToUpdate) throws Exception {
			//id of a photo
			int id = Integer.valueOf(photoID);
			if (photoToUpdate.anyNulls()) {
				throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
			}
			int numRowsAffected = photosService.updatePhotoByID(id, photoToUpdate);
			if (numRowsAffected == 0) {throw new InvalidIdException();}
	}
}