package group825.vetapp.animal.photos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import group825.vetapp.animal.comments.Comment;

/**
 * Service that performs Photo requests
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
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
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertPhoto(Photo photo ) throws Exception {
		return dbPhotos.insertPhoto(photo);
	}
	

	/**
	 * Selects all photos in the database
	 * @return list of all Photo Objects
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Photo> selectAllPhotos() throws Exception {
		ArrayList<String> results = dbPhotos.selectAllPhotos();
		List<Photo> listResults = createListPhoto(results);
		return listResults;
	}
	
	/**
	 * Selects a photo in the database by ID number
	 * @param id int pertaining to specific animal
	 * @return Optional<Photo> either containing the Photo object for particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Photo> selectPhotosById(int id) throws Exception {
		ArrayList<String> results = dbPhotos.selectPhotosById(id);
		List<Photo> listResults = createListPhoto(results);
		return listResults;
	}
	
	/**
	 * Deletes a photo from the database by ID number
	 * @param id int pertaining to specific animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deletePhotoById(int id) throws Exception {
		return dbPhotos.deletePhotoById(id);
	}
	
	/**
	 * Updates a photo from the database by ID number
	 * @param id int pertaining to specific animal
	 * @param photo Photo object with updated data members
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int updatePhotoById(int id, Photo photo) throws Exception {
		return dbPhotos.updatePhotoById(id, photo);
	}
	
	/**
	  * Create a list of Photo objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Photo> where each object was created from the data in each String from the ArrayList input
	 */
	public List<Photo> createListPhoto(ArrayList<String> foundResults){
		List<Photo> listResults = new ArrayList<Photo>(); 
		//review against Database setup
		int idx_id=0, idx_photoId=1, idx_dateUploaded=4, idx_filepath=2, idx_description=5, idx_userId=3;
		for (String result: foundResults) {
			String[] resultSplit = result.split(dbPhotos.getSplitPlaceholder());
			Photo temp =  new Photo( Integer.valueOf(resultSplit[idx_id]),  Integer.valueOf(resultSplit[idx_photoId]), resultSplit[idx_filepath],  
							Integer.valueOf(resultSplit[idx_userId]), resultSplit[idx_dateUploaded], resultSplit[idx_description]);
			listResults.add(temp);
		}
//		System.out.println("\nPrepared List to send as json response to API endpoint:");
//		System.out.println(listResults);

		return listResults;
	 }
	
	
}
