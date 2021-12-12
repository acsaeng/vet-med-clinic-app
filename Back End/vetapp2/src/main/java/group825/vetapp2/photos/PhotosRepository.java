package group825.vetapp2.photos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.DatabaseConnection;
import group825.vetapp2.database.OldDatabaseConnection;
import group825.vetapp2.weighthistory.Weight;

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
	String tableName;
	
	/**
	 * Database connection boundary class
	 */
	OldDatabaseConnection dao;
	
	Connection dao2;
	
	/**
	 * Any query that is sent to the database
	 */
	String query;
	
	/**
	 * Max Photo ID currently recorded on the database
	 */
	int latestID;
	
	/**
     * Results of a query to the database
     */
    private ResultSet results;
	
	/**
     * Constructor that initializes the PhotosRepository
     * Update the latestID data member holding the max photo ID from the database
     */
	public PhotosRepository() throws Exception {
		dao = new OldDatabaseConnection();
		dao2 = DatabaseConnection.getConnection();
		
		tableName = "PHOTOS";
	}
	
	
	/**
	 * Inserts a photo in the database
	 * @param photo = new Photo object for particular animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int insertPhoto(Photo photo) throws Exception{
		int responseCheck =0;
//		String query_begin = "INSERT INTO PHOTOS (Animal_ID, File_Path, Photo_ID, User_ID, Upload_Date, Description_Photo) VALUES";
//		query = query_begin + "( '"+photo.getAnimalID() +"', '" + photo.getFilepath() +"', '" + photo.getPhotoID()+"', '" 
//		+ photo.getUserID() +"', '" +  photo.getDateUploaded() + "', '" + photo.getDescription()+"');";
//		System.out.println(query);
//		try {
//			responseCheck = dao.manipulateRows(query);
//		}catch(Exception e) {
//			this.getLatestID();
//			query = query_begin + "( '"+photo.getAnimalID() +"', '" + photo.getFilepath() +"', '" + (this.latestID+1)+"', '" 
//			+ photo.getUserID() +"', '" +  photo.getDateUploaded() + "', '" + photo.getDescription()+"');";
//			responseCheck = dao.manipulateRows(query);
//		}
		
		getLatestID();
		
		PreparedStatement statement = this.dao2.prepareStatement("INSERT INTO PHOTOS (Animal_ID, File_Path, Photo_ID, User_ID, Upload_Date, Description_Photo) VALUES "
				+ "(?, ?, ?, ?, ?, ?)");
		statement.setInt(1, photo.getAnimalID());
		statement.setString(2, photo.getFilepath());
		statement.setInt(3, (this.latestID+1));
		statement.setInt(4, photo.getUserID());
		statement.setString(5, photo.getDateUploaded());
		statement.setString(6, photo.getDescription());
		
		
		responseCheck = statement.executeUpdate();
		statement.close();
		return responseCheck;
	}

	//DELETE THIS--------------------------------------------------------------------------
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
	public ArrayList<Photo> selectPhotosByID(int animalID) throws Exception{
//		query = "SELECT * FROM "+this.tableName +" AS P WHERE P.Animal_ID='"+animalID+"' ORDER BY Upload_Date asc;";
//		ArrayList<String> results = dao.getResponseArrayList(query);
		
		ArrayList<Photo> animalPhotos = new ArrayList<Photo>();
		
		PreparedStatement statement = this.dao2.prepareStatement("SELECT * FROM "+this.tableName +" AS P WHERE P.Animal_ID=? ORDER BY Upload_Date asc;");
		statement.setInt(1, animalID);
		results = statement.executeQuery();
		
		while (results.next()) {
			animalPhotos.add(new Photo(results.getInt("Animal_ID"), results.getInt("Photo_ID"), results.getString("File_Path"), 
					results.getInt("User_ID"), results.getString("Upload_Date"), results.getString("Description_Photo")));
        }
		statement.close();
		return animalPhotos;
	}
	
	
	public String getFilePath(int photoID) throws Exception{
		PreparedStatement statement = this.dao2.prepareStatement("SELECT * FROM "+ tableName+" WHERE Photo_ID = ?;");
        statement.setInt(1, photoID);
        results = statement.executeQuery();
        results.next();
        String filepath = results.getString("File_Path");
        statement.close();
        return filepath;
	}
	
	/** Deletes a photo from the database by photo ID number
	 * @param photoID = id pertaining to specific photo for an animal
	 * @return integer verifying successful code execution
	 * @throws Exception when there is an SQL Exception
	 */
	public int deletePhotoByID(int photoID) throws Exception{
//		String query = "DELETE FROM "+ tableName + " AS P WHERE P.Photo_ID='"+photoID+"';";
////		System.out.println("query for update: "+query);
//		int responseCheck = dao.manipulateRows(query);
		
		
		PreparedStatement statement = this.dao2.prepareStatement("DELETE FROM "+ tableName + " AS P WHERE P.Photo_ID=?;");
		statement.setInt(1, photoID);
		int responseCheck = statement.executeUpdate();
		statement.close();
		return responseCheck;
	}
	
	
	//MAYBE DELETE THIS -------------------------------------------------------------------------------------------------------
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
//		String queryMaxID = "SELECT MAX(P.Photo_ID) FROM PHOTOS AS P ";
//		String latestID = dao.getRows(queryMaxID).replaceAll("\\s+","");
//		System.out.println("latestID ='"+latestID+"'");
//		this.latestID = Integer.valueOf(latestID);
		
		
		PreparedStatement statement = this.dao2.prepareStatement("SELECT MAX(Photo_ID) FROM PHOTOS ");
		results = statement.executeQuery();
		results.next();
		
		this.latestID = results.getInt("Max(Photo_ID)");
		statement.close();
	}
	
	/**
     * @return the Split placeholder saved in the "DatabaseConnection.java"
     */
	public String getSplitPlaceholder() {
    	return dao.getSplitPlaceholder();
    }
	
}