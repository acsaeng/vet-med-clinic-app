package group825.vetapp.animal.photos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

/**
 * Repository that stores Photo information
 *
 * @author Jimmy Zhu
 * @version 1.0
 * @since November 15, 2021
 */
@Repository("tempPhotosRepo")
public class PhotosRepository {

	/**
	 * Database that stores all the photos
	 */
	private static List<Photo> dbPhotos = new ArrayList<>();
	
	/**
	 * Inserts a photo in the database
	 * @param photo new Photo object for particular animal
	 * @return integer verifying successful code execution
	 */
	public int insertPhoto(Photo photo) {
		UUID id = UUID.randomUUID();
		dbPhotos.add(new Photo(id, photo.getPhotoId(), photo.getDateUploaded(), photo.getFilepath()));
		return 1;
	}

	/**
	 * Selects all photos from the database
	 * @return database of photos for all animals
	 */
	public List<Photo> selectAllPhotos() {
		return dbPhotos;
	}
	
	/**
	 * Selects a photo in the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return Optional<Photo> object which contains the Photo of particular animal or is empty
	 */
	public Optional<Photo> selectPhotosById(UUID id) {
		return dbPhotos.stream().filter(photo -> photo.getId().equals(id)).findFirst();
	}
	
	/** Deletes a photo from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @return integer verifying successful code execution
	 */
	public int deletePhotoById(UUID id) {
		Optional<Photo> animalMaybe = selectPhotosById(id);

		if (animalMaybe.isEmpty()) {
			return 0;
		}

		dbPhotos.remove(animalMaybe.get());
		return 1;
	}
	
	/**
	 * Updates a photo from the database by ID number
	 * @param id UUID pertaining to specific animal
	 * @param update Photo object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updatePhotoById(UUID id, Photo update) {
		return selectPhotosById(id).map(animalFound -> {
			int indexOfAnimalToUpdate = dbPhotos.indexOf(animalFound);

			// Index was found
			if (indexOfAnimalToUpdate >= 0) {
				dbPhotos.set(indexOfAnimalToUpdate, new Photo(id, update.getPhotoId(), update.getDateUploaded(),
						update.getFilepath()));

				return 1;
			}

			return 0;
		}).orElse(0); // If no animal found by the id then return 0
	}
}