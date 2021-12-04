package group825.vetapp2.users;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//import group825.vetapp.animal.comments.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Service that performs User requests
 *
 * @author Aron Saengchan, Jimmy Zhu
 * @version 2.0
 * @since December 02, 2021
 */
@Service
public class UserService {

    /**
     * User repository that accesses the database
     */
    private final UserRepository repo;

    /**
     * Constructor that initializes the UserService
     * @param repo repository that accesses the database
     */
    public UserService(@Qualifier("tempUserRepo") UserRepository repo) {
        this.repo = repo;
    }

    /**
     * Verifies a user's email and password information
     * @param email user's email
     * @param password user's password
     * @return 1 if login was successful, 0 otherwise
     */
//    public int loginUser(String email, String password) throws Exception{
    public List<User> loginUser(String email, String password) throws Exception{
    	ArrayList<String> results =  this.repo.loginUser(email, password);
		List<User> listResults = createListUser(results);
		return listResults;
//        return this.repo.loginUser(email, password);
    }

    /**
     * Changes a user's password
     * @param email user's email
     * @param newPassword user's new password
     * @return 1 if password change was successful, 0 otherwise
     */
    public int changeUserPassword(String email, String newPassword) {
        return this.repo.changeUserPassword(email, newPassword);
    }

    /**
     * Retrieves all stored users from the database
     * @return a list of all stored users
     */
    public List<User> selectAllUsers() {
        return this.repo.selectAllUsers();
    }

    /**
     * Adds a user to the database
     * @param user user to be added
     * @return 1 if registration was successful, 0 otherwise
     */
    public int addUser(User user) {
        return this.repo.addUser(user);
    }

    /**
     * Updates a user's information
     * @param id user's existing ID
     * @param name user's new or existing name
     * @param email user's new or existing email
     * @return 1 if updates were successful, 0 otherwise
     */
    public int editUser(UUID id, String name, String email) {
        return this.repo.editUser(id, name, email);
    }

    /**
     * Block a user in the system
     * @param id user's ID
     * @return 1 if block was successful, 0 otherwise
     */
    public int blockUser(UUID id) {
        return this.repo.blockUser(id);
    }
    
    
    /**
	  * Create a list of User objects from ArrayList<String> returned from database query
	 * @param foundResults = ArrayList<String> preprocessed response from database of all returned tuples as an ArrayList of Strings
	 * @return ArrayList<Comment> where each object was created from the data in each String from the ArrayList input
	 */
    public List<User> createListUser(ArrayList<String> foundResults){
    	List<User> listResults = new ArrayList<User>(); 
    	//review against Database setup
    	int idx_id=0, idx_firstName=1, idx_lastName=2, idx_userType=3, idx_userName=4, idx_email=5, 
    			idx_phoneNum=6, idx_password=7, idx_startDate=8, idx_userStatus=9;
    	for (String result: foundResults) {
    		
    		String[] resultSplit = result.split(repo.getSplitPlaceholder());
    		User temp;
    		try{
	    		temp =  new User( Integer.valueOf(resultSplit[idx_id]), resultSplit[idx_firstName], resultSplit[idx_lastName], resultSplit[idx_userType], 
		    			resultSplit[idx_userName], resultSplit[idx_email], resultSplit[idx_phoneNum], resultSplit[idx_password], resultSplit[idx_startDate], resultSplit[idx_userStatus]);
	    	}catch(NumberFormatException e) {
//	    		System.out.println("nothing returned");
	    		temp = new User(0, null, null, null, null, null, null, null, null, null);
	    	}
	    	listResults.add(temp);
    }
    System.out.println("\nPrepared List to send as json response to API endpoint:");
    System.out.println(listResults);

    return listResults;
    }
    
}