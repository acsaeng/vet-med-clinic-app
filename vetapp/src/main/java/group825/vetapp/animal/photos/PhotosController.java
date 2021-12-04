package group825.vetapp.animal.photos;

import java.util.List;
import java.util.UUID;

import group825.vetapp.animal.comments.Comment;
import group825.vetapp.exceptions.*;

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
 * @version 1.0
 * @since November 15, 2021
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
	public void addPhoto(@RequestBody Photo photo) throws Exception{	
		if (photo.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		photosService.insertPhoto(photo);
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
	 * 'GET' mapping that selects a photo from the database by ID number
	 * @param animalId = string path variable obtained by path denoted inside the GetMapping annotation
	 * @return Photo object or throw exception
	 * @throws Exception when there is an SQL Exception
	 */
	@GetMapping(path="{id}") 
	public List<Photo> selectPhotosById(@PathVariable("id") String animalId){
		try {
			//id of animal
			int id = Integer.valueOf(animalId);
			return photosService.selectPhotosById(id);
		} catch(Exception e) {
			// Catch if id is not a valid Animal ID from Database
			throw new InvalidIdException();
		}
	}

	/**
	 * 'DELETE' mapping that deletes a photo from the database by ID number
	 * @param idStr UUID path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{id}")
	public void deletePhotoById(@PathVariable("id") String photoId)  throws Exception {
			//id of a photo
			int id = Integer.valueOf(photoId);
			int numRowsAffected = photosService.deletePhotoById(id);
			if (numRowsAffected == 0) {throw new InvalidIdException();}
	}
	
	/**
	 * 'PUT' mapping that updates a photo's information
	 * @param idStr int id path variable obtained by path denoted inside the PutMapping annotation
	 * @param photoToUpdate response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{id}")
	public void updatePhotoById(@PathVariable("id") String photoId, @RequestBody Photo photoToUpdate) throws Exception {
			//id of a photo
			int id = Integer.valueOf(photoId);
			if (photoToUpdate.anyNulls()) {
				throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
			}
			int numRowsAffected = photosService.updatePhotoById(id, photoToUpdate);
			if (numRowsAffected == 0) {throw new InvalidIdException();}
	}
}