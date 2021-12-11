package group825.vetapp2.users;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Service that performs User requests
 *
 * @author Aron Saengchan, Jimmy Zhu, Timothy Mok
 * @version 2.0
 * @since December 6, 2021
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
    public UserService(@Qualifier("userRepo") UserRepository repo) {
        this.repo = repo;
    }

    /**
     * Verifies a user's username and password information
     * @param username user's username
     * @param password user's password
     * @return true if login was successful, false otherwise
     */
    public boolean loginUser(String username, String password) {
    	return this.repo.loginUser(username, password);
    }

    /**
     * Adds a user to the database
     * @param user user to be added
     */
    public void addUser(User user) {
        this.repo.addUser(user);
    }

    /**
     * Returns a user based on ID number
     * @param userID user's ID number
     * @return user if found, 'null' otherwise
     */
    public User selectUserById(int userID) {
        return this.repo.selectUserById(userID);
    }

    /**
     * Updates a user's information
     * @param userID user's ID number
     * @param updatedInfo user's updated information
     */
    public void editUser(int userID, User updatedInfo) {
        this.repo.editUser(userID, updatedInfo);
    }

    /**
     * Blocks a user
     * @param userID user's ID number
     */
    public void blockUser(int userID) {
        this.repo.blockUser(userID);
    }
}