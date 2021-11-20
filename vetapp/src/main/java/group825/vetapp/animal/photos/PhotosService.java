package group825.vetapp.animal.photos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service that performs Photo requests
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Service
public class PhotosService {

	/**
	 * Photo repository that accesses the database
	 */
	private final PhotosRepository dbPhotos;
	
	/**
	 * Constructor that initializes the PhotoService
	 * @param dbPhotos repository denoted with Qualifier annotation storing the Photo objects
	 */
	@Autowired
	public PhotosService(@Qualifier("tempPhotosRepo") PhotosRepository dbPhotos) {
		this.dbPhotos = dbPhotos;
	}

	/**
	 * Inserts a photo in the database
	 * @param photo new Photo object to be added
	 * @return integer verifying successful code execution
	 */
	public int insertPhoto(Photo photo ) {
		return dbPhotos.insertPhoto(photo);
	}
	

	/**
	 * Selects all photos in the database
	 * @return list of all Photo Objects
	 */
	public List<Photo> selectAllPhotos() {
		return dbPhotos.selectAllPhotos();
	}
	
	/**
	 * Selects a photo in the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return Optional<Photo> either containing the Photo object for particular animal or is empty
	 */
	public Optional<Photo> selectPhotosById(UUID id) {
		return dbPhotos.selectPhotosById(id);
	}
	
	/**
	 * Deletes a photo from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deletePhotoById(UUID id) {
		return dbPhotos.deletePhotoById(id);
	}
	
	/**
	 * Updates a photo from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @param photo Photo object with updated data members
	 * @return integer verifying successful code execution
	 */
	public int updatePhotoById(UUID id, Photo photo) {
		return dbPhotos.updatePhotoById(id, photo);
	}
}
