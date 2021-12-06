package group825.vetapp2.users;

import org.springframework.stereotype.Repository;

import group825.vetapp2.database.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository that stores User information
 *
 * @author Aron Saengchan, Jimmy Zhu, Timothy Mok
 * @version 2.0
 * @since December 6, 2021
 */
@Repository("tempUserRepo")
public class UserRepository {
	
	/**
	 * Table name in SQL database
	 */
	private String tableName = "USERS";
	
	/**
	 * Connection to the SQL database
	 */
	private DatabaseConnection dao;
	
	/**
	 * SQL query
	 */
	private String query;
	
	/**
	 * The latest user ID in the database to update new user's with
	 */
	int latestUserID;

	/**
	 * Constructor for the UserRepository
	 * @throws Exception
	 */
	public UserRepository() throws Exception {
		dao = new DatabaseConnection();
		getLatestUserId();
	}

    /**
     * Verifies a user's email and password information
     * @param email user's email
     * @param password user's password
     * @return 1 if login was successful, 0 otherwise
     */
	public ArrayList<String>  loginUser(String userName, String password) throws Exception{	 
		query = "SELECT * FROM USERS AS U WHERE U.UserName='"+userName+"' AND U.User_Password='"+ password +"'";
		ArrayList<String> results = dao.getResponseArrayList(query);
		if (results.size() == 1) {
			return results;
		}  	 
		return results;
    }

    /**
     * Retrieves all stored users from the database
     * @return a list of all stored users
     * @throws Exception when there is an SQL Exception
     */
    public ArrayList<String> selectAllUsers() throws Exception {
    	query = "SELECT * FROM USERS;";
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
    }

    /**
     * Adds a user to the database
     * @param user user to be added
     * @return 1 if registration was successful, 0 otherwise
     * @throws Exception when there is an SQL Exception
     */
    public int addUser(User user) throws Exception {
    	String query_begin = "INSERT INTO USERS (User_ID, First_Name, Last_Name, User_Type, UserName,  "
    		+ "Email, Phone_Number, User_Password, Start_Date, User_Status) VALUES";
		query = query_begin + "( '"+user.getId() +"', '" + user.getFirstName()+"', '" +  user.getLastName() 
			+ "', '" + user.getUserType() +"', '" + user.getUserName() + "', '" + user.getEmail() + "', '" 
			+ user.getPhoneNum() +"', '" + user.getPassword() + "', '" + user.getStartDate() 
			+ "', '" + user.getUserStatus() + "');";
		System.out.println(query);
		try {
			int responseCheck = dao.manipulateRows(query);
		}catch(Exception e) {
			getLatestUserId();
			query = query_begin + "( '"+user.getId() +"', '" + user.getFirstName()+"', '" +  user.getLastName() 
				+ "', '" + user.getUserType() +"', '" + user.getUserName() + "', '" + user.getEmail() + "', '" 
				+ user.getPhoneNum() +"', '" + user.getPassword() + "', '" + user.getStartDate() 
				+ "', '" + user.getUserStatus() + "');";
			int responseCheck = dao.manipulateRows(query);	
		}
		return 1;
    }

    /**
     * Updates a user's information
     * @param id user's existing ID
     * @param user the User object with new information
     * @return 1 if updates were successful, 0 otherwise
     * @throws Exception when there is an SQL Exception
     */
    public int editUser(int id, User update) throws Exception {
    	String query = "UPDATE " + tableName + " AS U SET User_ID='" + update.getId() 
    		+ "', First_Name='" + update.getFirstName() +"', Last_Name='" + update.getLastName() 
    		+"', User_Type='" + update.getUserType() +"', UserName='" + update.getUserName() 
    		+ "', Email='" + update.getEmail() + "', Phone_Number='" + update.getPhoneNum() +
    		"', User_Password='" + update.getPassword() +"', Start_Date='" + update.getStartDate() +
    		"User_Status='" + update.getStartDate() + "' WHERE U.User_ID='"+ id +"';";
		 System.out.println("query = "+query);
		 int responseCheck = dao.manipulateRows(query);
		 return responseCheck;
    }

    /**
     * Finds a user in the database by ID
     * @param id a specific user's ID
     * @return user associated with a specified ID
     * @throws Exception when there is an SQL Exception
     */
    public ArrayList<String> selectUserById(int id) throws Exception {
    	query = "SELECT * FROM "+ this.tableName +" AS U WHERE U.User_ID='"+id+"';";
		System.out.println("query = "+query);
		ArrayList<String> results = dao.getResponseArrayList(query);
		return results;
    }
    
    
    /**
	 * get the latest Id for the primary key for Comment object from database
	 * @throws Exception when there is an SQL Exception
	 */
	private void getLatestUserId() throws Exception {
		String queryMaxId = "SELECT MAX(U.User_ID) FROM USERS AS U ";
		String latestId = dao.getRows(queryMaxId).replaceAll("\\s+","");
		System.out.println("latestId ='"+latestId+"'");
		this.latestUserID = Integer.valueOf(latestId);
	}
	
	/**
	 * @return a String from the dao split
	 */
	public String getSplitPlaceholder() {
		return dao.getSplitPlaceholder();
	}
    
}