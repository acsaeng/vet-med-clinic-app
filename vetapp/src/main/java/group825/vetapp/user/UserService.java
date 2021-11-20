package group825.vetapp.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Service that performs User requests
 *
 * @author Aron Saengchan
 * @version 1.0
 * @since November 15, 2021
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
    public int loginUser(String email, String password) {
        return this.repo.loginUser(email, password);
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
}