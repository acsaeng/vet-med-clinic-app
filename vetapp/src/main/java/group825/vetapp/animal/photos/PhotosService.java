package group825.vetapp.animal.photos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class PhotosService {
	private final PhotosRepository dB_Photos;
	
	/** Constructor 
	 * @param dB_Photos = Repository denoted with Qualifier annotation storing the Photo objects
	 */
	@Autowired
	public PhotosService(@Qualifier("tempPhotosRepo") PhotosRepository dB_Photos) {
		this.dB_Photos = dB_Photos; 
	}


	/** insertPhoto function 
	 * @param photo = new Photo object to be added
	 * @return integer verifying successful code execution
	 */
	public int insertPhoto(Photo photo ) {
		return dB_Photos.insertPhoto(photo);
	}
	

	/** selectAllPhotos function 
	 * @return List of all Photo Objects
	 */
	public List<Photo> selectAllPhotos(){
		return dB_Photos.selectAllPhotos();
	}
	
	/** selectPhotosById function
	 * @param id = UUID pertaining to specific animal
	 * @return Optional<Photo> either containing the Photo object for particular animal or is empty
	 */
	public Optional<Photo> selectPhotosById(UUID id){
		return dB_Photos.selectPhotosById(id);
	}
	
	/** deletePhotoById function
	 * @param id = UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deletePhotoById(UUID id) {
		return dB_Photos.deletePhotoById(id);
	}
	
	/** updatePhotoById function
	 * @param id = UUID pertaining to specific animal
	 * @param photo = Photo object with updated data members
	 * @return integer verifying successful code execution
	 */
	public int updatePhotoById(UUID id, Photo photo) {
		return dB_Photos.updatePhotoById(id, photo);
	}
	
	
	
}
