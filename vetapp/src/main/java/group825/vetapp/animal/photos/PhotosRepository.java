package group825.vetapp.animal.photos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import group825.vetapp.animal2.Search;
import group825.vetapp.database.Application_DbConnection;

/**
 * Repository that stores Photo information
 *
 * @author Jimmy Zhu
 * @version 2.0
 * @since November 30, 2021
 */
@Repository("tempPhotosRepo")
public class PhotosRepository {
	String table_name = "PHOTOS";
	Application_DbConnection dao;
	String query;
	int Latest_photo_id;
	
	public PhotosRepository() throws Exception {
		dao = new Application_DbConnection();
	}
	
//	/**
//	 * Database that stores all the photos
//	 */
//	private static List<Photo> dbPhotos = new ArrayList<>();
	
	/**
	 * Inserts a photo in the database
	 * @param photo new Photo object for particular animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertPhoto(Photo photo) throws Exception{
		String query_begin = "INSERT INTO PHOTOS (Animal_ID, File_Path, Photo_ID, User_ID, Upload_Date, Description_Photo) VALUES";
		query = query_begin + "( '"+photo.getId() +"', '" + photo.getFilepath() +"', '" + photo.getPhotoId()+"', '" 
		+ photo.getUserId() +"', '" +  photo.getDateUploaded() + "', '" + photo.getDescription()+"');";
		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			this.getLatestId();
			query = query_begin + "( '"+photo.getId() +"', '" + photo.getFilepath() +"', '" + (this.Latest_photo_id+1)+"', '" 
			+ photo.getUserId() +"', '" +  photo.getDateUploaded() + "', '" + photo.getDescription()+"');";
			int responseCheck = dao.manipulateRows(query);
		}
		return 1;
	}

	/**
	 * Selects all photos from the database
	 * @return ArrayList of strings for photos for all animals
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectAllPhotos() throws Exception{
		query = "SELECT * FROM "+this.table_name;
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}

	/**
	 * Selects a photo in the database by ID number
	 * @param id int pertaining to specific animal
	 * @return ArrayList<String> which contains the photos of particular animal or is empty
	 * @throws Exception when there is an SQL Exception
	 */
	public ArrayList<String> selectPhotosById(int id) throws Exception{
		query = "SELECT * FROM "+this.table_name +" AS P WHERE P.Animal_ID='"+id+"';";
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
	}
	
	/** Deletes a photo from the database by ID number
	 * @param id int pertaining to specific animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deletePhotoById(int id) throws Exception{
		String query = "DELETE FROM "+ table_name + " AS P WHERE P.Photo_ID='"+id+"';";
//		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * Updates a photo from the database by ID number
	 * @param id int pertaining to specific animal
	 * @param update Photo object containing new data members
	 * @return integer verifying successful code execution
	 */
	public int updatePhotoById(int id, Photo update) throws Exception {
		String query = "UPDATE "+ table_name +" AS P SET Animal_ID='"+update.getId() + "', Photo_ID='"+ update.getPhotoId() 
		+ "', File_Path= '" + update.getFilepath() + "', User_ID='"+update.getUserId()
		+ "', Upload_Date='"+ update.getDateUploaded() + "', Description_Photo='"+ update.getDescription()   +"'"
		+ " WHERE P.Photo_ID='"+update.getPhotoId()+"';";

		System.out.println("query for update: "+query);
		int responseCheck = dao.manipulateRows(query);
		return responseCheck;
	}
	
	/**
	 * get the latest Id for the primary key for Photo object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestId() throws Exception{
		String queryMaxId = "SELECT MAX(P.Photo_ID) FROM PHOTOS AS P ";
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.Latest_photo_id = Integer.valueOf(latestId);
	}
	
	/**
     * @return the Split placeholder saved in the "Application_DbConnection.java"
     */
	public String getSplitPlaceholder() {
    	return dao.getSplitPlaceholder();
    }
	
}