package group825.vetapp.animal.photos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;



@Repository("tempPhotosRepo")
public class PhotosRepository { 
	
	private static List<Photo> DB_Photos = new ArrayList<>();
	
	/** insertPhoto function
	 * @param photo = new Photo object for particular animal
	 * @return integer verifying successful code execution
	 */
	public int insertPhoto(Photo photo) {
		UUID id = UUID.randomUUID(); 
		DB_Photos.add(new Photo(id, photo.getPhotoId(), photo.getDateUploaded(), 
				photo.getFilepath()));
		return 1;
	}
	
	
	/** selectAllPhotos function
	 * @return DB of Photos for all animals
	 */
	public List<Photo> selectAllPhotos(){
		return DB_Photos;
	}
	
	/** selectPhotosById function
	 * @param id = UUID pertaining to specific animal
	 * @return Optional<Photo> object which contains the Photo of particular animal or is empty
	 */
	public Optional<Photo> selectPhotosById(UUID id) {
		
		return DB_Photos.stream()
				.filter(photo -> photo.getId().equals(id))
				.findFirst();
	}
	
	/** deletePhotoById function
	 * @param id = UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deletePhotoById(UUID id) {
		Optional<Photo> animalMaybe = selectPhotosById(id);
		if (animalMaybe.isEmpty()) { return 0; }
		DB_Photos.remove(animalMaybe.get());
		return 1;
	}
	
	/**updatePhotoById function
	 * @param id = UUID pertaining to specific animal 
	 * @param photo = Photo object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updatePhotoById(UUID id, Photo update) {
		return selectPhotosById(id)
				.map(animalFound -> {
					int indexOfAnimalToUpdate = DB_Photos.indexOf(animalFound);
					if (indexOfAnimalToUpdate >= 0) { //index was found, 
						DB_Photos.set(indexOfAnimalToUpdate, new Photo(id, 
								update.getPhotoId(), update.getDateUploaded(), 
								update.getFilepath()));
						return 1;   
					}
					return 0; 
				})
				.orElse(0); //if no animal found by the id then return 0
	}
	
	
	
}
