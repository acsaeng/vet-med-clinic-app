package group825.vetapp2.photos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.DatabaseConnection;

/**
 * Repository that stores Photo information
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since Dec 6, 2021
 */
@Repository("tempPhotosRepo")
public class PhotosRepository {
	/**
	 * Table name from database
	 */
	String tableName = "PHOTOS";
	
	/**
	 * Database connection boundary class
	 */
	DatabaseConnection dao;
	
	/**
	 * Any query that is sent to the database
	 */
	String query;
	
	/**
	 * Max Photo ID currently recorded on the database
	 */
	int latestID;
	
	/**
     * Constructor that initializes the PhotosRepository
     * Update the latestID data member holding the max photo ID from the database
     */
	public PhotosRepository() throws Exception {
		dao = new DatabaseConnection();
	}
	
	
	/**
	 * Inserts a photo in the database
	 * @param photo = new Photo object for particular animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertPhoto(Photo photo) throws Exception{
		int responseCheck =0;
		String query_begin = "INSERT INTO PHOTOS (Animal_ID, File_Path, Photo_ID, User_ID, Upload_Date, Description_Photo) VALUES";
		query = query_begin + "( '"+photo.getAnimalID() +"', '" + photo.getFilepath() +"', '" + photo.getPhotoID()+"', '" 
		+ photo.getUserID() +"', '" +  photo.getDateUploaded() + "', '" + photo.getDescription()+"');";
		System.out.println(query);
		try {
			responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			this.getLatestID();
			query = query_begin + "( '"+photo.getAnimalID() +"', '" + photo.getFilepath() +"', '" + (this.latestID+1)+"', '" 
			+ photo.getUserID() +"', '" +  photo.getDateUploaded() + "', '" + photo.getDescription()+"');";
			responseCheck = dao.manipulateRows(query);
		}
		return responseCheck;
	}

	/**
	 * Selects all photos from the database
	 * @return ArrayList of strings for photos for all animals
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectAllPhotos() throws Exception{
		query = "SELECT * FROM "+this.tableName;
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}

	/**
	 * Selects a photo in the database by animal ID number
	 * @param animalID = id pertaining to specific animal
	 * @return ArrayList<String> which contains the photos of particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectPhotosByID(int animalID) throws Exception{
		query = "SELECT * FROM "+this.tableName +" AS P WHERE P.Animal_ID='"+animalID+"';";
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/** Deletes a photo from the database by photo ID number
	 * @param photoID = id pertaining to specific photo for an animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deletePhotoByID(int photoID) throws Exception{
		String query = "DELETE FROM "+ tableName + " AS P WHERE P.Photo_ID='"+photoID+"';";
//		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * Updates a photo from the database by photo ID number
	 * @param photoID = id pertaining to a specific photo for an animal
	 * @param update Photo object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updatePhotoByID(int photoID, Photo update) throws Exception {
		String query = "UPDATE "+ tableName +" AS P SET Animal_ID='"+update.getAnimalID() + "', Photo_ID='"+ photoID
		+ "', File_Path= '" + update.getFilepath() + "', User_ID='"+update.getUserID()
		+ "', Upload_Date='"+ update.getDateUploaded() + "', Description_Photo='"+ update.getDescription()   +"'"
		+ " WHERE P.Photo_ID='"+photoID+"';";

		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * get the latest ID for the primary key for Photo object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestID() throws Exception{
		String queryMaxID = "SELECT MAX(P.Photo_ID) FROM PHOTOS AS P ";
		String latestID = dao.getRows(queryMaxID).replaceAll("\\s+","");
		System.out.println("latestID ='"+latestID+"'");
		this.latestID = Integer.valueOf(latestID);
	}
	
	/**
     * @return the Split placeholder saved in the "DatabaseConnection.java"
     */
	public String getSplitPlaceholder() {
    	return dao.getSplitPlaceholder();
    }
	
}