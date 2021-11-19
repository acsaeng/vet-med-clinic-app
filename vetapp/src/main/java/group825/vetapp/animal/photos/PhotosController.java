package group825.vetapp.animal.photos;

import java.util.List;
import java.util.UUID;

import group825.vetapp.exceptions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RequestMapping("app/photos/animal")
@RestController
public class PhotosController {
	private final PhotosService photosService;

	/** PhotosController Constructor 
	 * @param photosService = PhotosService object which interacts with the "PhotosRepository" Class
	 */
	@Autowired
	public PhotosController(PhotosService photosService) {
		this.photosService = photosService;
	}
	
	
	
	/** addPhoto function - POST MAPPING
	 * @param photo = RequestBody json object to be passed to the Photo class where the json keys are already mapped to specific data members
	 */
	@PostMapping
	public void addPhoto(@RequestBody Photo photo){	
		if (photo.anyNulls()) {
			throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
		}
		photosService.insertPhoto(photo);
	}


	
	/** selectAllPhotos function - GET MAPPING
	 * @return List<Photo> object containing the Photos of all animals by calling method from the repository
	 */
	@GetMapping
	public List<Photo> selectAllPhotos(){
		return photosService.selectAllPhotos();
	}
	
	
	
	/** selectPhotoById function - GET MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the GetMapping annotation
	 * @return Photo object or throw exception
	 */
	@GetMapping(path="{id}") 
	public Photo selectPhotoById(@PathVariable("id") String id_str) {	
		try {
			UUID id = UUID.fromString(id_str);
			return photosService.selectPhotosById(id)
					.orElseThrow(ApiExceptions.invalidIdException()); //throw exception if UUID is valid but does not exist in database
		}catch(java.lang.IllegalArgumentException e) { //catch if id_str is not a valid UUID
			throw new InvalidIdException();
		}
		
	}
	

	
	/** deletePhotoById function - DELETE MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the DeleteMapping annotation
	 */
	@DeleteMapping(path = "{id}")
	public void deletePhotoById(@PathVariable("id") String id_str) {
		try {
			UUID id = UUID.fromString(id_str);
			photosService.deletePhotoById(id);
		}catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
		
	}
	
	
	
	/** updatePhotoById function - PUT MAPPING
	 * @param id = UUID path variable obtained by path denoted inside the PutMapping annotation
	 * @param photoToUpdate = response body from HTTP request which should contain keys for necessary data members
	 */
	@PutMapping(path = "{id}")
	public void updatePhotoById(@PathVariable("id") String id_str, @RequestBody Photo photoToUpdate) {	
		try {
			UUID id = UUID.fromString(id_str);
			if (photoToUpdate.anyNulls()) {
				throw new ApiRequestException("Data members cannot be null! Check the Request Body being sent.");
			}
			photosService.updatePhotoById(id, photoToUpdate);
		}catch(java.lang.IllegalArgumentException e) {
			throw new InvalidIdException();
		}
	}
}
