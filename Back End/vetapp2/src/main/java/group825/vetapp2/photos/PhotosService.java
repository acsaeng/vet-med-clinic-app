package group825.vetapp2.photos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


/**
 * Service that performs Photo requests
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since Dec 6, 2021
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
	 * @param photo = new Photo object to be added
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
	 * Selects photos from the database by the animal ID number
	 * @param animalID = id pertaining to specific animal
	 * @return List<Photo> either containing the list of Photo objects for particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public List<Photo> selectPhotosByID(int animalID) throws Exception {
		ArrayList<String> results = dbPhotos.selectPhotosByID(animalID);
		List<Photo> listResults = createListPhoto(results);
		return listResults;
	}
	
	/**
	 * Deletes a photo from the database by photo ID number
	 * @param photoID = id pertaining to specific photo for an animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deletePhotoByID(int photoID) throws Exception {
		return dbPhotos.deletePhotoByID(photoID);
	}
	
	/**
	 * Updates a photo from the database by ID number
	 * @param photoID = id pertaining to specific photo for an animal
	 * @param photo = Photo object with updated data members
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int updatePhotoByID(int photoID, Photo photo) throws Exception {
		return dbPhotos.updatePhotoByID(photoID, photo);
	}
	
	/**
	  * Create a list of Photo objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Photo> where each object was created from the data in each String from the ArrayList input
	 */
	public List<Photo> createListPhoto(ArrayList<String> foundResults){
		List<Photo> listResults = new ArrayList<Photo>(); 
		//review against Database setup
		int idxAnimalID=0, idxPhotoID=1, idxDateUploaded=4, idxFilepath=2, idxDescription=5, idxUserID=3;
		for (String result: foundResults) {
			String[] resultSplit = result.split(dbPhotos.getSplitPlaceholder());
			Photo temp =  new Photo( Integer.valueOf(resultSplit[idxAnimalID]),  Integer.valueOf(resultSplit[idxPhotoID]), resultSplit[idxFilepath],  
							Integer.valueOf(resultSplit[idxUserID]), resultSplit[idxDateUploaded], resultSplit[idxDescription]);
			listResults.add(temp);
		}
		System.out.println("\nPrepared List to send as json response to API endpoint:");
		System.out.println(listResults);

		return listResults;
	 }
	
	
}
